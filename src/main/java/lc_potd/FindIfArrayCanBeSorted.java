package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/find-if-array-can-be-sorted/description/
//@check - https://www.youtube.com/watch?v=TYOnpelfejY&ab_channel=codestorywithMIK
public class FindIfArrayCanBeSorted {
    public boolean canSortArray(int[] nums) {
        return mysol(nums);
    }

    /*
        Following is my sol.
        Mik showed a solution using simple quicksort and also
        an optimization to my sol. @check if recurring.
     */
    private boolean mysol(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int j = i+1;
            int prev = nums[i];

            /*
                Keep expanding till we see elements that have
                same num of set bits.
            */
            while ((j< nums.length) && (setBits(nums[j]) == setBits(prev))) {
                j += 1;
            }

            /*
                Sort that range of elements. Here range means contiguous
                set of elements with same number of set bits.
                NOTE: within a range, an element can be moved
                from any position to any position by swapping continuously.

                Mik showed an optimization. Here instead of sorting the range,
                just keep track of min and max in each range. Then, for every range
                check if curr range's minimum is greater (or equal) than prev range's
                maximum. This optimization will turn the time complexity to O(N)
            */
            Arrays.sort(nums, i, j);

            /*
                Move to next range.
            */
            i = j;
        }

        /*
            Finally, at this point, each range will sorted in itself.
            We just need to confirm if the entire array is sorted too.
        */
        for (int j=1; j<nums.length; j++) {
            if (nums[j] < nums[j-1]) return false;
        }

        return true;
    }

    private int setBits(int num) {
        int count = 0;
        while (num > 0) {
            count += (num & 1);
            num = num >> 1;
        }
        return count;
    }
}
