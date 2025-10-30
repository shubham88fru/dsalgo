package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/description/?
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {
    public int minNumberOperations(int[] target) {
        return pass1(target);
    }

    /**
     My greedy soln.

     */
    private int pass1(int[] target) {
        int n = target.length;

        int ops = 0;
        int sum = 0;

        for (int num: target) {
            if (ops < num) {
                sum += (num - ops);
                ops += (num - ops);
            } else {
                ops = num; //ops can't exceed the num.
            }
        }

        return sum;
    }
}
