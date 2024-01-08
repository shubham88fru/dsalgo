package ptrn.twoheaps;

import java.util.*;

//@link - https://www.codingninjas.com/studio/problems/cpu-task-scheduler_2248081?leftPanelTabValue=SUBMISSION
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5234130054021120
public class ScheduleTasksOnMinimumMachines {
    public static int cpuTaskScheduler(int n, int[][] arr){
        // Write your code here.
        //return kindaBrute(n, arr);
        return optimal(n, arr);
    }

    /**
     This problem is a good candidate for the two heaps pattern since
     we require efficient access to these two quantities that are computed
     after sorting:
     - The task with the earliest start time that has not yet been scheduled.
     - The machine whose currently scheduled workload ends first.
     **/
    private static int optimal(int n, int[][] arr) {
        //keep track of the task the starts the earliest.
        PriorityQueue<int[]> taskHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        //keep track of the core that frees up the earliest. (earliest end time)
        PriorityQueue<Integer> coreTaskHeap = new PriorityQueue<>();
        int minMachines = 0;

        //add all task in the minHeap.
        for (int[] task: arr) {
            taskHeap.add(task);
        }

        //iterate over all the tasks
        while (!taskHeap.isEmpty()) {
            int[] task = taskHeap.remove();
            //If a core has a task that has finished by the current task's start,
            //we don't need an extra core. We'll schedule the current task on that
            //core itself.
            if (!coreTaskHeap.isEmpty() && task[0] >= coreTaskHeap.peek()) {
                coreTaskHeap.remove();
            } else {
                //otherwise, we need an extra core.
                minMachines += 1;
            }

            //update the heap with current task's end.
            coreTaskHeap.add(task[1]);
        }

        return minMachines;
    }

    private static int kindaBrute(int n, int[][] arr) {
        //sort the task intervals in ascending order.
        Arrays.sort(arr, Comparator.<int[]>comparingInt(a -> a[0])
                .thenComparingInt(a -> a[1]));

        List<int[]> cores = new ArrayList<>();

        //for the sorted tasks
        //check if they can be scheduled
        //on any of the available cores.
        for (int[] task: arr) {
            boolean scheduled = false;
            for (int[] core: cores) {
                //if current task on the core has
                //ended before the start of the curr task,
                //the task can be scheduled on this core.
                if (task[0] >= core[1]) {
                    core[0] = task[0];
                    core[1] = task[1];
                    scheduled = true;
                    break;
                }
            }

            //if here, and the task wasn't scheduled,
            //means none of the cores could accommodate
            //the task since they were busy. So, we
            //have no option but to schedule the task
            //on a new core.
            if (scheduled) continue;
            cores.add(task);
        }
        return cores.size();
    }
}
