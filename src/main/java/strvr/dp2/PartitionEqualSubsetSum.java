package strvr.dp2;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/partition-equal-subset-sum/
//@strvr - https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num: nums) totalSum+=num;
        if (totalSum%2!=0) return false;

        return hasASubsetForWhichSumIsHalfTheSumOfEntireArray(nums, 0, totalSum/2, new HashMap<String, Boolean>());
    }

    private boolean hasASubsetForWhichSumIsHalfTheSumOfEntireArray(int[] nums, int currIndex, int targetSum, Map<String, Boolean> memo) {

        if (targetSum == 0) return true;

        if (currIndex >= nums.length) return false;

        String key = currIndex + "-" + targetSum;

        if (memo.containsKey(key)) return memo.get(key);

        boolean putCurrentItemInSubset = false;
        if (nums[currIndex]<=targetSum) {
            putCurrentItemInSubset
                    = hasASubsetForWhichSumIsHalfTheSumOfEntireArray(nums, currIndex+1, targetSum-nums[currIndex], memo);

            if (putCurrentItemInSubset) return true;
        }


        boolean skipCurrentItem
                = hasASubsetForWhichSumIsHalfTheSumOfEntireArray(nums, currIndex+1, targetSum, memo);

        if (skipCurrentItem) return true;

        memo.put(key, putCurrentItemInSubset || skipCurrentItem);

        return memo.get(key);
    }
}


