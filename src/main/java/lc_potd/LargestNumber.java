package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/largest-number/
public class LargestNumber {
    //my soln.
    public String largestNumber(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        boolean onlyZeros = true;
        for (int num: nums) {
            ans.add(num);
            if (num != 0) onlyZeros = false;
        }

        if (onlyZeros) return "0";

        Comparator<Integer> cmp = this::cmp;

        ans.sort(cmp);
        StringBuilder sb = new StringBuilder();
        for (int num: ans) sb.append(num);

        return sb.toString();
    }

    private int cmp(int i1, int i2) {
        String s1 = String.valueOf(i1) + i2;
        String s2 = String.valueOf(i2) + i1;

        for (int i=0; i<s1.length(); i++) {
            int d1 = Character.getNumericValue(s1.charAt(i));
            int d2 = Character.getNumericValue(s2.charAt(i));

            if (d1 > d2) return -1;
            else if (d2 > d1) return 1;
        }

        return 0;
    }
}
