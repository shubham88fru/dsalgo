package lc_potd;

//@link - https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/
//@check - https://www.youtube.com/watch?v=oe9HR-cLAHo&t=1456s&ab_channel=AryanMittal
public class MinOperationsToMakeBinaryArrayElementsEqualTo1 {

    //My soln.
    public int minOperations(int[] nums) {
        int i = 0;
        int n = nums.length;

        int count = 0;
        //iterate till the third last element.
        while (i <= n-3) {
            //if curr element is zero, we flip
            //the set of 3 elements.
            if (nums[i] == 0) {
                nums[i] ^= 1;
                nums[i+1] ^= 1;
                nums[i+2] ^= 1;
                count += 1;
            }
            i += 1;
        }

        //at this point, we would have ensured that all elements
        //till third last element are certainly one. All that needs to
        //verify is that the last two elements are one. If they are, we
        //return the flip count, otherwise its impossible to convert the
        //array to all ones.
        return (nums[i] == 1 && nums[i+1] == 1) ? count : -1;
    }
}
