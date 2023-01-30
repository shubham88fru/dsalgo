package swd.dp;

//@link - https://leetcode.com/problems/target-sum/
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        return waysForTargetSum(nums, target, 0);
    }

    private int waysForTargetSum(int[] nums, int target, int currIndex) {

        //target sum should be acheived only after considering the last element.
        //Becuase it is possible that the target sum becomes zero midway -> but that isn't a valid soln.
        if (target == 0 && (currIndex==nums.length)) return 1;
        if (currIndex >= nums.length) return 0;

        int withPlusSignToCurrNum = waysForTargetSum(nums, target-nums[currIndex], currIndex+1);

        int withNegativeSignToCurrNum = waysForTargetSum(nums, target+nums[currIndex], currIndex+1);

        return withPlusSignToCurrNum + withNegativeSignToCurrNum;
    }
}
