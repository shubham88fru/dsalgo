package ptrn.dp;

import java.util.Map;

//@link - https://leetcode.com/problems/n-th-tribonacci-number/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6621826336948224
public class NthTribonacci {
    public int tribonacci(int n) {
        //return triboTopDown(n, new HashMap<>());
        return triboBottomUp(n);
    }

    //1) Recursive, top-down approach.
    private int triboTopDown(int n, Map<Integer, Integer> cache) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        if (cache.containsKey(n)) return cache.get(n);

        cache.put(n, triboTopDown(n-1, cache) + triboTopDown(n-2, cache) + triboTopDown(n-3, cache));
        return cache.get(n);
    }

    //2) Iterative, bottom-up approach.
    private int triboBottomUp(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int f = 0, s = 1, t = 1;
        for (int i=3; i <= n; i++) {
            int sum = f + s + t;
            //for next iteration,
            //second becomes first,
            //third becomes second,
            //and prev sum becomes third.
            f = s;
            s = t;
            t = sum;
        }

        return t;
    }
}
