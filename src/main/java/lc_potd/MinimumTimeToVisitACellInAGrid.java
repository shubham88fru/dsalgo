package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/
//@check - https://www.youtube.com/watch?v=2H9CVEmmMUM&ab_channel=codestorywithMIK
public class MinimumTimeToVisitACellInAGrid {
    public int minimumTime(int[][] grid) {
        return dijks(grid);
    }

    /*
       This solution is completely based on Mik's
       explanation.
     */
    private int dijks(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //If can't move from (0,0), there's no way to
        //move to the end.
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

        int[][] costs = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 && j==0) costs[i][j] = 0;
                else costs[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<PqNode> pq = new PriorityQueue<>((n1, n2) -> n1.cost-n2.cost);
        pq.add(new PqNode(0, 0, 0));
        int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, { -1, 0}};

        while (!pq.isEmpty()) {
            PqNode curr = pq.remove();
            int currI = curr.i;
            int currJ = curr.j;
            int currTime = curr.cost;

            //These will be neighbors of curr node.
            //Note, we didn't separately form a graph
            //for this question. Wasnt' needed coz, for
            //any given node, we only have four directions
            //where the neighbour will be, and so we already
            //have the neihbour info.
            for (int[] dir: dirs) {
                int nextI = dir[0] + currI;
                int nextJ = dir[1] + currJ;

                if (nextI < 0 || nextI >= m) continue;
                if (nextJ < 0 || nextJ >= n) continue;

                int newTime = currTime;
                if (currTime < grid[nextI][nextJ]) {
                    int diff = grid[nextI][nextJ] - currTime;
                    if (diff%2 == 0) newTime = grid[nextI][nextJ];
                    else newTime = grid[nextI][nextJ]-1;
                }

                if (newTime +1 < costs[nextI][nextJ]) {
                    costs[nextI][nextJ] = newTime+1;
                    pq.add(new PqNode(costs[nextI][nextJ], nextI, nextJ));
                }
            }
        }

        return costs[m-1][n-1];
    }
}
