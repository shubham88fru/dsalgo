package strvr.dp;

import java.util.Arrays;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-increasing-subsequence/description/
//@strvr - https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
public class LongestIncreasingSubsequence {

    /*
        The top-down approach (recursion) gives TLE for this problem.
        And so, this needs to be solved using the bottom-up (tabulation)
        approach only.

        Kind of nasty to understand, so rewatch videos of swd or on
        youtube for explanation.
     */
    public int lengthOfLIS(int[] nums) {
        int answer = 1;
        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        return answer;
    }

    /*
        Top-down DP. Gives TLE.
     */
    private int revise(int[] nums, int curr, int prev, Map<String, Integer> memo) {
        if (curr >= nums.length) return 0;

        String key = curr + "_" + prev;
        if (memo.containsKey(key)) return memo.get(key);

        int pick = 0;
        if (nums[curr] > prev || prev == Integer.MIN_VALUE) {
            pick = 1 + revise(nums, curr+1, nums[curr], memo);
        }

        int noPick = revise(nums, curr+1, prev, memo);

        memo.put(key, Math.max(pick, noPick));

        return memo.get(key);
    }
}
