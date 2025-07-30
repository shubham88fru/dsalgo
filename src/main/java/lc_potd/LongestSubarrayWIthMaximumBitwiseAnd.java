package lc_potd;

//@link - https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/
//@check - https://www.youtube.com/watch?v=xhKpOhtqAnM&t=307s&ab_channel=codestorywithMIK
public class LongestSubarrayWIthMaximumBitwiseAnd {
    public int longestSubarray(int[] nums) {
        /**
         Note that when and(ing) two (or more) nums,
         the result can not be more than the maximum of
         the and(ed) numbers. The best we can get is that
         all the and(ed) numbers are equal to the max among
         the number, in which case, the result will be equal
         to the max itself.

         As a rule of thumb --> Math.min(a, b) <= (a&b) <= Math.max(a, b)

         And therefore, this problem basically boils down
         to finding the maximum number of the array
         and then finding its longest chain. Even if this
         chain is just one length long, there can be no other
         subarray in the array whose `and` will be larger than
         the `and` of the subarray consisting of only the max num.
         */
        int max = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            max = Math.max(nums[i], max);
        }

        int len = 0;
        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == max) {
                len += 1;
            } else {
                len = 0;
            }
            maxLen = Math.max(len, maxLen);
        }

        return maxLen;
    }

    /* *
     My soln
     */
    private int revise(int[] nums) {
        int n = nums.length;
        int max = -1;
        for (int num: nums) max = Math.max(num, max);

        int maxLen = 1;
        int i=0;
        int j=0;
        while (i<n) {
            if (nums[i] == max) {
                while (j<n && nums[j] == max) j += 1;
                maxLen = Math.max(maxLen, j-i);
                i=j;
            } else {
                j += 1;
                i += 1;
            }

        }

        return maxLen;
    }
}
