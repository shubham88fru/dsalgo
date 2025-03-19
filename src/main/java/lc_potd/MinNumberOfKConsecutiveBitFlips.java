package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
//@check - https://www.youtube.com/watch?v=NtCLzpLmxNU&ab_channel=codestorywithMIK
// https://www.youtube.com/watch?v=oe9HR-cLAHo&t=1456s&ab_channel=AryanMittal
public class MinNumberOfKConsecutiveBitFlips {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int toFlips = 0;
        int count = 0;

        Set<Integer> iFlipped = new HashSet<>();

        while (i <= n-k) {
            //At every index, we'll remove the contribution of
            //i-kth element to toFlips, coz for the current element (ith)
            //i-kth element is out of window of `k` elements.
            if (iFlipped.contains(i-k)) toFlips -= 1;
            if (
                //If curr element is a zero, and toFlips is even,
                //means even after the flips the element will remain zero only.
                    (nums[i] == 0 && toFlips%2 == 0)
                            ||
                            //If curr element is a one, and toFlips is odd, means
                            //after the flips curr element will become zero.
                            (nums[i] == 1 && toFlips%2 != 0)

            ) {

                //if the curr effective element is a zero,
                //flip it and increment the toFlips for the rest
                //of the array elements.
                count += 1;
                toFlips += 1;
                iFlipped.add(i);
            }
            i += 1;
        }

        /*
            We don't need to do this extra loop.
            this can be checked in the first loop itself.
            Check latest submission on 19th march for that.
         */

        //At this points, we should have made
        //all the elemnts in [0..i-k] equal to 1.
        //All that remains is if the last few elements
        //are 1 too.
        while (i < n) {
            if (iFlipped.contains(i-k)) toFlips -= 1;
            if (
                //If curr element is a zero, and toFlips is even,
                //means even after the flips the element will remain zero only.
                    (nums[i] == 0 && toFlips%2 == 0)
                            ||
                            //If curr element is a one, and toFlips is odd, means
                            //after the flips curr element will become zero.
                            (nums[i] == 1 && toFlips%2 != 0)

            ) return -1;

            i += 1;
        }

        return count;
    }
}
