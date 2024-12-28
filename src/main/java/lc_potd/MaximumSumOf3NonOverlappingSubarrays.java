package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
//@check - https://www.youtube.com/watch?v=aGmHF7Rs0R8&t=2233s&ab_channel=codestorywithMIK

/**
 * This soln is coded by me but completely based on mik's explanation.
 * @check if in doubt.
 */
public class MaximumSumOf3NonOverlappingSubarrays {
    int[] ps;
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int rs = 0;
        int n = nums.length;
        ps = new int[n];
        ps[0] = nums[0];
        for (int i=1; i<n; i++) {
            ps[i] = ps[i-1] + nums[i];
        }

        List<Integer> ans = new ArrayList<>();
        Integer[][] cache = new Integer[n+1][4];

        dp(nums, ans, k, 0, 3, cache);

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private void dp(int[] nums, List<Integer> ans, int k, int curr, int count, Integer[][] cache) {
        if (count <= 0) return;
        if (curr >= nums.length) return;

        int pick = getSum(curr-1, curr+k-1) + helper(nums, k, curr+k, count-1, cache);
        int notPick = helper(nums, k, curr+1, count, cache);

        if (pick >= notPick) {
            ans.add(curr);
            dp(nums, ans, k, curr+k, count-1, cache);
        } else {
            dp(nums, ans, k, curr+1, count, cache);
        }
    }

    private int getSum(int start, int end) {
        if (end >= ps.length) return 0; //invalid so return 0

        if (start < 0) return ps[end]; //tricky to come up.
        return (ps[end] - ps[start]);
    }

    private int helper(int[] nums, int k, int curr, int count, Integer[][] cache) {
        if (count <= 0) return 0;
        if (curr >= nums.length) return Integer.MIN_VALUE;

        if (cache[curr][count] != null) return cache[curr][count];
        int pick = getSum(curr-1, curr+k-1) + helper(nums, k, curr+k, count-1, cache);
        int notPick = helper(nums, k, curr+1, count, cache);

        cache[curr][count] = Math.max(pick, notPick);
        return cache[curr][count];
    }
}
