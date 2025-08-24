package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
public class LongestSubarrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        // return pass1(nums);
        return brute(nums);
    }

    /**
     * My solns brute and sliding window.
     * Mik also showed a slightly different
     * sliding window approach.
     */
    private int brute(int[] nums) {
        int n = nums.length;

        int maxLen = 0;
        for (int i=0; i<n; i++) {
            int zc = 0;

            int j = i;
            while (j < n && (nums[j] == 1 || zc < 1)) {
                if (nums[j] == 0) zc += 1;
                j += 1;
            }
            maxLen = Math.max(j-i, maxLen);
        }

        return maxLen > 0 ? maxLen-1: 0;
    }

    private int pass1(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> window = new HashMap<>();
        int l = 0;
        int r = 0;
        int maxLen = 0;

        while (r < n) {
            while (r < n && (nums[r] == 1 || !window.containsKey(0))) {
                window.put(nums[r], window.getOrDefault(nums[r], 0)+1);
                maxLen = Math.max(maxLen, r-l+1);
                r += 1;
            }

            int rem = nums[l];
            window.put(rem, window.get(rem)-1);
            if (window.get(rem) == 0) window.remove(rem);
            l += 1;
        }

        return maxLen > 0 ? maxLen-1: 0;
    }
}
