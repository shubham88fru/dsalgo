package ptrn.matrices;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/shortest-bridge/
//@check - https://www.youtube.com/watch?v=GSE0-dReL6Y&t=535s&ab_channel=codestorywithMIK
public class ShortestBridge {
    public int shortestBridge(int[][] grid) {
        return shortest(grid);
    }
    /**
     The Intuition for this problem is that
     BFS gives us the shortest path from one to another point.
     Dijkstra would be an overkill for this problem coz, there
     really no weight to any edge, i.e. all edges are equally weighted.
     And so, a simple BFS can be used to reach from a source to destination
     in shortest path.

     Idea is that, if we are able to find the shortest path from the first
     island to the second island, basically all the zeros on that path will
     the zeros that need to flipped.

     Intuition by mik but coded by me.
     */
    private int shortest(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //First find the first island using a DFS.
        Deque<int[]> q = new ArrayDeque<>();
        for (int i=0; i<m; i++) {
            boolean firstFound = false;
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, m, n, q); //end as soon as we find the first island.
                    firstFound = true;
                    break;
                }
            }
            if (firstFound) break;
        }

        //Once we have the first island, we'll know run a BFS
        //from each nodes of the first island and try to reach the
        //second island. Each time we reach the second island, we'll
        //caculate the dist with the shortest distance seen so far.
        int minDist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            int x = node[0];
            int y = node[1];

            //since ATQ, there are just two islands in the grid,
            //if during BFS we find a node with value 1, means that
            //node is certainly part of the second island.
            if (
                    (x+1 < m && grid[x+1][y] == 1) ||
                            (x-1 >= 0 && grid[x-1][y] == 1) ||
                            (y+1 < n && grid[x][y+1] == 1) ||
                            (y-1 >= 0 && grid[x][y-1] == 1)
            ) {
                minDist = Math.min(minDist, node[2]);
            } else {
                //Otherwise, record the distance and move to next node.
                if ((x+1 < m && grid[x+1][y] == 0)) {
                    grid[x+1][y] = -1;
                    q.addLast(new int[] {x+1, y, node[2]+1});
                }

                if ((x-1 >= 0 && grid[x-1][y] == 0)) {
                    grid[x-1][y] = -1;
                    q.addLast(new int[] {x-1, y, node[2]+1});
                }

                if ((y+1 < n && grid[x][y+1] == 0)) {
                    grid[x][y+1] = -1;
                    q.addLast(new int[] {x, y+1, node[2]+1});
                }

                if ((y-1 >= 0 && grid[x][y-1] == 0)) {
                    grid[x][y-1] = -1;
                    q.addLast(new int[] {x, y-1, node[2]+1});
                }
            }
        }

        return minDist;
    }

    private void dfs(int[][] grid, int i, int j, int m, int n, Deque<int[]> q) {
        if (
                i >= m || i < 0 ||
                        j >= n || j < 0 ||
                        grid[i][j] == -1 ||
                        grid[i][j] == 0
        ) return;

        grid[i][j] = -1;
        q.addLast(new int[] {i, j, 0}); //while moving over the first island, add each node to q.
        dfs(grid, i-1, j, m, n, q);
        dfs(grid, i+1, j, m, n, q);
        dfs(grid, i, j-1, m, n, q);
        dfs(grid, i, j+1, m, n, q);
    }
}
