package lc_cntst;

import java.util.Arrays;

//@link - https://leetcode.com/problems/minimum-average-of-smallest-and-largest-elements/
public class MinAvgOfSmallestAndLargestElements {
    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length-1;
        double min = Integer.MAX_VALUE;
        while (l < r) {
            double avg = (double) ((nums[l] + nums[r]) / 2.0);
            min = (double) Math.min(min, avg);

            l += 1;
            r -= 1;
        }

        return min;
    }
}
