package swd.dp;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public int fib(int n) {
        //return calcFibRecursive(n);
        return calcFibDP(n, new HashMap<Integer, Integer>());
    }

    //T:O(2^N), S: O(N)
    private int calcFibRecursive(int n) {
        if (n==0) return 0;
        if (n==1) return 1;

        return calcFibRecursive(n-1) + calcFibRecursive(n-2);
    }


    //T: O(N), S: O(N)
    private int calcFibDP(int n, Map<Integer, Integer> memo) {
        if (n==0) return 0;
        if (n==1) return 1;

        int key = n;

        if (memo.containsKey(key)) return memo.get(key);

        int lastFib = calcFibDP(n-1, memo);
        int lastLastFib = calcFibDP(n-2, memo);
        int res = lastFib + lastLastFib;
        memo.put(key, res);

        return res;
    }
}
