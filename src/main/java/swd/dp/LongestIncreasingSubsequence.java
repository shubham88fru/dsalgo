package swd.dp;

import java.util.Arrays;

//@link - https://leetcode.com/problems/longest-increasing-subsequence/description/
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
}
