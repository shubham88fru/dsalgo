package lc_potd;

//@link - https://leetcode.com/problems/path-with-maximum-gold/
public class PathWithMaximumGold {
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] max = new int[]{0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) dfs(grid, new int[m][n], i, j, 0, max);
            }
        }

        return max[0];
    }

    //standard backtracking algorithm.
    private void dfs(int[][] grid, int[][] visited, int i, int j, int gold, int[] max) {
        if (
                i < 0 ||
                        i >= grid.length ||
                        j < 0 ||
                        j >= grid[0].length ||
                        grid[i][j] == 0 ||
                        visited[i][j] == -1
        ) {
            return;
        }

        visited[i][j] = -1;
        gold += grid[i][j];

        //up
        dfs(grid, visited, i - 1, j, gold, max);

        //down
        dfs(grid, visited, i + 1, j, gold, max);

        //left
        dfs(grid, visited, i, j - 1, gold, max);

        //right
        dfs(grid, visited, i, j + 1, gold, max);

        max[0] = Math.max(gold, max[0]);
        visited[i][j] = 0;
    }
}
