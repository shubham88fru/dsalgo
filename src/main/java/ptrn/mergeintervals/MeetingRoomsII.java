package ptrn.mergeintervals;

import java.util.Arrays;
import java.util.PriorityQueue;

//@link - https://www.codingninjas.com/studio/problems/meeting-room-ii_893289?leftPanelTabValue=PROBLEM
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6375390508744704
//@tag - TO_REVISIT
/***
 * Directly copied the algo from edctv.
 */
public class MeetingRoomsII {
    public static int findSets(int[][] intervals) {

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
}
