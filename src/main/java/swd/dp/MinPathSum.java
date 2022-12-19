package swd.dp;

//@link - https://leetcode.com/problems/minimum-path-sum/description/
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[201][201];
        //return minSum(grid, grid.length, grid[0].length, 0, 0, new HashMap<String, Integer>());
        return minSum(grid, grid.length, grid[0].length, 0, 0, memo);

    }

    private int minSum(int[][] grid, int maxRow, int maxCol, int currRow, int currCol, int[][] memo) {
        if (currRow >= maxRow || currCol >= maxCol) return 50001;
        if (currRow == (maxRow-1) && currCol == (maxCol-1)) return grid[currRow][currCol];

        //String key = currRow + "-" + currCol;
        //if (memo.containsKey(key)) memo.get(key);
        if (memo[currRow][currCol] != 0) return memo[currRow][currCol];
        int moveRight = grid[currRow][currCol] + minSum(grid, maxRow, maxCol, currRow, currCol+1, memo);
        int moveDown = grid[currRow][currCol] + minSum(grid, maxRow, maxCol, currRow+1, currCol, memo);

        //memo.put(key, Math.min(moveRight, moveDown));
        memo[currRow][currCol] = Math.min(moveRight, moveDown);
        //return memo.get(key);
        return memo[currRow][currCol];
    }
}
