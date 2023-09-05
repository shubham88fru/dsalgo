package strvr.greedy;

import java.util.Arrays;

//@link - https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1#
//@strvr - https://takeuforward.org/data-structure/job-sequencing-problem/
public class JobSequencingProblem {
    int[] JobScheduling(Job arr[], int n) {
        // Your code here
        return scheduleJobsGreedy(arr, n);
    }

    private int[] scheduleJobsGreedy(Job[] jobs, int n) {
        //Sort wrt max cost job first.
        //then greedily start scheudling the jobs to the latest position (i.e. deadline)
        //so that max possible jobs can be scheduled between 0 to deadline.
        Arrays.sort(jobs, (j1, j2) -> j2.profit-j1.profit);
        int maxDeadline = -1;
        for (Job jb: jobs) {
            if (jb.deadline > maxDeadline) {
                maxDeadline = jb.deadline;
            }
        }

        int[] schedules = new int[maxDeadline+1];
        Arrays.fill(schedules, -1);
        int maxCost = 0;
        int scheduled = 0;
        for (int i=0; i<n; i++) {
            for (int j=jobs[i].deadline; j>0; j--) {
                //if some job already scheduled,
                //take a step back and try to schedule it
                //earlier.
                if (schedules[j] == -1) {
                    schedules[j] = i;
                    maxCost += jobs[i].profit;
                    scheduled += 1;
                    break;
                }
            }
        }

        return new int[]{scheduled, maxCost};

    }
}
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}
