package swd.dp;

//@link - https://leetcode.com/problems/target-sum/
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        return waysForTargetSum(nums, target, 0);
    }

    //1) With memoization.
    private int revise(int[] nums, int target) {
        int n = nums.length;

        Integer[][] dp = new Integer[21][3000];
        return dp(nums, target, 0, dp);
    }

    private int dp(int[] nums, int target, int i, Integer[][] dp) {
        if (i >= nums.length && target == 0) return 1;
        if (i >= nums.length) return 0;

        if (dp[i][target+1000] != null) return dp[i][target+1000];
        int plus = dp(nums, target-nums[i], i+1, dp);
        int minus = dp(nums, target+nums[i], i+1, dp);

        dp[i][target+1000] = plus + minus;
        return dp[i][target+1000];
    }

    //2) Without memoization
    private int waysForTargetSum(int[] nums, int target, int currIndex) {

        //target sum should be achieved only after considering the last element.
        //Because it is possible that the target sum becomes zero midway -> but that isn't a valid soln.
        if (target == 0 && (currIndex==nums.length)) return 1;
        if (currIndex >= nums.length) return 0;

        int withPlusSignToCurrNum = waysForTargetSum(nums, target-nums[currIndex], currIndex+1);

        int withNegativeSignToCurrNum = waysForTargetSum(nums, target+nums[currIndex], currIndex+1);

        return withPlusSignToCurrNum + withNegativeSignToCurrNum;
    }
}
