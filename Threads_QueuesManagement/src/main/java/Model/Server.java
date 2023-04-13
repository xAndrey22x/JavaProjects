package Model;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private AtomicInteger serviceTime;
    private AtomicInteger tasksServed;

    public Server(int maxTaskPerServer) {
        this.tasks = new ArrayBlockingQueue<Task>(maxTaskPerServer);
        this.waitingPeriod = new AtomicInteger();
        this.serviceTime = new AtomicInteger();
        this.tasksServed = new AtomicInteger();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        this.waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    public Object[] getTasks() {
        return this.tasks.toArray();
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public Double getServiceTime() {
        return serviceTime.doubleValue();
    }

    public int getTasksServed() {
        return tasksServed.get();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                if (this.tasks.peek() != null) {
                    int tempService = this.serviceTime.get();
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        this.waitingPeriod.addAndGet(-1);
                        this.tasks.peek().setServiceTime(this.tasks.peek().getServiceTime() - 1);
                        tempService++;
                        if (this.tasks.peek().getServiceTime() == 0)
                            break;
                    }
                    this.tasks.poll();
                    this.serviceTime.set(tempService);
                    this.tasksServed.addAndGet(1);
                }
                if (Thread.currentThread().isInterrupted())
                    break;
            }
        }
    }
}