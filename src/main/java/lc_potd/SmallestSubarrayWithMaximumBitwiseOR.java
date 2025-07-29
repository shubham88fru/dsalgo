package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/description/
//@check - https://www.youtube.com/watch?v=7ZOmJlTI2Z0&ab_channel=codestorywithMIK
public class SmallestSubarrayWithMaximumBitwiseOR {
    public int[] smallestSubarrays(int[] nums) {
        return mikssol(nums);
    }

    /*
        Coded by me based on
        mik's explanation.
        Slightly inefficient that
        mik's sol.
    */
    private int[] mikssol(int[] nums) {
        int n = nums.length;

        int[] setBits = new int[32]; //setBits[i] --> the index that can set the ith bit.
        Arrays.fill(setBits, -1);

        int[] ans = new int[n];
        Arrays.fill(ans, 1); //mik doesn't do this. Makes it a bit inefficient TC wise.
        for (int j=n-1; j>=0; j--) {
            for (int i=0; i<32; i++) {
                int bitVal = (nums[j] >> i) & 1;
                if (bitVal != 0) setBits[i] = j;
                ans[j] = Math.max(ans[j], setBits[i]-j+1);
            }
        }

        return ans;
    }
}
