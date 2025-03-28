package lc_potd;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/
//@check - https://www.youtube.com/watch?v=XBd9vO4N0Js&t=2574s&ab_channel=codestorywithMIK
public class MaximumNumberOfPointsFromGridQueries {
    public int[] maxPoints(int[][] grid, int[] queries) {
        // return bruteDFS(grid, queries);
        // return bruteBFS(grid, queries);
        return optimal(grid, queries);
    }

    /*
        Based on mik's explanation.
        The idea is that if we sort the queries,
        then for every query, we know that the
        answer/count of the previous query will also be part
        of the current query (coz current query is larger than prev)
        and therefore, for every query, we'll need to find out how many
        extra (over the previous) elements will be included.

        This is a tricky and a smart solution.
    */
    private int[] optimal(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;

        int[][] qwithindex = new int[k][2];

        for (int i=0; i<k; i++) {
            qwithindex[i][0] = queries[i];
            qwithindex[i][1] = i;
        }
        Arrays.sort(qwithindex, (a1, a2) -> a1[0] - a2[0]);
        int[][] visited = new int[m][n];

        int[] ans = new int[k];
        PriorityQueue<int[]> minHeap
                = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        minHeap.add(new int[]{grid[0][0], 0, 0});
        visited[0][0] = -1;

        int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        int cnt = 0;

        for (int i=0; i<k; i++) {
            while (!minHeap.isEmpty()) {
                if (minHeap.peek()[0] >= qwithindex[i][0]) break;
                int[] cell = minHeap.remove();
                cnt += 1;

                for (int[] dir: dirs) {
                    int nextI = cell[1] + dir[0];
                    int nextJ = cell[2] + dir[1];

                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == -1) continue;

                    visited[nextI][nextJ] = -1;
                    minHeap.add(new int[] { grid[nextI][nextJ], nextI, nextJ });
                }

            }
            int idx = qwithindex[i][1];
            ans[idx] += cnt;
        }

        return ans;

    }

    private int[] bruteBFS(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;

        int[] ans = new int[k];
        for (int i=0; i<k; i++) {
            int[][] visited = new int[m][n];
            ans[i] = bfs(grid, queries, visited, queries[i]);
        }

        return ans;
    }

    private int bfs(int[][] grid, int[] queries, int[][] visited, int max) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
        q.addLast(new int[] {0, 0});
        visited[0][0] = -1;

        int points = 0;
        while (!q.isEmpty()) {
            int[] cell = q.removeFirst();
            if (grid[cell[0]][cell[1]] >= max) continue;

            points += 1;

            for (int[] dir: dirs) {
                int nextI = cell[0] + dir[0];
                int nextJ = cell[1] + dir[1];

                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ] == -1) continue;

                visited[nextI][nextJ] = -1;
                q.addLast(new int[] { nextI, nextJ });
            }
        }

        return points;
    }

    private int[] bruteDFS(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;

        int[] ans = new int[k];
        for (int i=0; i<k; i++) {
            int[][] visited = new int[m][n];
            ans[i] = dfs(grid, queries, m, n, 0, 0, visited, queries[i]);
        }

        return ans;
    }

    private int dfs(int[][] grid, int[] queries, int m, int n, int i, int j, int[][] visited, int max) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] >= max || visited[i][j] != 0) return 0;

        visited[i][j] = -1;
        int up = 1 + dfs(grid, queries, m, n, i-1, j, visited, max)
                + dfs(grid, queries, m, n, i+1, j, visited, max)
                + dfs(grid, queries, m, n, i, j-1, visited, max)
                + dfs(grid, queries, m, n, i, j+1, visited, max);

        return up;
    }
}
