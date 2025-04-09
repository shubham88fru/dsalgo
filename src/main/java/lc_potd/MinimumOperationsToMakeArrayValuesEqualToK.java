package lc_potd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/
//@check - https://www.youtube.com/watch?v=6UI_f7_epV0&t=619s&ab_channel=codestorywithMIK
public class MinimumOperationsToMakeArrayValuesEqualToK {
    public int minOperations(int[] nums, int k) {
        // return pass1(nums, k);
        return optimal(nums, k);
    }

    /*
        Optimal soln. TC O(n).
        The problem is basically finding
        count of distinct numbers larger than k.
    */
    private int optimal(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> st = new HashSet<>();

        for (int i=0; i<n; i++) {
            if (nums[i] < k) return -1; //impossible to make all elements to k.
            if (nums[i] > k) st.add(nums[i]);
        }

        return st.size();
    }

    /*
        My soln. TC O(nlogn)
    */
    private int pass1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int i = n-2;
        int ops = 0;
        int vn = nums[n-1];
        while (i >= 0) {
            if (nums[i] == nums[i+1]) {
                i -= 1;
                continue;
            }

            ops += 1;
            vn = nums[i];
            i -= 1;
        }

        if (vn < k) return -1;
        if (vn == k) return ops;
        return ops + 1;
    }
}
