package lc_potd;

//@link - https://leetcode.com/problems/maximum-difference-between-increasing-elements/description/
public class MaximumDifferenceBetweenIncreasingElements {
    public int maximumDifference(int[] nums) {
        return revise2(nums);
        // return revise(nums);
    }

    //My approach 1
    private int revise2(int[] nums) {
        int n = nums.length;
        int lg = nums[n-1];
        int maxDiff = -1;
        for (int i=n-2; i>=0; i--) {
            if (nums[i] >= lg) {
                lg = nums[i];
            } else maxDiff = Math.max(maxDiff, lg-nums[i]);
        }

        return maxDiff;
    }

    //My approach 2
    private int revise(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int diff = -1;

        for (int i=0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }

            if (nums[i] > min) {
                max = nums[i];
                diff = Math.max(diff, max-min);
            }
        }

        return diff;
    }
}
