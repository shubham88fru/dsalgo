package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/making-a-large-island/
//@check - https://www.youtube.com/watch?v=iCAC-QrQ-4A&ab_channel=codestorywithMIK
public class MakingALargeIsland {
    int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        // return brute1(grid);
        // return brute2(grid);
        return optimal(grid);
    }

    /*
        This optimal approach is based on Mik's explanation.
        It is an extremely simple to understand approach once you
        get it.
     */
    private int optimal(int[][] grid) {
        int n = grid.length;

        int tnt = 2; //any starting value other than 0, 1
        Map<Integer, Integer> taintSz = new HashMap<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    taintSz.put(tnt, taint(grid, tnt, i, j, n));

                    //edge case. If entire grid tainted means
                    //the entire grid only has 1s and no zeros.
                    if (taintSz.get(tnt) == n*n) return n*n;
                    tnt += 1;
                }
            }
        }

        int mx = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int sum = 0;
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    for (int[] dir: dirs) {
                        int nextI = dir[0] + i;
                        int nextJ = dir[1] + j;

                        if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n ||
                                grid[nextI][nextJ] == 0) continue;

                        if (seen.contains(grid[nextI][nextJ])) continue;
                        sum += taintSz.get(grid[nextI][nextJ]);
                        seen.add(grid[nextI][nextJ]);
                    }
                }

                mx = Math.max(mx, 1+ sum);
            }
        }

        return mx;

    }

    private int taint(int[][] grid, int tnt, int i, int j, int n) {
        grid[i][j] = tnt;

        int sz = 1;
        for (int[] dir: dirs) {
            int nextI = dir[0] + i;
            int nextJ = dir[1] + j;

            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n ||
                    grid[nextI][nextJ] != 1) continue;

            sz += taint(grid, tnt, nextI, nextJ, n);
        }

        return sz;

    }

    /* 0) A brute force approach is -
        First, without changing anything in the graph,
        dfs and find the max size island.
        Then one by one only change each zero to 1,
        and run a dfs.
        Take max of all. This approach is slightly similar to
        my brute force approach.
     */

    /* 1) DFS from each cell. - TLE */
    private int brute1(int[][] grid) {
        int n = grid.length;

        int max = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int[][] visited = new int[n][n];
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    max = Math.max(max, dfs(grid, i, j, n, visited));
                    grid[i][j] = 0;
                } else {
                    max = Math.max(max, dfs(grid, i, j, n, visited));
                }
            }
        }

        return max;
    }

    /* 2) Based on mik's explanation. Still TLE. */
    private int brute2(int[][] grid) {
        int n = grid.length;

        int max = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int[][] visited = new int[n][n];
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    max = Math.max(max, callDFS(grid, visited));
                    grid[i][j] = 0;
                } else {
                    max = Math.max(max, callDFS(grid, visited));
                }
            }
        }

        return max;
    }

    private int callDFS(int[][] grid, int[][] visited) {
        int n = grid.length;

        int max = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (visited[i][j] != -1 && grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j, n, visited));
                }
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int i, int j, int n, int[][] visited) {

        visited[i][j] = -1;

        int sz = 1;
        for (int[] dir: dirs) {
            int nextI = dir[0] + i;
            int nextJ = dir[1] + j;

            if (nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n ||
                    visited[nextI][nextJ] == -1 || grid[nextI][nextJ] == 0) continue;

            sz += dfs(grid, nextI, nextJ, n, visited);

        }

        return sz;

    }
}
