package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/counting-bits/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4578267430125568
public class CountingBits {
    public int[] countBits(int n) {
        //return brute(n);
        //return topdowndp(n);
        return bottomupdp(n);
    }

    //1) bottom up soln.
    private int[] bottomupdp(int n) {
        int[] ans = new int[n+1];
        ans[0] = 0;

        if (n==0) return ans;
        ans[1] = 1;
        for (int i=2; i<=n; i++) {
            /*
                Num of 1s in binary rep of an
                even num is same as the num of 1s
                in binary rep of half of the num.
            */
            if (i%2 == 0) ans[i] = ans[i/2];

            /*
                Num of 1s in binary rep of an
                odd num is 1 plus num of 1s in
                binary rep of half of the num.
                (below, even if we do i/2, it will be same
                as i-1/2 because of how division by integer
                works.)
            */
            else ans[i] = 1+ans[(i-1)/2];
        }

        return ans;
    }

    private int[] topdowndp(int n) {
        if (n==0) return new int[] {0};
        int[] ans = new int[n+1];
        ans[0] = 0;
        ans[1] = 1;
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, ans[0]);
        cache.put(1, ans[1]);
        for (int i=n; i>=0; i--)
            recursion(i, ans, cache);
        return ans;
    }



    private int recursion(int n, int[] ans, Map<Integer, Integer> cache) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (cache.containsKey(n)) return cache.get(n);

        /*
            Num of 1s in binary rep of an
            even num is same as the num of 1s
            in binary rep of half of the num.
        */
        if (n%2 == 0) ans[n] = recursion(n/2, ans, cache);

        /*
            Num of 1s in binary rep of an
            odd num is 1 plus num of 1s in
            binary rep of half of the num.
            (below, even if we do i/2, it will be same
            as i-1/2 because of how division by integer
            works.)
        */
        else ans[n] = 1 + recursion((n-1)/2, ans, cache);

        cache.put(n, ans[n]);
        return ans[n];
    }

    private int[] brute(int n) {
        int[] ans = new int[n+1];
        for (int i=0; i <= n; i++) {
            ans[i] = Integer.bitCount(i);
        }

        return ans;
    }
}
