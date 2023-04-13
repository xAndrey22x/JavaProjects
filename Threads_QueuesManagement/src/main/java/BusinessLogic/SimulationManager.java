package BusinessLogic;

import GUI.SimulationLog;
import Model.SelectionPolicy;
import Model.Server;
import Model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable {

    private int numberOfClients;
    private int numberOfServers;
    private int timeLimit;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minProcessingTime;
    private int maxProcessingTime;
    private SelectionPolicy selectionPolicy;

    private Scheduler scheduler;

    private List<Task> generatedTasks;

    private BufferedWriter writerLog;
    private BufferedWriter writerResult;
    private BufferedWriter writerClients;

    private SimulationLog simulationLog;

    private double avgWaiting;
    private double avgService;
    private int peakHour;
    private int maxTaskPerServer = 0;

    private boolean fullLog;

    public SimulationManager(int numberOfClients, int numberOfServers, int timeLimit, int minArrivalTime, int maxArrivalTime,
                             int minProcessingTime, int maxProcessingTime, SelectionPolicy selectionPolicy, SimulationLog simulationLog, boolean fullLog) {
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.selectionPolicy = selectionPolicy;
        this.simulationLog = simulationLog;
        this.fullLog = fullLog;
        this.scheduler = new Scheduler(this.numberOfServers, this.numberOfClients);
        this.scheduler.changeStrategy(selectionPolicy);
        this.generatedTasks = new ArrayList<Task>();
        generateNRandomTasks();
        try {
            this.writerLog = new BufferedWriter(new FileWriter("logEvents.txt"));
            this.writerResult = new BufferedWriter(new FileWriter("result.txt"));
            this.writerClients = new BufferedWriter(new FileWriter("logClients.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNRandomTasks() {
        for (int i = 1; i <= numberOfClients; i++) {
            Random random = new Random();
            int processingTime = random.nextInt(minProcessingTime, maxProcessingTime + 1);
            random = new Random();
            int arrivalTime = random.nextInt(minArrivalTime, maxArrivalTime + 1);
//            this.avgService += processingTime;
            Task t = new Task(i, arrivalTime, processingTime);
            this.generatedTasks.add(t);
            Collections.sort(this.generatedTasks);
        }
//        this.avgService /= numberOfClients;
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime <= timeLimit) {
            removeTask(currentTime);
            int k = updateFrame(currentTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (k == 0 && this.generatedTasks.size() == 0) break;
            currentTime++;
        }
        this.scheduler.stopServers();
        this.avgWaiting /= this.numberOfClients;
        int count = 0;
        for (Server s: this.scheduler.getServers()) {
            this.avgService += s.getServiceTime();
            count += s.getTasksServed();
        }
        this.avgService /= count;
        String result = "Average waiting time: " + String.format("%.2f", this.avgWaiting) + "\n" +
                "Average service time: " + String.format("%.2f", this.avgService) + "\n" + "Peak hour: " + this.peakHour;
        ;
        this.simulationLog.updateResultText(result);
        try {
            this.writerLog.close();
            this.writerClients.close();
            this.writerResult.write(result);
            this.writerResult.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTask(int currentTime) {
        Iterator<Task> itr = this.generatedTasks.iterator();
        while (itr.hasNext()) {
            Task t = itr.next();
            if (t.getArrivalTime() == currentTime) {
                this.avgWaiting += this.scheduler.dispatchTask(t);
                itr.remove();
            }
        }
    }

    public int updateFrame(int currentTime) {
        String logEvent = "Time " + currentTime + "\n";
        int i = 1, k = 0, max = 0;
        for (Server s : this.scheduler.getServers()) {
            max += s.getTasks().length;
            logEvent = logEvent + "Queue " + i + ":\n";
            for (Object t : s.getTasks()) logEvent = logEvent + "   " + ((Task) t) + "\n";
            if (s.getTasks().length != 0)
                k = 1;
            else logEvent = logEvent + "   (empty)\n";
            i++;
        }
        if (max >= this.maxTaskPerServer) {
            maxTaskPerServer = max;
            this.peakHour = currentTime;
        }
        logEvent += "\n";
        if (this.fullLog)
            this.simulationLog.updateFullLogText(logEvent);
        else this.simulationLog.updateLogText(logEvent);
        String clients;
        try {this.writerLog.write(logEvent);} catch (IOException e) {throw new RuntimeException(e);}
        if (this.generatedTasks.size() == 0) {
            clients = "Time " + currentTime + "\n" + "   (empty)\n";
        }
        else {
            clients = "Time " + currentTime + "\n";
            for (Task t : this.generatedTasks)
                clients += "   " + t + "\n";
        }
        this.simulationLog.updateClientText(clients);
        try {this.writerClients.write(clients);} catch (IOException e) {throw new RuntimeException(e);}
        return k;
    }
}