package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/description/
public class PartitionArraySuchThatMaximumDifferenceIsK {
    public int partitionArray(int[] nums, int k) {
        return pass1(nums, k);
    }

    /*
        My soln. Mik had the exact same soln.
        Idea is to sort the array so that elements
        are closer to each other and we get the min
        number of parts.
    */
    private int pass1(int[] nums, int k) {
        int n = nums.length;
        int parts = 1;

        Arrays.sort(nums);
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;

        for (int i=0; i<n; i++) {
            mini = Math.min(mini, nums[i]);
            maxi = Math.max(maxi, nums[i]);

            if (maxi-mini > k) {
                parts += 1;
                mini = nums[i];
                maxi = nums[i];
            }
        }

        return parts;
    }
}
