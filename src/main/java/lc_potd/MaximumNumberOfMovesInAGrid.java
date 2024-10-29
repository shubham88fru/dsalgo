package lc_potd;

//@link - https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/
public class MaximumNumberOfMovesInAGrid {
    public int maxMoves(int[][] grid) {
        Integer[][] memo = new Integer[grid.length][grid[0].length];

        int maxLen = 0;
        for (int i=0; i<grid.length; i++) {
            maxLen = Math.max(dp(grid, i, 0, memo), maxLen);
        }

        return maxLen;
    }

    private int dp(int[][] grid, int i, int j, Integer[][] memo) {

        if (memo[i][j] != null) return memo[i][j];

        int upRight = 0;
        if ((i-1 >=0) && (j+1<grid[0].length) && (grid[i][j] < grid[i-1][j+1])) {
            upRight =  1 + dp(grid, i-1, j+1, memo);
        }

        int right = 0;
        if ((i < grid.length) && (j+1<grid[0].length) && (grid[i][j] < grid[i][j+1])) {
            right =  1 + dp(grid, i, j+1, memo);
        }

        int downRight = 0;
        if ((i+1 < grid.length) && (j+1<grid[0].length) && (grid[i][j] < grid[i+1][j+1])) {
            downRight = 1 + dp(grid, i+1, j+1, memo);
        }

        memo[i][j] = Math.max(upRight, Math.max(right, downRight));

        return Math.max(upRight, Math.max(right, downRight));
    }
}
