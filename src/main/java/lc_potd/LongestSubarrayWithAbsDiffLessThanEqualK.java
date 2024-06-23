package lc_potd;

import java.util.TreeMap;

//@link - https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/
public class LongestSubarrayWithAbsDiffLessThanEqualK {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int n = nums.length;
        int l = 0;
        int r = 0;
        int maxi = 0;

        /**
         Idea is to use sliding window. Keep track of max and min
         in the window coz if diff between max and min is smaller than
         the limit than every other pair's diff will also be smaller.
         */
        while (r < n) {
            if (tm.isEmpty()) { //If map is empty, just acquire.
                tm.put(nums[r], tm.getOrDefault(nums[r], 0)+1);
                maxi = Math.max(maxi, r-l+1);
                r += 1;
            } else if (nums[r] >= tm.firstKey() && nums[r] <= tm.lastKey()) { //If new el is between min and max.
                tm.put(nums[r], tm.getOrDefault(nums[r], 0)+1);
                maxi = Math.max(maxi, r-l+1);
                r += 1;
            } else if (nums[r] < tm.firstKey() && //If new el is smaller than min, then its addition to window may make it invalid.
                    Math.abs(tm.lastKey() - nums[r]) <= limit
            ) {
                tm.put(nums[r], tm.getOrDefault(nums[r], 0)+1);
                maxi = Math.max(maxi, r-l+1);
                r += 1;
            } else if (nums[r] > tm.lastKey() &&  //If new el is larger than max, then its addition to window may make it invalid.
                    Math.abs(nums[r] - tm.firstKey()) <= limit
            ) {
                tm.put(nums[r], tm.getOrDefault(nums[r], 0)+1);
                maxi = Math.max(maxi, r-l+1);
                r += 1;
            } else { //If the new el makes the window invalid, time to release from left end.
                int toRemove = nums[l];
                tm.put(toRemove, tm.get(toRemove)-1);
                if (tm.get(toRemove) == 0) tm.remove(toRemove);
                l += 1;
            }

        }

        return maxi;
    }
}
