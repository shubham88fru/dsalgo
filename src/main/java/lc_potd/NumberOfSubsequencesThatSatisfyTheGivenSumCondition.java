package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/
//@check - https://www.youtube.com/watch?v=eGqs55VTP3I&ab_channel=codestorywithMIK
public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    public int numSubseq(int[] nums, int target) {
        // return pass1(nums, target);
        return pass2(nums, target);
    }

    /*
        Optimized version of my pass1 soln.
        Took hint from mik.
    */
    private int pass2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0;
        int r = n-1;
        int[] pow = new int[n];
        pow[0] = 1;
        for (int i=1; i<n; i++) {
            pow[i] = (2 * pow[i-1])%1000000007; //pre compute the power.
        }

        int count = 0;
        while (l <= r) { //optimization using two pointer.
            if (nums[l] + nums[r] <= target) {
                count = (count%1000000007 + pow[r-l]%1000000007)%1000000007; //special handling for power.
                l += 1;
            } else r -= 1;
        }

        return count;
    }

    /*
        My soln. Correct approach and intuition
        but slightly un-optimized and therefore
        was giving wrong answers for largers tc
        due to overflow.
    */
    private int pass1(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        long count = 0;
        for (int i=0; i<n; i++) { //fix the smaller num
            if (2*nums[i] > target) break;
            int pos = 0;
            for (int j=i+1; j<n; j++) {
                if (nums[j] + nums[i] <= target) pos += 1;
                else break;
            }
            count += (long)Math.pow(2.0, pos)%1000000007; //then two possibilities for valid nums.
        }

        return (int)count%1000000007;
    }
}
