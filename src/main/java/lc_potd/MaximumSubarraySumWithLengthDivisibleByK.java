package lc_potd;

//@link - https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/description/?
//@check - https://www.youtube.com/watch?v=pPxqRNyUbIs
public class MaximumSubarraySumWithLengthDivisibleByK {
    public long maxSubarraySum(int[] nums, int k) {
        return mikssol(nums, k);
    }

    /**
     * Coded entirely by me based on
     * mik's explanation.
     *
     * @param nums
     * @param k
     * @return
     */
    private long mikssol(int[] nums, int k) {
        int n = nums.length;

        long[] ps = new long[n];
        ps[0] = nums[0];
        for (int i=1; i<n; i++) {
            ps[i] = nums[i] + ps[i-1];
        }

        long maxSum = Long.MIN_VALUE;
        for (int i=0; i<k; i++) {//try for k-1 boxes

            int l = i;
            int r = l+k-1;
            long sum = 0;
            //Standard kadane's
            //algorithm boxwise.
            while (r < n) {

                sum += (ps[r] - (l==0?0: ps[l-1]));
                if (sum > maxSum) maxSum = sum;

                if (sum < 0) sum = 0;

                l += k; //next box.
                r = l+k-1;
            }
        }

        return maxSum;
    }
}
