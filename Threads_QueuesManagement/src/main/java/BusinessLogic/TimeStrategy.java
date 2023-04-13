package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class TimeStrategy implements Strategy {
    @Override
    public int addTask(List<Server> servers, Task t) {
        int minTime = Integer.MAX_VALUE;
        int waitingTime = 0;
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() < minTime)
                minTime = s.getWaitingPeriod().get();
        }
        for (Server s : servers) {
            if (s.getWaitingPeriod().get() == minTime) {
                for (Object t1 : s.getTasks()) {
                    Task t1Task = (Task) t1;
                    waitingTime += t1Task.getServiceTime();
                }
                s.addTask(t);
                break;
            }
        }
        return waitingTime;
    }
}