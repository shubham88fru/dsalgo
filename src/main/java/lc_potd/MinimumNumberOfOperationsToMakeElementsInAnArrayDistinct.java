package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/
//@check - https://www.youtube.com/watch?v=6UI_f7_epV0&t=619s&ab_channel=codestorywithMIK
public class MinimumNumberOfOperationsToMakeElementsInAnArrayDistinct {
    public int minimumOperations(int[] nums) {
        // return pass1(nums);
        return optimal(nums);
    }

    private int optimal(int[] nums) {
        int n = nums.length;

        Set<Integer> st = new HashSet<>();
        for (int i=n-1; i>=0; i--) {
            int num = nums[i];
            if (st.contains(num)) {
                return (int)Math.ceil((i+1)/3.0);
            }
            st.add(num);
        }

        return 0;
    }

    /*
        Based on my intuition for a one pass soln.
        This problem is a bit tricky to implement
        one pass when iterating from left, and prone
        to missing edge cases (which I did as well)

        See mik's sol for a one pass soln iterating from
        the right.

    */
    private int pass1(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        int k = 2;
        int ops = 0;

        Set<Integer> st = new HashSet<>();
        int l = 0;
        while (l < n) {
            int num = nums[l];
            while (st.contains(num)) { //tricky
                if (i < n) {
                    st.remove(nums[i]);
                    i += 3;
                }

                if (j < n) {
                    st.remove(nums[j]);
                    j += 3;
                }

                if (k < n) {
                    st.remove(nums[k]);
                    k += 3;
                }

                ops += 1;
            }

            if (l >= i) st.add(num); //tricky

            l += 1;
            l = Math.max(l, i);

        }

        return ops;
    }
}
