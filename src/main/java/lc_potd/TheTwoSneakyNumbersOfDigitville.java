package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/?
public class TheTwoSneakyNumbersOfDigitville {
    public int[] getSneakyNumbers(int[] nums) {
        return brute(nums);
    }

    private int[] brute(int[] nums) {
        int[] ans = new int[2];
        Set<Integer> st = new HashSet<>();
        int idx = 0;
        for (int num: nums) {
            if (st.contains(num)) {
                ans[idx++] = num;
            } else st.add(num);
        }

        return ans;
    }
}
