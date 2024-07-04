package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        return recursiveSol(nums, 0, n-1, 3);
    }

    //My soln.
    private int recursiveSol(int[] nums, int l, int r, int moves) {
        //if both pointers reach to the same value i.e. min and max are same - return 0.
        if (l == r) return 0;
        if (moves == 0) return Math.abs(nums[l]-nums[r]); //else when no more moves possible, return diff.

        //calculate by moving the left pointer.
        int left = recursiveSol(nums, l+1, r, moves-1);

        //calculate by moving the right pointer.
        int right = recursiveSol(nums, l, r-1, moves-1);

        return Math.min(left, right); //take the min.
    }
}
