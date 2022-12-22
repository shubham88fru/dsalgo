package swd.dp;

//@link - https://leetcode.com/problems/minimum-falling-path-sum-ii/description/
public class MinimumFallingPathSumII {
    public int minFallingPathSum(int[][] grid) {
        int min = 1000001;
        int m = grid.length;
        int n = grid[0].length;
        //Map<String, Integer> memo = new HashMap<String, Integer>();
        int[][] memo = new int[201][201];
        for (int i=0; i<n; i++) {
            int ans = minFallPath(grid, m, n, 0, i, i, memo);
            if (ans<min) min = ans;
        }
        return min;
    }

    private int minFallPath(int[][] grid, int maxRow, int maxCol, int currRow, int currCol, int cantGotoCol, int[][] memo) {
        if (currRow < 0 || currRow >= maxRow || currCol < 0 || currCol >= maxCol) return 1000001;
        if (currRow == (maxRow - 1)) return grid[currRow][currCol];

        int min = 100001;
        //String key = currRow + "-" + currCol;
        //if (memo.containsKey(key)) return memo.get(key);
        if (memo[currRow][currCol] != 0 ) return memo[currRow][currCol];

        for (int i=0; i<maxCol; i++) {
            if ((i != cantGotoCol)) {
                int res = grid[currRow][currCol] + minFallPath(grid, maxRow, maxCol, currRow+1, i, i, memo);
                min = Math.min(min, res);
            }
        }
        //memo.put(key, min);
        //return memo.get(key);
        memo[currRow][currCol] = min;
        return memo[currRow][currCol];
    }
}
