package lc_potd;

//@link - https://leetcode.com/problems/number-of-ways-to-split-array/
public class NumberOfWaysToSplitArray {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long[] rs = new long[n];

        for (int i=0; i<n; i++) {
            if (i==0) rs[0] = nums[0];
            else rs[i] = nums[i] + rs[i-1];
        }

        int split = 0;
        for (int i=0; i<n-1; i++) {
            if (rs[i] >= (rs[n-1] - rs[i])) split += 1;
        }

        return split;
    }
}
