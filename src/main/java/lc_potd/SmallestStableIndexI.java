package lc_potd;

//@link - https://leetcode.com/problems/smallest-stable-index-i/?
public class SmallestStableIndexI {
    public int firstStableIndex(int[] nums, int k) {
        return brute(nums, k);
    }

    private int brute(int[] nums, int k) {
        int n = nums.length;
        for (int i=0; i<n; i++) {
            int max = max(nums, 0, i);
            int min = min(nums, i);

            if (max-min <= k) return i;
        }

        return -1;
    }

    private int max(int[] nums, int l, int r) {
        int max = Integer.MIN_VALUE;
        for (int i=l; i<=r; i++) {
            max = Math.max(nums[i], max);
        }

        return max;
    }

    private int min(int[] nums, int l) {
        int min = Integer.MAX_VALUE;
        for (int i=l; i<nums.length; i++) {
            min = Math.min(min, nums[i]);
        }

        return min;
    }
}
