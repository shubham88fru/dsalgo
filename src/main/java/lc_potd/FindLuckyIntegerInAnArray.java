package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-lucky-integer-in-an-array/
public class FindLuckyIntegerInAnArray {
    public int findLucky(int[] arr) {
        return brute(arr);
    }
    /**
     There's a overly tricky bit manip soln
     that mik showed. check if this a recurring
     problem for some company.
     */
    private int brute(int[] arr) {
        int n = arr.length;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int a: arr) {
            freq.put(a, freq.getOrDefault(a, 0)+1);
        }

        int ans = -1;
        for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (key == value) ans = Math.max(ans, key);
        }

        return ans;
    }
}
