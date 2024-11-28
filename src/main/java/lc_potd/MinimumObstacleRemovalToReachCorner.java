package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
//@check - https://www.youtube.com/watch?v=UQogvDBoHe4&t=1138s
public class MinimumObstacleRemovalToReachCorner {
    public int minimumObstacles(int[][] grid) {
        return dijks(grid);
    }

    /*
        Dijkstra soln inspired by Mik's explanation.
        Mik said, that since weights are fix (either 1 or 0),
        this problem can be solved using simple BFS also.
     */
    private int dijks(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

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
            int currCost = curr.cost;

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

                //note grid[i][j] = 1 if i,j is an obstacle.
                //here, we say that if the dest node is an obstacle,
                //the edge from curr (currI, currJ) to dest (nextI, nextJ) has a weight 1,
                //otherwise 0.
                if (currCost + grid[nextI][nextJ] < costs[nextI][nextJ]) {
                    costs[nextI][nextJ] = currCost + grid[nextI][nextJ];
                    pq.add(new PqNode(costs[nextI][nextJ], nextI, nextJ));
                }
            }
        }

        return costs[m-1][n-1];
    }
}

class PqNode {
    int cost;
    int i;
    int j;

    public PqNode(int cost, int i, int j) {
        this.cost = cost;
        this.i = i;
        this.j = j;
    }
}