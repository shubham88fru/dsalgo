package ptrn.mergeintervals;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://www.codingninjas.com/studio/problems/meeting-room-ii_893289?leftPanelTabValue=PROBLEM
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6375390508744704
//         https://www.youtube.com/watch?v=FdzJmTCVyJU&t=512s&ab_channel=NeetCode
//@tag - TO_REVISIT
/***
 * Directly copied the algo from edctv.
 */
public class MeetingRoomsII {
    public static int findSets(int[][] intervals) {

        /*
        * Edctv's approach. Didn't understand need to revisit.
        * */
        Arrays.sort(intervals, (a1, a2) -> a1[0] - a2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);

        for (int i=1; i<intervals.length; i++) {
            if (pq.peek() <= intervals[i][0]) {
                pq.remove();
                pq.add(intervals[i][1]);
            } else pq.add(intervals[i][1]);
        }

        return pq.size();
    }

    /*
        Neetcode's approach. Didn't understand completely. Need to
        revisit.
     */

    /*
       This problems basically boils down
       to finding the maximum number of
       overlapping intervals. Coz,
       the max number number of overlapping
       intervals will be the meeting rooms required.
   */
    private int revise(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i=0; i<n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int max = 0;
        int p1 = 0;
        int p2 = 0;
        int rooms = 0;
        while (p1 < n && p2 < n) {
            if (start[p1] < end[p2]) { //new meeting started.
                p1 += 1;
                rooms += 1; //new meeting started.
                max = Math.max(max, rooms);
            } else { //a meeting ended.
                p2 += 1;
                rooms -= 1;
            }
        }

        return max;
    }

}
