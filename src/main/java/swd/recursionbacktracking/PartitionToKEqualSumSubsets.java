package swd.recursionbacktracking;

//@link - https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        /***SWD Soln ***/
        int totalSum = 0;

        for (int currentNum: nums) totalSum += currentNum;

        if (totalSum %k != 0) return false;

        return canPartition(nums, 0, 0, totalSum/k, k);
    }

    private boolean canPartition(int[] nums, int currentIndex, int currentSubsetSum, int targetSum, int k) {
        if (k==0) return true;

        if (currentSubsetSum == targetSum)
            return canPartition(nums, 0, 0, targetSum, k-1);

        for (int i=currentIndex; i<nums.length; i++) {
            if (nums[i] == 0 || currentSubsetSum + nums[i] > targetSum) continue;

            int temp = nums[i];
            nums[i] = 0;

            if (canPartition(nums, i+1, currentSubsetSum+temp, targetSum, k))
                return true;

            nums[i] = temp;
        }

        return false;
    }
}
