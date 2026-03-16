package lc_potd;

//@link - https://leetcode.com/problems/knight-probability-in-chessboard/
//@check - https://www.youtube.com/watch?v=ETA-5CD0z7Q
public class KnightProbabilityOfChessboard {
    public double knightProbability(int n, int k, int row, int column) {
        return tddp(n, k, row, column);
    }

    private double tddp(int n, int k, int row, int column) {

        return dp(n, k, row, column, new Double[n+1][n+1][k+1]);
    }

    private double dp(int n, int k, int i, int j, Double[][][] memo) {
        if (i < 0 || i >= n || j < 0 || j >= n) return 0;
        if (k == 0) return 1;

        if (memo[i][j][k] != null) return memo[i][j][k];
        double moves = dp(n, k-1, i-2, j+1, memo) + dp(n, k-1, i-1, j+2, memo)
                + dp(n, k-1, i+1, j+2, memo) + dp(n, k-1, i+2, j+1, memo)
                + dp(n, k-1, i+2, j-1, memo) + dp(n, k-1, i+1, j-2, memo)
                + dp(n, k-1, i-1, j-2, memo) + dp(n, k-1, i-2, j-1, memo);

        memo[i][j][k] = moves/8.0;
        return memo[i][j][k];
    }
}
