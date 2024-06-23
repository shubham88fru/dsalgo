package lc_cntst;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximize-total-cost-of-alternating-subarrays/description/
public class MaximizeTotalCostOfAlternatingSubarrays {
    public long maximumTotalCost(int[] nums) {

        int[][] memo = new int[2][nums.length];
        return sol2(nums, 0, 0, new HashMap<>()); //start with no flip.

        /**
         long[] maxi = {0};
         sol1(nums, 0, 1, 0, maxi);
         return maxi[0];
         */
    }

    //To down dp soln, eazier to memoize.
    private long sol2(int[] nums, int flip, int curr, Map<String, Long> memo) {
        if (curr >= nums.length) return 0;

        String key = flip + "-" + curr;
        if (memo.containsKey(key)) return memo.get(key);

        //keep including in current group.
        long includeCurr = (long)(((flip == 1) ? -1 : 1)*nums[curr])
                + sol2(nums, 1-flip, curr+1, memo);

        //when not including, we break free from the alternate flips.
        //and start afresh.
        long dontIncludeCurr = (long)nums[curr]
                + sol2(nums, 1, curr+1, memo);

        memo.put(key, Math.max(includeCurr, dontIncludeCurr));
        return memo.get(key);
    }

    //This one will give TLE coz its not memoized.
    //It's kinda difficult to memoize when the soln is written this way,
    //so I converted it to the above sol2 which is straightforward to memoize.
    private void sol1(int[] nums, long sum, int flip, int curr, long[] maxi) {
        if (curr >= nums.length) {
            maxi[0] = Math.max(maxi[0], sum);
            return;
        }

        sol1(nums, sum+(long)(((flip == 1) ? -1 : 1)*nums[curr]), 1-flip, curr+1, maxi);
        sol1(nums, sum+(long)nums[curr], 1, curr+1, maxi);
    }
}
