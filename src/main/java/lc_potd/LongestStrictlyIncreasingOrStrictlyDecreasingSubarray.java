package lc_potd;

//@link - https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/
public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    public int longestMonotonicSubarray(int[] nums) {
        // return suboptimal(nums);
        return optimal(nums);
    }

    //1) One pass solution.
    private int optimal(int[] nums) {
        int n = nums.length;

        int incLen = 1;
        int maxIncLen = 1;

        int decLen = 1;
        int maxDecLen = 1;
        for (int i=1; i<n; i++) {
            if (nums[i] > nums[i-1]) {
                incLen += 1;
                maxIncLen = Math.max(maxIncLen, incLen);
            } else {
                incLen = 1;
            }

            if (nums[i] < nums[i-1]) {
                decLen += 1;
                maxDecLen = Math.max(maxDecLen, decLen);
            } else {
                decLen = 1;
            }
        }


        return Math.max(maxDecLen, maxIncLen);
    }

    //2) 2 pass solution.
    private int suboptimal(int[] nums) {
        int n = nums.length;

        int incLen = 1;
        int maxIncLen = 1;
        for (int i=1; i<n; i++) {
            if (nums[i] > nums[i-1]) {
                incLen += 1;
                maxIncLen = Math.max(maxIncLen, incLen);
            } else {
                incLen = 1;
            }
        }

        int decLen = 1;
        int maxDecLen = 1;
        for (int i=1; i<n; i++) {
            if (nums[i] < nums[i-1]) {
                decLen += 1;
                maxDecLen = Math.max(maxDecLen, decLen);
            } else {
                decLen = 1;
            }
        }


        return Math.max(maxDecLen, maxIncLen);
    }
}
