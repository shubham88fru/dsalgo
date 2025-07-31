package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/bitwise-ors-of-subarrays/description/
//@check - https://www.youtube.com/watch?v=Jlj4LUJhQJY&ab_channel=codestorywithMIK
public class BitwiseORsOfSubarrays {
    public int subarrayBitwiseORs(int[] arr) {
        return mikssol(arr);
    }

    /*
        Coded by me based on mik's explanation.
    */
    private int mikssol(int[] arr) {
        int n = arr.length;
        Set<Integer> prev = new HashSet<>();
        Set<Integer> ans = new HashSet<>();
        for (int num: arr) {
            Set<Integer> curr = new HashSet<>();
            curr.add(num);
            for (int it: prev) {
                curr.add(num|it);
            }
            prev = curr;
            ans.addAll(curr);
        }

        return ans.size();
    }
}
