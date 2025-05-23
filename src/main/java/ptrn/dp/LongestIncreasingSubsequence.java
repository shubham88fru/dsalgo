package ptrn.dp;

import java.util.Arrays;

//@link - https://leetcode.com/problems/longest-increasing-subsequence/description/
//@strvr - https://www.youtube.com/watch?v=h9rm4N8XbL0&t=1720s&ab_channel=codestorywithMIK
//       - https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
//       - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6263849939632128
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        Integer[][] memo = new Integer[2501][2501];
//        return topdown(nums, 0, -1, memo);
        return bottomUp(nums);
    }

    //1) bottom up approach.
    //T: O(n^2)
    private int bottomUp(int[] nums) {
        int answer = 1;
        int n = nums.length;

        int[] dp = new int[n]; //dp[i] denotes the length of LIS at index i.
        Arrays.fill(dp, 1); //initially LIS at each index is 1 (the curr number itself)

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
    private int topdown(int[] nums, int curr, int prevIdx, Integer[][] memo) {
        if (curr >= nums.length) return 0;

        if (prevIdx != -1 && memo[curr][prevIdx] != null) return memo[curr][prevIdx];

        int pick = 0;
        if (prevIdx == -1 || nums[curr] > nums[prevIdx]) {
            pick = 1 + topdown(nums, curr+1, curr, memo);
        }

        int noPick = topdown(nums, curr+1, prevIdx, memo);

        if (prevIdx != -1) memo[curr][prevIdx] = Math.max(pick, noPick);

        return Math.max(pick, noPick);
    }
}
