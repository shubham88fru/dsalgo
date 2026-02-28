package lc_potd;

//@link - https://leetcode.com/problems/maximum-average-subarray-i/
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        return revise(nums, k);
    }

    private double revise(int[] nums, int k) {
        int n = nums.length;

        int l=0, r=0;
        double avg = Long.MIN_VALUE;
        double sum = 0.0;
        while (r < n) {
            while (r < n && r-l < k) {
                sum += nums[r];
                r += 1;
            }
            avg = Math.max(avg, (sum*1.0)/k);

            sum -= nums[l];
            l += 1;
        }

        return avg;
    }
}
