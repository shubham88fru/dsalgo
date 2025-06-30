package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/longest-harmonious-subsequence/description/
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        return pass1(nums);
    }

    /*
        My sliding window approach.
        Mik's approach was different,
        used extra space but linear TC.
        (Check for multiple approaches)
    */
    private int pass1(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        int l = 0;
        int r = 1;

        int max = Integer.MIN_VALUE;
        int maxLen = 0;
        while (r < n) {
            int min = nums[l];

            while (r < n && (Math.max(max, nums[r])-min <= 1)) {
                if (Math.max(max, nums[r])-min == 1) maxLen = Math.max(maxLen, r-l+1); //TC: [15, 3, 18, 10, 19, 19, 16, 11, 15, 16]
                r += 1;

            }
            l += 1;
            if (r == l) r += 1;
        }
        return maxLen;
    }
}
