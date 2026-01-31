package ptrn.mergeintervals;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://www.codingninjas.com/studio/problems/meeting-room-ii_893289?leftPanelTabValue=PROBLEM
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6375390508744704
//         https://www.youtube.com/watch?v=FdzJmTCVyJU&t=512s&ab_channel=NeetCode
//@tag - TO_REVISIT
public class MeetingRoomsII {
    public int findSets(int[][] intervals) {
//        return edctvSol(intervals);
        return revise(intervals);
    }

    private int edctvSol(int[][] intervals) {
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
       the max number of overlapping
       intervals will be the meeting rooms required.
   */
    private int revise(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i=0; i<n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;
        int minRooms = 0;
        int i=0, j=0;

        while (i < n && j < n) {
            if (start[i] < end[j]) {
                i += 1;
                rooms += 1;
                minRooms = Math.max(minRooms, rooms);
            } else {
                j += 1;
                rooms -= 1;
            }
        }

        return minRooms;
    }

}
