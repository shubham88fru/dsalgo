package strvr.dp;

import java.util.Arrays;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-increasing-subsequence/description/
//@strvr - https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        return bottomUp(nums);
    }

    private int bottomUp(int[] nums) {
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
        Had to do few improvisations to have the top-down
        approach work.
        Imp:
            - Use 2d array for DP/memo instead of Map.
            - Use prevIdx in recursion instead of the prev element itself.
              constrains for prevIdx are far smaller than the constraints
              for prev num, so it can easily fit the dp/memo array.
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
