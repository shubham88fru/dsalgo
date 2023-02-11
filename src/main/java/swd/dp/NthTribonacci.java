package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/n-th-tribonacci-number/description/
public class NthTribonacci {
    public int tribonacci(int n) {
        return nthTribonacci(n, new HashMap<Integer, Integer>());
    }

    private int nthTribonacci(int n, Map<Integer, Integer> memo) {

        if (n==0) return 0;
        if (n==1) return 1;
        if (n==2) return 1;

        int key = n;
        if (memo.containsKey(key)) return memo.get(key);

        int nthTribonacci = nthTribonacci(n-3, memo)
                + nthTribonacci(n-2, memo)
                + nthTribonacci(n-1, memo);
        memo.put(key, nthTribonacci);
        return memo.get(key);
    }
}
