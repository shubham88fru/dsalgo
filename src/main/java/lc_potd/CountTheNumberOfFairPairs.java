package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/count-the-number-of-fair-pairs/description/
//@check - https://www.youtube.com/watch?v=r3EnymXRC9A&t=1512s&ab_channel=codestorywithMIK
public class CountTheNumberOfFairPairs {
    public long countFairPairs(int[] nums, int lower, int upper) {
        /*
            We can sort the array, since we just need to find the
            sum of two index positions anyways. So, for two indexes
            i and j, even if then get jumbled, we are anyways just interested
            int sum[i] + sum[j] which would remain same anyways.
        */
        Arrays.sort(nums);
        int n = nums.length;

        long count = 0;
        for (int i=0; i<n; i++) {
            int low = lower-nums[i];
            int high = upper-nums[i];

            //Find the num of nums that pair up with nums[i] (curr)
            //such that the sum is < (strictly less) than low, starting
            //from the next index (remember valid pairs are such that i<j)
            int lowIndex = binarySearchHigh(nums, low-1, i+1, n-1);

            //Find the num of nums that pair up with nums[i] (curr)
            //such that the sum is >= (greater than equal) than high, starting
            //from the next index (remember valid pairs are such that i<j)
            int highIndex = binarySearchHigh(nums, high, i+1, n-1);

            /*
                Don't have a very good explanation for
                why I did the following two. But the idea
                is to handle edge cases when there are no
                elements in the range that is lower than low or high.
             */
            if (lowIndex == -1) lowIndex = i;
            if (highIndex == -1) continue;

            //add the number of pairs contributed by nums[i]
            count += (long)(highIndex-lowIndex);
        }

        return count;
    }

    /*
        Returns the index of the largest numbers smaller than the given
        target such that it lies in the given range.
     */
    private int binarySearchHigh(int[] sorted, int target, int low, int high) {
        int l = low;
        int r = high;

        int highIndex = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (sorted[mid] == target) {
                highIndex = mid;
                l = mid+1;
            } else if (sorted[mid] > target) {
                r = mid-1;
            } else {
                highIndex = mid;
                l = mid + 1;
            }
        }

        return highIndex;
    }
}
