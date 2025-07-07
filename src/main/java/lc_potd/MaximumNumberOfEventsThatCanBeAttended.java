package lc_potd;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
//@check - https://www.youtube.com/watch?v=7xa6JIuxAOo&ab_channel=codestorywithMIK
public class MaximumNumberOfEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        // return pass1(events);
        return pass2(events);
    }

    /*
        Took hint from mik and editorial.
        Idea is to first get all the events that
        can be attended on a given day and attend the
        one that ends earliest.
    */
    private int pass2(int[][] events) {
        int n = events.length;
        int maxDay = -1;
        for (int[] event: events) {
            maxDay = Math.max(event[1], maxDay);
        }

        int count = 0;
        Arrays.sort(events, (e1, e2) -> e1[0]-e2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int day=1, idx=0; day<=maxDay; day+=1) {

            //collect all (strategically using pq) that can be attended.
            while (idx<n && events[idx][0] <= day) {
                pq.add(events[idx][1]);
                idx += 1;
            }

            //May happen that some events in queue are past their end date.
            //can't attend them anymore.
            while (!pq.isEmpty() && pq.peek() < day) pq.remove();

            if (!pq.isEmpty()) {
                pq.remove(); //attend the earliest ending event.
                count += 1;
            }
        }

        return count;
    }

    /*
        My initial approach. Was on the right track of solving day-by-day,
        and keep attending the one that ends the earliest.
        But this soln won't work in all cases.
        e.g. failing case - [[1,5],[1,5],[1,5],[2,3],[2,3]]; expected 5 but got 3.

    */
    private int pass1(int[][] events) {
        int n = events.length;
        Comparator<int[]> cmp1 = (e1, e2) -> e1[0] - e2[0];
        Comparator<int[]> cmp2 = (e1, e2) -> e1[1] - e2[1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(cmp1.thenComparing(cmp2));
        for (int i=0; i<n; i++) {
            pq.add(events[i]);
        }

        int day = 1;
        int count = 0;
        while (!pq.isEmpty()) {
            int[] event = pq.remove();
            if (day >= event[0] && day <= event[1]) {
                count += 1;
            } else if (day < event[0]) {
                pq.add(event);
            }
            day += 1;
        }

        return count;
    }
}
