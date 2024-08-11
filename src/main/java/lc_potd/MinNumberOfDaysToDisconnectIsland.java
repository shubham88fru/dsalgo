package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/
//@check - https://www.youtube.com/watch?v=HCHpyAk1Ekw
public class MinNumberOfDaysToDisconnectIsland {
    /**
     * Solved by self after slight hint.
     */
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //If num of islands in the OG grid
        //is either 0 or more than 1, then
        //the grid is already disconnected.
        int islands = numIslands(grid);
        if (islands != 1) return 0;

        //else, try making each 1 as a 0 one by one
        //and check if it makes the grid disconnected.
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (numIslands(grid) != 1) return 1;
                    grid[i][j] = 1; //didn't work, so back to og value.
                }
            }
        }

        //finally, if the grid can't be disconnected in
        //one day, then no matter what, it can definitely be disconnect
        //in 2 days (Take any 2*2 subgrid of 1s and make the diagonal 1s as 0)
        return 2;
    }

    //find num of islands in given grid.
    private int numIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];

        int islands = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1 && visited[i][j] != 1) {
                    islands += 1;
                    mark(grid, visited, i, j, m, n);
                }
            }
        }

        return islands;
    }

    private void mark(int[][] grid, int[][] visited, int i, int j, int m, int n) {
        if (i >= m || i < 0 || j >= n || j < 0) return ;
        if (grid[i][j] != 1) return;
        if (visited[i][j] == 1) return;

        visited[i][j] = 1;

        mark(grid, visited, i-1, j, m, n);
        mark(grid, visited, i+1, j, m, n);
        mark(grid, visited, i, j+1, m, n);
        mark(grid, visited, i, j-1, m, n);
    }
}
