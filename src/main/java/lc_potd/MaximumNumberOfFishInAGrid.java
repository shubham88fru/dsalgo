package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/
//@check - https://www.youtube.com/watch?v=kfs9vzv7kHc&ab_channel=codestorywithMIK
public class MaximumNumberOfFishInAGrid {
    private final int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public int findMaxFish(int[][] grid) {
        return brute(grid);
    }

    /*
    * Following are my BFS and DFS solutions.
    * However, this problem is a good candidate for the
    * DSU topic. Mik also showed the DSU approach for this
    * problem. Study DSU topic from mik's playlist and
    * solve all DSU questions again.
    * */
    private int brute(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int max = 0;
        for (int i=0; i<m; i++) {
            int[][] visited = new int[m][n];
            for (int j=0; j<n; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(max, dfs(grid, visited, i, j, m, n));
                }
            }
        }

        return max;
    }

    private int bfs(int[][] grid, int[][] visited, int i, int j, int m, int n) {
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {i, j});

        int sum = 0;
        while (!q.isEmpty()) {
            int[] node = q.removeFirst();

            int nodeI = node[0];
            int nodeJ = node[1];
            if (visited[nodeI][nodeJ] == -1) continue;

            sum += grid[nodeI][nodeJ];

            visited[nodeI][nodeJ] = -1;
            for (int[] dir: dirs) {
                int nextI = dir[0] + nodeI;
                int nextJ = dir[1] + nodeJ;
                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == -1 || grid[nextI][nextJ] == 0) continue;
                q.addLast(new int[] { nextI, nextJ });
            }
        }

        return sum;
    }

    private int dfs(int[][] grid, int[][] visited, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == -1 || grid[i][j] == 0) return 0;
        visited[i][j] = -1;
        int sum = 0;
        for (int[] dir: dirs) {
            int nextI = dir[0] + i;
            int nextJ = dir[1] + j;

            sum += dfs(grid, visited, nextI, nextJ, m, n);
        }

        return grid[i][j] + sum;
    }
}
