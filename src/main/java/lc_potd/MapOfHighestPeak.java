package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/map-of-highest-peak/description/
//@check - https://www.youtube.com/watch?v=nQe5OBs0FgQ&t=0s&ab_channel=codestorywithMIK
public class MapOfHighestPeak {
    public int[][] highestPeak(int[][] isWater) {
        /*
            There is no way I could have realized that this
            problem is the same as the 0-1 matrix problem.
            Only when I saw it noted that the problem is
            same as 01 matrix, I wrote this soln.
        */


        // return pass1(isWater);
        return pass2(isWater);
    }

    /*
        Below code is fine but gives TLE.
        Apparently, my understanding of multi-source
        BFS was wrong. Multi source BFS doesn't just
        mean running BFS one-by-one from each source (as I did here).
        Instead it means running BFS simoultanesouly from each source
        (as I did in pass2's bfs2)
     */
    private int[][] pass1(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] updated = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (isWater[i][j] != 1) {
                    updated[i][j] = Integer.MAX_VALUE;
                } else {
                    updated[i][j] = 1;
                }
            }
        }

        Deque<int[]> q = new ArrayDeque<>();

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (isWater[i][j] == 1) {
                    updated[i][j] = 0;
                    bfs(updated, i, j);
                    q.addLast(new int[] { i, j });
                } else isWater[i][j] = -1;
            }
        }
        return updated;
    }

    private int[][] pass2(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        Deque<int[]> q = new ArrayDeque<>();

        int[][] visited = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (isWater[i][j] == 1) {
                    isWater[i][j] = 0;
                    q.addLast(new int[] { i, j });
                } else isWater[i][j] = -1;
            }
        }
        bfs2(isWater, q);
        return isWater;
    }

    private void bfs(int[][] updated, int i, int j) {
        int m = updated.length;
        int n = updated[0].length;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, { -1, 0 }};
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] { i, j });

        int dist = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz > 0) {
                int[] node = q.removeFirst();
                int currI = node[0];
                int currJ = node[1];


                for (int[] dir: dirs) {
                    int nextI = dir[0] + currI;
                    int nextJ = dir[1] + currJ;

                    if (nextI < 0 || nextI >= m || nextJ < 0 ||
                            nextJ >= n || updated[nextI][nextJ] == 1) continue;

                    if (dist < updated[nextI][nextJ]) {
                        updated[nextI][nextJ] = dist;
                        q.addLast(new int[] { nextI, nextJ });
                    }


                }
                sz -= 1;
            }

            dist += 1;
        }

    }

    private void bfs2(int[][] updated, Deque<int[]> q) {
        int m = updated.length;
        int n = updated[0].length;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, { -1, 0 }};

        int dist = 1;
        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            int currI = node[0];
            int currJ = node[1];

            for (int[] dir: dirs) {
                int nextI = dir[0] + currI;
                int nextJ = dir[1] + currJ;

                if (nextI < 0 || nextI >= m || nextJ < 0 ||
                        nextJ >= n || updated[nextI][nextJ] == 0) continue;

                if (updated[nextI][nextJ] == -1) {
                    updated[nextI][nextJ] = updated[currI][currJ] + 1;
                    q.addLast(new int[] { nextI, nextJ });
                }
            }
        }

    }
}
