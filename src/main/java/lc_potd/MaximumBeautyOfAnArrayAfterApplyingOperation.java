package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/
//@check - https://www.youtube.com/watch?v=VrFolxiw_yQ&t=1706s&ab_channel=codestorywithMIK
//         https://www.youtube.com/watch?v=FdzJmTCVyJU&t=512s&ab_channel=NeetCode
public class MaximumBeautyOfAnArrayAfterApplyingOperation {
    public int maximumBeauty(int[] nums, int k) {
        return miksapproach1(nums, k);
    }

    /*
        Mik's first approach, he showed other approaches
        as well. @check if this is a recurring problem for
        some company.
    */
    private int miksapproach1(int[] nums, int k) {
        int n = nums.length;

        //Find the bounds for each num.
        List<int[]> ranges = new ArrayList<>();
        for (int num: nums) {
            ranges.add(new int[] { num-k, num+k });
        }

        ///////////////////////////
        /*
            1. Following is my approach to find the
            max number of overlapping intervals, based
            on mik's approach. This works for this question on lc,
            but apparently the approach is wrong and doesn't pass
            test cases on gfg and lintcode for the classic max overlapping
            intervals problem.

            Probably the test case for this problem is weak.
        */
        //sort the intervals.
        // Collections.sort(ranges, (a1, a2) -> a1[0]-a2[0]);

        //at this point, the problem basically becomes
        //find the max number of overlapping intervals.
        // int i = 0;
        // int j = 1;
        // int maxSz = 1;
        // while (j < ranges.size()) {
        //     if (ranges.get(j)[0] <= ranges.get(i)[1]) {
        //         maxSz = Math.max(maxSz, j-i+1);
        //         j += 1;
        //     } else {
        //         i += 1;
        //     }
        // }

        /*
            2. Mik's approach to find max overlapping intervals,
            works on lc, but doesn't work on gfg and lintcode.
        */
        // int maxSz = 1;
        // Deque<int[]> q = new ArrayDeque<>();
        // q.addLast(new int[] {ranges.get(0)[0], ranges.get(0)[1]});
        // for (int j=1; j < ranges.size(); j++) {
        //     while (!q.isEmpty() && (ranges.get(j)[0] > q.peekFirst()[1])) {
        //         q.removeFirst();
        //     }
        //     q.addLast(ranges.get(j));
        //     maxSz = Math.max(maxSz, q.size());
        // }
        ///////////////////////////

        // return maxSz;


        /*
            3. Neetcode's approach to finding max overlapping
            intervals. Works on all platforms.
        */
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];
        for (int i=0; i<n; i++) {
            startTimes[i] = ranges.get(i)[0];
            endTimes[i] = ranges.get(i)[1];

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
