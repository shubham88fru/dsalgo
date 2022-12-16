package swd.dp;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/nth-catalan-number0817/1
public class NthCatalanNumber {
    public static BigInteger findCatalan(int n)
    {
        return nthCatalan(n, new HashMap<Integer, BigInteger>());

    }

    private static BigInteger nthCatalan(int n, Map<Integer, BigInteger> memo) {
        if (n==0 || n==1) return BigInteger.valueOf(1);

        int key = n;
        if (memo.containsKey(key)) return memo.get(key);
        BigInteger term = BigInteger.valueOf(0);
        for (int i=0; i<n; i++) {
            BigInteger b1 = nthCatalan(n-i-1,memo);
            BigInteger b2 = nthCatalan(i, memo);
            term = term.add((b2.multiply(b1)));
        }
        memo.put(key, term);
        return memo.get(key);
    }
}
