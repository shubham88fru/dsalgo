package lc_potd;

//@link - https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/description/
public class MaximumValueOfAnOrderedTripletII {
    public long maximumTripletValue(int[] nums) {
        return better(nums);
    }

    /*
        This is the same question as part I of this
        problem, but with a larger constraint so brute force
        won't work.

        There is an optimal approach (see part I) but its
        not very intuitive.
    */
    private long better(int[] nums) {
        int n = nums.length;

        int[] lm = new int[n]; //max to the left.
        lm[0] = 0;
        for (int j=1; j<n; j++) {
            lm[j] = Math.max(lm[j-1], nums[j-1]);
        }

        int[] rm = new int[n]; //max to the right.
        rm[n-1] = 0;
        for (int j=n-2; j >= 0; j--) {
            rm[j] = Math.max(rm[j+1], nums[j+1]);
        }

        long max = 0;
        for (int j=1; j<n-1; j++) {
            max = Math.max(max, (long)(lm[j] - nums[j])*(long)rm[j]);
        }

        return max;
    }
}
