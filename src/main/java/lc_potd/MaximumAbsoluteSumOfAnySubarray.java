package lc_potd;

// @link - https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
// @check - https://www.youtube.com/watch?v=hLx5BpqPbRw&ab_channel=codestorywithMIK
public class MaximumAbsoluteSumOfAnySubarray {
    public int maxAbsoluteSum(int[] nums) {
        return mikssol(nums);
    }

    /*
        Intuition by mik -
        1. Find the max subarray sum using kadane's algo.
        2. Find the min subarray sum using kadane's algo. (Imp: Can use kadane's to find min subarray sum too!)
        3. Take the max of absolute values of both.
    */
    private int mikssol(int[] nums) {
        int n = nums.length;

        //Mik's code for kadanes' algo.
        //appears more clean than strvr's.

        //finding max sub sum using kadanes'
        int currSubSum = nums[0];
        int maxSubSum = nums[0];
        for (int i=1; i<n; i++) {
            currSubSum = Math.max(nums[i], currSubSum+nums[i]);
            maxSubSum = Math.max(maxSubSum, currSubSum);
        }

        //finding min sub sum using kadanes'
        currSubSum = nums[0];
        int minSubSum = nums[0];
        for (int i=1; i<n; i++) {
            currSubSum = Math.min(nums[i], currSubSum+nums[i]);
            minSubSum = Math.min(minSubSum, currSubSum);
        }

        //No need to do two iterations for max and min.
        //We can do it in one iteration only, but took two
        //so it is more clear.

        //take max of absolute of maxsum and minsum.
        return Math.max(Math.abs(maxSubSum), Math.abs(minSubSum));
    }
}
