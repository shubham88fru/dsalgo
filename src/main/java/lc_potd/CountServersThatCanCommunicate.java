package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-servers-that-communicate/
//@check - https://www.youtube.com/watch?v=GTahroSaPe0&t=2156s&ab_channel=codestorywithMIK
public class CountServersThatCanCommunicate {
    public int countServers(int[][] grid) {
        // return pass1(grid);
        return pass2(grid);
    }

    /*
        Based on mik's explanation.
        Kind of unfortunate that I couldn't
        come up with a solution for such an
        easy problem myself.

        Idea is to think of this problem as a counting
        problem. i.e. for each server check if it can
        be included in the count or not.
        To decide if a particular server can be counted,
        we need to check if the same row or column has atleast
        one other server.
     */
    private int pass2(int[][] grid) {
        //Approach - 1: Brute force.
        //For each server iterate in its row and col
        //and check if there's another 1 in there (except the curr server itself)

        //Approach - 2: Better.
        //Precompute the number of servers in each row and col.
        //Then go through each server and do a lookup if curr server's
        //row or col has an extra server.
        return better(grid);
    }

    private int better(int[][] grid) {
        Map<Integer, Integer> rows = new HashMap<>();
        Map<Integer, Integer> cols = new HashMap<>();

        int m = grid.length;
        int n = grid[0].length;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    rows.put(i, rows.getOrDefault(i, 0)+1);
                    cols.put(j, cols.getOrDefault(j, 0)+1);
                }
            }
        }

        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    if (rows.getOrDefault(i, 0) > 1 || cols.getOrDefault(j, 0) > 1) count += 1;
                }
            }
        }

        return count;
    }

    /*
        In my first pass, I misunderstood the problem
        thinking that we have to find the no. of connected
        components with size 1. However, soon enough
        this false understanding shattered my dream of
        solving this problem by myself.

        Fails for: [[1,0,0,1,0],[0,0,0,0,0],[0,0,0,1,0]]
        and similar cases.
     */
    private int pass1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] visited = new int[m][n];
        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (visited[i][j] != -1 && grid[i][j] == 1) {
                    int connected = bfs(grid, visited, i, j);
                    count += (connected > 1 ? connected : 0);
                }
            }
        }

        return count;
    }

    private int bfs(int[][] grid, int[][] visited, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] { i, j });
        visited[i][j] = -1;

        int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        int count = 0;
        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            int currI = node[0];
            int currJ = node[1];

            visited[currI][currJ] = -1;
            count += 1;

            for (int[] dir: dirs) {
                int nextI = currI + dir[0];
                int nextJ = currJ + dir[1];


                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || (visited[nextI][nextJ] == -1) || (grid[nextI][nextJ] == 0)) continue;

                q.addLast(new int[] { nextI, nextJ });
            }
        }

        return count;
    }
}
