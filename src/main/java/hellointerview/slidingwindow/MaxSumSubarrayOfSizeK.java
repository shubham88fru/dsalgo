package hellointerview.slidingwindow;

//@link - https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k
public class MaxSumSubarrayOfSizeK {
    public int maxSum(int[] nums, int k) {
        return optimal(nums, k);
    }

    private int optimal(int[] nums, int k) {
        int n = nums.length;
        int l=0, r=0, rs = 0, maxSum = Integer.MIN_VALUE;
        while (r < n) {
            while (r < n && r-l < k) {
                rs += nums[r];
                r += 1;
            }

            maxSum = Math.max(maxSum, rs);

            rs -= nums[l];
            l += 1;
        }

        return maxSum;
    }
}
