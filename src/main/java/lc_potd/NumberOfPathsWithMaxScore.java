package lc_potd;

import java.util.List;

//@link - https://leetcode.com/problems/number-of-paths-with-max-score/
//@check - https://www.youtube.com/watch?v=52a_yLVy_MQ
public class NumberOfPathsWithMaxScore {
    public int[] pathsWithMaxScore(List<String> board) {
        return pass1(board);
    }

    /*
        Took hint from mik.
     */
    private int[] pass1(List<String> board) {
        int n = board.get(0).length();
        int m = board.size();

        Pair23[][] dp = new Pair23[m+1][n+1];
        Pair23 ans = dfs(board, m, n, m-1, n-1, dp);
        return new int[]{ans.sum, ans.paths};
    }

    private Pair23 dfs(List<String> board, int m, int n, int i, int j, Pair23[][] dp) {
        if (i == 0 && j == 0) return new Pair23(0, 1);

        if (i < 0 || j < 0) return new Pair23(0, 0);

        char ch = board.get(i).charAt(j);
        if (ch == 'X') return new Pair23(0, 0);

        if (dp[i][j] != null) return dp[i][j];

        int add = (ch == 'S' ? 0: Character.getNumericValue(ch));

        Pair23 u = dfs(board, m, n, i-1, j, dp);
        Pair23 l =  dfs(board, m, n, i, j-1, dp);
        Pair23 ul = dfs(board, m, n, i-1, j-1, dp);

        int mxp = 0; //max path
        int mxs = Math.max(Math.max(u.paths > 0 ? u.sum: 0, l.paths > 0 ? l.sum: 0), ul.paths > 0 ? ul.sum: 0); //max sum
        if (u.sum == mxs) mxp = (mxp+u.paths)%1000000007;
        if (l.sum == mxs) mxp = (mxp+l.paths)%1000000007;
        if (ul.sum == mxs) mxp = (mxp+ul.paths)%1000000007;

        dp[i][j] = new Pair23(add+mxs, mxp);
        return dp[i][j];
    }
}


class Pair23 {
    int sum;
    int paths;

    public Pair23(int sum, int paths) {
        this.sum = sum;
        this.paths = paths;
    }
}
