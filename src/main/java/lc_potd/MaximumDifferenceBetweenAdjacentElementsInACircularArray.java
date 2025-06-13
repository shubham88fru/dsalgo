package lc_potd;

//@link - https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/
public class MaximumDifferenceBetweenAdjacentElementsInACircularArray {
    public int maxAdjacentDistance(int[] nums) {
        /*
        * can use this property
        * of circular array (for a more
        * cleaner soln)
        * nums[i+1] = nums[(i+1)%n]
        * @see miks soln.
        * */
        int maxDiff = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i=1; i<n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums[i]-nums[i-1]));
        }

        maxDiff = Math.max(maxDiff, Math.abs(nums[n-1]-nums[0]));

        return maxDiff;

    }
}
