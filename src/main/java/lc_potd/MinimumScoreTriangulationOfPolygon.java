package lc_potd;

//@link - https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/
public class MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] values) {
        return mikssol(values);
    }

    /**
     Coded by me based on mik's explanation.
     */
    private int mikssol(int[] values) {
        int n = values.length;
        Integer[][] dp = new Integer[n][n];
        return dp(values, 0, n-1, dp);
    }

    private int dp(int[] values, int i, int j, Integer[][] dp) {
        if (j==i+1) return 0; //base case, no just two points. Triangle not possible.

        if (dp[i][j] != null) return dp[i][j];

        int res = Integer.MAX_VALUE;
        for (int k=i+1; k<j; k++) {
            int val = (values[i]*values[j]*values[k]) +
                    dp(values, i, k, dp) + dp(values, k, j, dp);

            res = Math.min(res, val);
        }

        dp[i][j] = res;
        return res;
    }
}
