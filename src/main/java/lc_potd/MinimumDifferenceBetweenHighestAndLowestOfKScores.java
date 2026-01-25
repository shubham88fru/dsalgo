package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/description/?
public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public int minimumDifference(int[] nums, int k) {
        if (k==1) return 0;
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        //It's unituitive how sliding window would be
        //able to check all possible k combs. But actually,
        //if the array is sorted, for this problem, we don't
        //need to find all k combs. Instead, sorting will group
        //all the elements as close as they can be (which is what
        //we need to minimize the max-min diff). And so,
        //iterating over the groups of k contiguous elements in sliding
        //window will the best options of k elements to find the answer.
        while (r < nums.length) {
            //once we have k elements of the group..
            if (r - l + 1 >= k) {
                //compare the min-max diff of the group with the min diff
                //seen so far.
                min = Math.min(min, Math.abs(nums[l]-nums[r]));
                l += 1; //slide the window from back.
            }
            r += 1; //slide the window from right.
        }
        return min;
    }
}
