package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximum-erasure-value/
public class MaximumErasureValue {
    public int maximumUniqueSubarray(int[] nums) {
        return pass1(nums);
    }

    /*
    * My simple sliding window approach.
    * One optimization that can be done is
    * to directly move l to nums[r]+1 when
    * duplicate is found.
    * Mik had the same approach.
    * */
    private int pass1(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        int maxSum = 0;
        int sum = 0;
        Map<Integer, Integer> mp = new HashMap<>();

        while (r < n) {
            while (r < n && (!mp.containsKey(nums[r]) || mp.get(nums[r]) == 0)) {
                sum += nums[r];
                mp.put(nums[r], mp.getOrDefault(nums[r], 0)+1);
                r += 1;
                maxSum = Math.max(maxSum, sum);
            }

            sum -= nums[l];
            mp.put(nums[l], mp.get(nums[l])-1);
            l += 1;
        }

        return maxSum;
    }
}
