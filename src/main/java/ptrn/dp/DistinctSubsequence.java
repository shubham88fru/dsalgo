package ptrn.dp;

//@link - https://leetcode.com/problems/distinct-subsequences/
public class DistinctSubsequence {
    public int numDistinct(String s, String t) {
        return pass1(s, t);
    }

    /*
        My topdown soln.
        Need to check bottomup approach.
    */
    private int pass1(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (n > m) return 0;

        Integer[][] memo = new Integer[m+1][n+1];
        return dp(s, t, m, n, 0, 0, memo);
    }

    private int dp(String s, String t, int m, int n, int i, int j, Integer[][] memo) {
        if (j >= n) return 1;
        if (i >= m) return 0;

        if (memo[i][j] != null) return memo[i][j];

        char ch1 = s.charAt(i);
        char ch2 = t.charAt(j);

        int pick = 0;
        if (ch1 == ch2) {
            pick += dp(s, t, m, n, i+1, j+1, memo);
        }

        int notPick = dp(s, t, m, n, i+1, j, memo);

        memo[i][j] = pick + notPick;
        return memo[i][j];
    }
}
