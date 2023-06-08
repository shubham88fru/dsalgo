package swd.dp;

//@link - https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class MaximumSumIncreasingSubsequence {
    public int maxSumIS(int nums[], int n) {
        int answer = nums[0];

        int[] dp = new int[n];
        System.arraycopy(nums, 0, dp, 0, n);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+nums[i]);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        return answer;
    }
}
