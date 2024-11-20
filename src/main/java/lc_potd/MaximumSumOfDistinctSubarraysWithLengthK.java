package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
public class MaximumSumOfDistinctSubarraysWithLengthK {
    public long maximumSubarraySum(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        int l = 0;
        int r = 0;
        int n = nums.length;

        long maxSum = Long.MIN_VALUE;
        long subSum = 0;
        while (r < n) {
            if ((r-l+1 <= k) && (!window.contains(nums[r]))) {
                window.add(nums[r]);
                subSum += nums[r];
                r += 1;
            }  else if (r-l+1 > k) {
                // System.out.println("L: " + l + " R: " + r);
                maxSum = Math.max(maxSum, subSum);
                subSum -= nums[l];
                window.remove(nums[l]);
                l += 1;
            } else {
                subSum -= nums[l];
                window.remove(nums[l]);
                l += 1;
            }
        }

        //the last window problem with my
        //approach.
        if (r-l+1 > k) {
            maxSum = Math.max(maxSum, subSum);
        }

        return maxSum == Long.MIN_VALUE ? 0: maxSum;
    }
}
