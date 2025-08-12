package lc_potd;

//@link - https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/description/
public class WaysToExpressAnIntegerAsSumOfPowers {
    public int numberOfWays(int n, int x) {
        return pass1(n, x);
    }

    private int pass1(int n, int x) {
        Integer[][] dp = new Integer[n+1][n+1];

        // return dp1(n, x, 1, dp);
        return dp2(n, x, 1, dp);
    }

    /*
        Not that I couldn't have come up with this
        approach had I not gotten into my og approach (below).
        Although they look very similar, there's something
        fundamentally different between the two solns.
        Not 100% sure what!
    */
    private int dp2(int n, int x, int j, Integer[][] dp) {
        if (n==0) return 1;
        if (n < 0) return 0;

        int pow = (int)Math.pow(j, x);
        if (pow > n) return 0;

        if (dp[n][j] != null) return dp[n][j];

        int take = dp2(n-pow, x, j+1, dp);
        int ntake = dp2(n, x, j+1, dp);

        dp[n][j] = (take+ntake)%1000000007;
        return dp[n][j];
    }


    /*
        My first intuition was dp
        but not sure why I chose to
        write the code in this way.
        This gives TLE even with memo.
        Not 100% sure why.
     */
    private int dp1(int n, int x, int j, Integer[][] dp) {
        if (n == 0) return 1;

        if (dp[n][j] != null) return dp[n][j];

        int count = 0;
        for (int i=j; ((int)Math.pow(i, x))<=n; i++) {
            count = (count + dp1(n-((int)Math.pow(i, x)), x, i+1, dp))%1000000007;
        }

        dp[n][j] = count;
        return count;
    }
}
