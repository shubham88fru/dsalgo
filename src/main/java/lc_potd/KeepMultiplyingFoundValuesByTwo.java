package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/keep-multiplying-found-values-by-two/?
public class KeepMultiplyingFoundValuesByTwo {
    public int findFinalValue(int[] nums, int original) {
        return revise(nums, original);
    }

    private int revise(int[] nums, int original) {
        int n = nums.length;

        Set<Integer> st = new HashSet<>();
        for (int num: nums) st.add(num);

        while (st.contains(original)) {
            original *= 2;
        }

        return original;
    }
}
