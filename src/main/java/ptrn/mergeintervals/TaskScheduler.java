package ptrn.mergeintervals;

import java.util.*;

//@link - https://leetcode.com/problems/task-scheduler/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4580085862236160
//       - https://www.youtube.com/watch?v=s8p8ukTyA2I&t=688s&ab_channel=NeetCode
public class TaskScheduler {
    //T: O(N), S: O(1)
    public int leastInterval(char[] tasks, int n) {
        return usingHeap(tasks, n);
        //return edctvSoln(tasks, n);
    }

    //0) nc soln using max heap. optimal.


    //1) My soln using minHeap.
    private int usingHeap(char[] tasks, int n) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.t - p2.t);

        int[] minTime = new int[26];
        for (int i=0; i<tasks.length; i++) {
            char ch = tasks[i];
            if (minTime[ch-'A'] == 0) {
                minTime[ch-'A'] += 1;
            } else {
                minTime[ch-'A'] += 1 + n;
            }
            pq.add(new Pair(tasks[i], minTime[ch-'A']));

        }

        int t = 0;
        while (!pq.isEmpty()) {
            if (t < pq.peek().t) {
                t += (pq.peek().t - t);
            } else {
                t += 1;
            }

            pq.remove();
        }

        return t;
    }


    //2) edctv soln using interval
    private int edctvSoln(char[] tasks, int n) {
        /**
         The optimal strategy to solve this problem is to
         schedule the most frequent task first, then the second most
         frequency task, ans so on. This is because once we've scheudled the most
         frequent task, we can get an estimate of the maximum idles time.

         The idea is that we'll figure out a plan first for the task that has the highest
         frequncy, this plan will include the times when this task will be scheduled and all
         the gaps between their scheduling accounting for the cooldown rule. We'll then iteratively
         keep selecting lower frequency taks and try to schedule then in the gaps (whilst stll respecting
         the cooldown rules) so as to reduce the slots (i.e. idleTime)
         At a given point, idleTime represents the empty slots.

         We'll iteratively keep updating the `idleTime`.
         (Refer edctv for more details of the equations below)
         For the task with highest frequency -
         idleTime = (maxFreq - 1) * n;
         while, for all other tasks -
         idleTime = idleTime - Math.min(maxFreq - 1, currFreq);
         **/

        //estimate the frequencies of the tasks.
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char ch: tasks) {
            frequencies.put(ch, frequencies.getOrDefault(ch, 0)+1);
        }

        //sort the frequencies (values in map)
        List<Map.Entry<Character, Integer>> sortedFrequencies
                = new ArrayList<>(frequencies.entrySet());
        Collections.sort(sortedFrequencies, (ent1, ent2) -> ent1.getValue().compareTo(ent2.getValue()));

        //get the max frequency
        int maxFreq = sortedFrequencies.get(sortedFrequencies.size()-1).getValue();
        sortedFrequencies.remove(sortedFrequencies.size()-1); //no longer the max for next iteration.

        //idleTime for the task with max frequency.
        int idleTime = (maxFreq - 1) * n;

        //iterate over the sortedFrequencies and update the idleTime
        while (!sortedFrequencies.isEmpty()) {
            int currMaxFreq = sortedFrequencies.get(sortedFrequencies.size()-1).getValue();
            //idleTime equations for all other tasks other than the
            //task with maximum frequency.
            idleTime -= Math.min(maxFreq - 1, currMaxFreq);
            sortedFrequencies.remove(sortedFrequencies.size()-1);
        }

        //to handle case if idleTime goes -ve for some reason
        //Still, not sure why edctv soln has it though.
        idleTime = Math.max(0, idleTime);

        //min time will be atleast the time to execute each task plus the gaps (idleTimes) used, if any.
        return tasks.length + idleTime;
    }
}

class Pair {
    char ch;
    int t;

    Pair(char ch, int t) {
        this.ch = ch;
        this.t = t;
    }
}