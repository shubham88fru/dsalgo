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
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i=0; i<n; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;

        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int max = 0;
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < n && j < n) {
            if (startTimes[i] >= endTimes[j]) {
                count -= 1;
                j += 1;
            } else {
                count += 1;
                i += 1;
            }
            max = Math.max(count, max);

        }

        return max;
    }

}
