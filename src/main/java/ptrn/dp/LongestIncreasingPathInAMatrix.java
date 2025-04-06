package ptrn.dp;

//@link - https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPathInAMatrix {
    private final int[][] dirs = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };

    public int longestIncreasingPath(int[][] matrix) {
        return brute(matrix);
    }

    /*
        My topdown dp + memoization approach.
        Performs poorly on the runtime.
        Need to check online for an optimal
        approach.
     */
    private int brute(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Integer[][] memo = new Integer[m+1][n+1];

        int maxLen = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                maxLen = Math.max(maxLen, dfs(matrix, m, n, i, j, new int[m][n], memo));
            }
        }

        return 1 + maxLen;
    }

    private int dfs(int[][] matrix, int m, int n, int i, int j, int[][] visited, Integer[][] memo) {

        if (memo[i][j] != null) return memo[i][j];

        visited[i][j] = -1;

        int max = 0;
        for (int[] dir: dirs) {
            int nextI = dir[0] + i;
            int nextJ = dir[1] + j;

            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n ||
                    visited[nextI][nextJ] == -1 || matrix[nextI][nextJ] <= matrix[i][j]) continue;

            max = Math.max(max, 1 + dfs(matrix, m, n, nextI, nextJ, visited, memo));

        }

        visited[i][j] = 0;

        memo[i][j] = max;
        return max;
    }
}
