package lc_potd;

//@link - https://leetcode.com/problems/smallest-stable-index-ii/?
public class SmallestStableIndexII {
    public int firstStableIndex(int[] nums, int k) {
        return optimal(nums, k);
    }

    private int optimal(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        prefixMax[0] = nums[0];
        suffixMin[n-1] = nums[n-1];

        for (int i=1; i<n; i++) {
            prefixMax[i] = Math.max(nums[i], prefixMax[i-1]);
            suffixMin[n-1-i] = Math.min(nums[n-1-i], suffixMin[n-i]);
        }

        for (int i=0; i<n; i++) {
            if (prefixMax[i]-suffixMin[i] <= k) return i;
        }

        return -1;
    }
}
