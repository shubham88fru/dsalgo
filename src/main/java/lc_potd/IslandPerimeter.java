package lc_potd;

//@link - https://leetcode.com/problems/island-perimeter/
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        // return brute(grid);
        return optimal(grid);
    }

    //2. Brute
    //Go on each check that is 1
    //and check if how many sides
    //touch water.

    //1. DFS
    private int optimal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] perimeter = {0};
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, m, n, perimeter);
                    return perimeter[0];
                }
            }
        }

        return 0;
    }

    private void dfs(int[][] grid, int i, int j, int m, int n, int[] perimeter) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            perimeter[0] += 1;
            return;
        }

        if (grid[i][j] == -1) return;

        grid[i][j] = -1;
        dfs(grid, i-1, j, m, n, perimeter);
        dfs(grid, i, j+1, m, n, perimeter);
        dfs(grid, i+1, j, m, n, perimeter);
        dfs(grid, i, j-1, m, n, perimeter);
    }
}
