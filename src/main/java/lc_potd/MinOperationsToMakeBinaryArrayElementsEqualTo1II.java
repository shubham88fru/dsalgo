package lc_potd;

//@link - https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/description/
//@check - https://www.youtube.com/watch?v=oe9HR-cLAHo&t=1456s&ab_channel=AryanMittal
public class MinOperationsToMakeBinaryArrayElementsEqualTo1II {

    //My soln.
    public int minOperations(int[] nums) {
        int n = nums.length;
        int count = 0;
        int i = 0;

        //required number of flips to be performed on
        //curr elements before we know its current state (0 or 1.)
        int toFlips = 0;

        //Since every time we decide to flip, the reset of
        //the array also needs to be flipped, if we simply
        //follow the approach used in part 1 of this problem,
        //we'll get TLE.
        //So, instead we try a smarter approach. Everytime we have to flip,
        //we flip the current element and instead of running loop to flip the
        //rest of array elements, we just increment a toFlips variable. When
        //we reach a particular element, we check the effective value of the
        //current element i.e. after apply toFlips flip on the element, and if
        //that turns out to be a zero, means we need to flip the elment and rest
        //of array..
        while (i < n ) {
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
            }
            i += 1;
        }

        return count;
    }
}
