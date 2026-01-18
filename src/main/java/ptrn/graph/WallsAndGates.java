package ptrn.graph;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://www.lintcode.com/problem/663/
//      - https://leetcode.com/problems/walls-and-gates/description
public class WallsAndGates {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void wallsAndGates(int[][] rooms) {
        // write your code here
        pass1(rooms);
    }

    private void pass1(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;

        Deque<int[]> q = new ArrayDeque<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    q.addLast(new int[]{i, j, 0});
                }
            }
        }

        bfs(rooms, q);
    }

    private void bfs(int[][] rooms, Deque<int[]> q) {
        int m = rooms.length;
        int n = rooms[0].length;

        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            int i = node[0];
            int j = node[1];
            int dist = node[2];


            for (int[] dir: dirs) {
                int nextI = i + dir[0];
                int nextJ = j + dir[1];
                if (nextI <0 || nextI >=m || nextJ < 0 || nextJ >= n || rooms[nextI][nextJ] == -1 || rooms[nextI][nextJ] == 0) {
                    continue;
                }

                if (rooms[nextI][nextJ] > dist + 1) {
                    rooms[nextI][nextJ] = Math.min(rooms[nextI][nextJ], dist+1);
                    q.addLast(new int[] {nextI, nextJ, dist+1});
                }
            }
        }
    }
}
