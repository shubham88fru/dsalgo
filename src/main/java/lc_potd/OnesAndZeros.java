package lc_potd;

//@link - https://leetcode.com/problems/ones-and-zeroes/description/?
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        return pass1(strs, m, n);
    }

    /**
      My soln.
     */
    private int pass1(String[] strs, int m, int n) {
        Integer[][][] dp = new Integer[strs.length+1][m+1][n+1];
        return dp(strs, dp, 0, m, n);
    }

    private int dp(String[] strs, Integer[][][] dp, int i, int m, int n) {
        if (i >= strs.length) return 0;
        if (m < 0 || n < 0) return 0;

        if (dp[i][m][n] != null) return dp[i][m][n];

        int[] cnt = count(strs[i]);

        int pick = 0;
        if (cnt[0] <= m && cnt[1] <= n) {
            pick = 1 + dp(strs, dp, i+1, m-cnt[0], n-cnt[1]);
        }

        int nPick = dp(strs, dp, i+1, m, n);

        dp[i][m][n] = Math.max(pick, nPick);

        return dp[i][m][n];
    }

    private int[] count(String str) {
        int o = 0;
        int z = 0;
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '0') o += 1;
            else z += 1;
        }

        return new int[] {o, z};
    }
}
