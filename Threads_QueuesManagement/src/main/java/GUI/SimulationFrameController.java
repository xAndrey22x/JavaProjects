package GUI;

import Model.SelectionPolicy;
import BusinessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrameController {

    private SimulationFrame simulationFrame;

    public SimulationFrameController(SimulationFrame simulationFrame) {
        this.simulationFrame = simulationFrame;
        this.simulationFrame.startButtonListener(new ActionListener() {

            private int numberOfClients;
            private int numberOfServers;
            private int timeLimit;
            private int minArrivalTime;
            private int maxArrivalTime;
            private int minProcessingTime;
            private int maxProcessingTime;
            private SelectionPolicy selectionPolicy;

            private SimulationLog simulationLog;

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verified = false;
                try {
                    this.numberOfClients = checkSimData(simulationFrame.getClient());
                    this.numberOfServers = checkSimData(simulationFrame.getQueue());
                    this.timeLimit = checkSimData(simulationFrame.getSim());
                    this.minArrivalTime = checkSimData(simulationFrame.getArrivalMin());
                    this.maxArrivalTime = checkSimData(simulationFrame.getArrivalMax());
                    if (this.minArrivalTime >= this.maxArrivalTime)
                        throw new Exception("Wrong bounds of arrival time!");
                    this.minProcessingTime = checkSimData(simulationFrame.getServiceMin());
                    this.maxProcessingTime = checkSimData(simulationFrame.getServiceMax());
                    if (this.minProcessingTime >= this.maxProcessingTime)
                        throw new Exception("Wrong bounds of service time!");
                    if (simulationFrame.timeStrategy() && simulationFrame.shortestQueueStrategy())
                        throw new Exception("Select one strategy!");
                    else if (simulationFrame.timeStrategy())
                        this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
                    else if (simulationFrame.shortestQueueStrategy())
                        this.selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
                    else throw new Exception("A strategy must be selected!");
                    verified = true;
                } catch (Exception ex) {
                    simulationFrame.errorMessage(ex.getMessage());
                }
                if (verified) {
                    simulationFrame.setVisible(false);
                    simulationLog = new SimulationLog();
                    simulationLog.setVisible(true);
                    SimulationManager gen = new SimulationManager(this.numberOfClients, this.numberOfServers, this.timeLimit, this.minArrivalTime,
                            this.maxArrivalTime, this.minProcessingTime, this.maxProcessingTime, this.selectionPolicy, this.simulationLog, simulationFrame.fullLog());
                    Thread t = new Thread(gen);
                    t.start();
                }
            }

            public int checkSimData(String str) throws Exception {
                if (str.length() == 0)
                    throw new Exception("Empty field");
                int number;
                try {
                    number = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    throw new Exception("Must be a number => " + str);
                }
                if (number <= 0)
                    throw new Exception("Must be a positive number => " + str);
                return number;
            }
        });
    }

}