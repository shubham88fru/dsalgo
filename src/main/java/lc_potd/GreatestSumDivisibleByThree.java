package lc_potd;

//@link - https://leetcode.com/problems/greatest-sum-divisible-by-three/?
public class GreatestSumDivisibleByThree {
    public int maxSumDivThree(int[] nums) {
        return pass2(nums);
    }

    private int pass2(int[] nums) {

        Integer[][] memo = new Integer[nums.length+1][3];

        return dp2(nums, 0, 0, memo);
    }

    private int dp2(int[] nums, int i, int sum, Integer[][] memo) {
        int mod = sum%3;
        if (i >= nums.length) {
            if (mod == 0) return 0;
            return -100000000;
        }

        if (memo[i][mod] != null) return memo[i][mod];

        int pick = nums[i] + dp2(nums, i+1, sum+nums[i], memo);
        int nPick = dp2(nums, i+1, sum, memo);

        memo[i][mod] = Math.max(pick, nPick);
        return memo[i][mod];
    }
}
