package lc_potd;

//@link - https://leetcode.com/problems/maximum-width-ramp/description/
//@check - https://www.youtube.com/watch?v=mcJBhWXel-Y&ab_channel=codestorywithMIK
public class MaximumWidthRamp {
    public int maxWidthRamp(int[] nums) {
        return miksol(nums);
    }

    /*
        Coded by me but approach by mik.
     */
    private int miksol(int[] nums) {
        int n = nums.length;

        //Pre-compute an array which
        //contains the largest element
        //to the right for each element.
        int[] maxToRight = new int[n];
        int max = nums[n-1];
        for (int i=n-1; i>=0; i--) {
            if (nums[i] >= max) {
                max = nums[i];
            }
            maxToRight[i] = max;
        }

        int i = 0;
        int j = 0;

        int maxLen = 0;
        while (j < n) {
            //for each num, keep checking
            //till what point we can have
            //a valid length.
            if (nums[i] <= maxToRight[j]) {
                maxLen = Math.max(maxLen, j-i);
            } else {
                //if for an element, the element
                //is smaller than the max element
                //to its right, then no point checking
                //further for that num.
                i += 1;
            }

            j += 1;

        }

        return maxLen;
    }

    //2) Better. For each element compare it from rightmost
    //and keep moving inwards until you find the first element
    //that is larger, that way we don't have to keep getting closer.

    //3) Brute. for each element, check all elements
    //to its right and keep updating the ramp value.
}
