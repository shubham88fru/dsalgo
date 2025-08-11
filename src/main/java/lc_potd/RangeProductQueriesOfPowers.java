package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/range-product-queries-of-powers/description/
public class RangeProductQueriesOfPowers {
    public int[] productQueries(int n, int[][] queries) {
        return pass1(n, queries);
    }

    /**
     * My soln. Mik had a soln on similar lines but slightly diff.
     */
    private int[] pass1(int n, int[][] queries) {
        List<Integer> powers = new ArrayList<>();
        for (int i=0; i<=31; i++) {
            int and = n & (1<<i);
            if (and != 0) powers.add(i);
        }

        int[] pp = new int[powers.size()];
        for (int i=0; i<powers.size(); i++) {
            if (i==0) {
                pp[i] = powers.get(i);
            } else {
                pp[i] = pp[i-1] + powers.get(i);
            }
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i=0; i<m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if (start == 0) ans[i] = (int)(Math.pow(2.0, pp[end])%1000000007);
            else {

                ans[i] = (int)(Math.pow(2.0, pp[end]-pp[start-1])%1000000007);

            }

        }

        return ans;
    }
}
