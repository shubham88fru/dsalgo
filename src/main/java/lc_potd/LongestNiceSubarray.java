package lc_potd;

//@link - https://leetcode.com/problems/longest-nice-subarray/description/
public class LongestNiceSubarray {
    public int longestNiceSubarray(int[] nums) {
        // return brute(nums);
        // return optimalWithMySlidingWindowTemplate(nums);
        return optimalWithMiksSlidingWindowTemplate(nums);
    }

    //T: O(N)
    private int optimalWithMiksSlidingWindowTemplate(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = 0;

        int mask = 0;
        int maxLen = 1;

        while (r < n) {
            int curr = nums[r];

            while ((mask & nums[r]) != 0) { //keep shrinking till invalid.
                mask ^= nums[l];
                l += 1;
            }

            maxLen = Math.max(maxLen, r-l+1);
            mask |= nums[r];
            r += 1;
        }

        return maxLen;
    }

    //T: O(N)
    private int optimalWithMySlidingWindowTemplate(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = 0;

        int mask = 0;
        int maxLen = 1;

        while (r < n) {
            int curr = nums[r];
            if ((curr & mask) == 0) {
                mask |= curr; //track 1's at any bit - Smart.
                maxLen = Math.max(maxLen, r-l+1);
                r += 1;
            } else {
                mask ^= nums[l]; //XOR to remove nums[l] from mask - Smart trick.
                l += 1;
            }
        }

        return maxLen;
    }


    //T: O(N^2)
    private int brute(int[] nums) {

        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            int mask = 0;
            for (int j=i; j<nums.length; j++) {
                if ((nums[j] & mask) != 0) break;
                mask |= nums[j];
                maxLen = Math.max(maxLen, j-i+1);
            }
        }

        return maxLen;
    }
}
