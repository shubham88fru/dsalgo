package lc_potd;

import java.util.Arrays;

//@link - https://www.geeksforgeeks.org/problems/intersecting-intervals/1
//@check - https://www.youtube.com/watch?v=FdzJmTCVyJU&t=512s&ab_channel=NeetCode
public class MaximumNumberOfOverlappingIntervals {
    public static int overlap(int n, int[][] ranges) {
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i=0; i<n; i++) {
            startTimes[i] = ranges[i][0];
            endTimes[i] = ranges[i][1];

        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int max = 0;
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < n && j < n) {
            if (startTimes[i] > endTimes[j]) {
                count -= 1;
                j += 1;
            } else {
                count += 1;
                i += 1;
            }
            max = Math.max(count, max);

        }

        return max == 0 ? 1: max;
    }
}
