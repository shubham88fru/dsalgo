package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/design-task-manager/
public class DesignTaskManager {
    Map<Integer, Task> taskMap = new HashMap<>();
    PriorityQueue<Task> globalQ;

    public DesignTaskManager(List<List<Integer>> tasks) {
        Comparator<Task> cmp1 = (t1, t2) -> t2.priority - t1.priority;
        Comparator<Task> cmp2 = (t1, t2) -> t2.taskId - t1.taskId;
        globalQ = new PriorityQueue<>(cmp1.thenComparing(cmp2));

        for (List<Integer> task: tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);

            Task t = new Task(userId, taskId, priority, false);
            taskMap.put(taskId, t);

            globalQ.add(t);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task t = new Task(userId, taskId, priority, false);
        taskMap.put(taskId, t);
        globalQ.add(t);
    }

    public void edit(int taskId, int newPriority) {
        System.out.println(taskId);
        if (taskMap.containsKey(taskId)) taskMap.get(taskId).stale = true;
        Task t = new Task(taskMap.get(taskId).userId, taskId, newPriority, false);
        taskMap.put(taskId, t);
        globalQ.add(t);
    }

    public void rmv(int taskId) {
        taskMap.get(taskId).stale = true;
    }

    public int execTop() {
        if (globalQ.isEmpty()) return -1;

        while (!globalQ.isEmpty()) {
            Task t = globalQ.remove();

            if (!t.stale) {
                taskMap.remove(t.taskId);
                return t.userId;
            }
        }

        return -1;
    }
}

class Task {
    int userId;
    int taskId;
    int priority;
    boolean stale;

    public Task(int userId, int taskId, int priority, boolean stale) {
        this.userId = userId;
        this.taskId = taskId;
        this.priority = priority;
        this.stale = stale;
    }
}
