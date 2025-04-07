package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/partition-equal-subset-sum/
//@strvr - https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5035587288694784
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num: nums) totalSum+=num;
        if (totalSum%2!=0) return false;

        return hasASubsetForWhichSumIsHalfTheSumOfEntireArray(nums, 0, totalSum/2, new HashMap<String, Boolean>());
        //return partition(nums, 0, 0, total, new HashMap<>());
    }

    //0) My top-down soln.
    private boolean revise(int[] nums) {
        int n = nums.length;
        int total = 0;

        for (int num: nums) total += num;
        if (total%2 != 0) return false;
        Boolean[][] memo = new Boolean[201][20001];
        return revise(nums, total/2, 0, memo);
    }

    private boolean revise(int[] nums, int target, int i, Boolean[][] memo) {
        if (target == 0) return true;
        if (i >= nums.length || target < 0) return false;

        if (memo[i][target] != null) return memo[i][target];

        boolean pick = revise(nums, target-nums[i], i+1, memo);
        boolean notPick = revise(nums, target, i+1, memo);

        memo[i][target] = pick || notPick;
        return memo[i][target];
    }

    //1) SWD solution
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

        memo.put(key, false);
        return memo.get(key);
    }

    //2) My Soln
    private boolean partition(int[] nums, int curr, int subsum, int total, Map<String, Boolean> cache) {
        if (subsum == total/2) return true;
        if (subsum > total/2) return false;
        if (curr >= nums.length) return false;

        String key = curr + "-" + subsum;
        if (cache.containsKey(key)) return cache.get(key);

        boolean pick = partition(nums, curr+1, subsum+nums[curr], total, cache);
        if (pick) {
            cache.put(key, true);
            return true;
        }
        boolean notPick = partition(nums, curr+1, subsum, total, cache);
        if (notPick) {
            cache.put(key, true);
            return true;
        }

        cache.put(key, false);
        return false;
    }

    //3) Check edctv/strvr for tabulation/bottom-up approach
}


