package BusinessLogic;

import Model.SelectionPolicy;
import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private List<Thread> threads;
    private int maxNoServers;
    private int maxTasksPerServer;

    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<Server>();
        this.threads = new ArrayList<Thread>();
        for (int i = 0; i < maxNoServers; i++) {
            this.servers.add(new Server(maxTasksPerServer));
            this.threads.add(new Thread(this.servers.get(i)));
            this.threads.get(i).start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_TIME)
            this.strategy = new TimeStrategy();
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            this.strategy = new ShortestQueueStrategy();
    }

    public int dispatchTask(Task t) {
        return this.strategy.addTask(this.servers, t);
    }

    public void stopServers() {
        for (Thread t : this.threads) {
            t.interrupt();
        }
    }

    public List<Server> getServers() {
        return servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

}