package ptrn.greedy;

import java.util.Collections;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-number-of-refueling-stops/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4586813794287616
public class MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int currFuel = startFuel;

        //the car already has sufficient fuel to
        //start with. No stops needed.
        if (currFuel >= target) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        int n = stations.length;
        int stops = 0;
        int maxDistance = startFuel;

        while (maxDistance < target) {
            if (i < n && stations[i][0] <= maxDistance) {
                maxHeap.add(stations[i][1]);
                i += 1;
            } else if (maxHeap.isEmpty()) {
                return -1;
            } else {
                maxDistance += maxHeap.remove();
                stops += 1;
            }
        }

        return stops;
    }
}
