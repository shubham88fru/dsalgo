package ptrn.twoheaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//@link - https://www.codingninjas.com/studio/problems/cpu-task-scheduler_2248081?leftPanelTabValue=SUBMISSION
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5234130054021120
public class ScheduleTasksOnMinimumMachines {
    public static int cpuTaskScheduler(int n, int[][] arr){
        // Write your code here.
        return kindaBrute(n, arr);
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
