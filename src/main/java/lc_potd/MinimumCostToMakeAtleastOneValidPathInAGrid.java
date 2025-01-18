package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
//@check - https://www.youtube.com/watch?v=NS3C4W_jJJM&t=2349s&ab_channel=codestorywithMIK
public class MinimumCostToMakeAtleastOneValidPathInAGrid {
    public int minCost(int[][] grid) {
        // return brute(grid);
        return optimal(grid);
    }

    //1) Using dfs/bactracking - TLE
    private int brute(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];

        return backtrack(0, 0, m, n, grid, visited, 0);
    }

    //2) Using Dijkstra
    //Note that, we don't really want to find the shorted length
    //path between (0,0) and (m-1,n-1). However, imagining this as
    //a graph problem, where edge weight between two nodes is 1 if
    //we have to flip source else edge weight is 0, this problem
    //basically boils down to finding the least weight path (i.e. shortest
    //path in terms of weight) from source to destination. And dijkstra is
    //perfect for such problem.
    private int optimal(int[][] grid) {
        return dijkstra(grid);
    }

    //Note in this variant of my dijkstra implementation,
    //i'm not using a visited array.
    private int dijkstra(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] costs = new int[m][n]; //djk gives min cost from s to each d.
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                costs[i][j] = Integer.MAX_VALUE;
            }
        }

        costs[0][0] = 0; //s to s is 0.
        PriorityQueue<Pai224> pq = new PriorityQueue<Pai224>((p1, p2) -> p1.cost-p2.cost);
        pq.add(new Pai224(0, new int[] {0, 0}));

        while (!pq.isEmpty()) {
            Pai224 p = pq.remove();

            int currCost = p.cost;
            int currI = p.loc[0];
            int currJ = p.loc[1];

            int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
            for (int i=0; i<4; i++) {
                int nextI = currI + dirs[i][0];
                int nextJ = currJ + dirs[i][1];

                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
                    continue;
                }

                int edgeCost = 0;
                if ((grid[currI][currJ] == 1 && i != 0) ||
                        (grid[currI][currJ] == 2 && i != 1) ||
                        (grid[currI][currJ] == 3 && i != 2) ||
                        (grid[currI][currJ] == 4 && i != 3) ) {
                    edgeCost = 1;
                }

                if (costs[nextI][nextJ] > currCost + edgeCost) {
                    costs[nextI][nextJ] = currCost + edgeCost;
                    pq.add(new Pai224(costs[nextI][nextJ], new int[] {nextI, nextJ}));

                }
            }
        }

        return costs[m-1][n-1];

    }

    /*
        No matter what value the current grid position has,
        try moving in each direction from the current position
        and keep track of the cost of move.
        Select the min from the 4 directional movements.

        I couldn't have thought of this. I had a hunch to use dfs
        because we'll have to try out all the possibilities, but
        didn't think of ignoring the given direction and trying all
        directions from each cell while taking into account the cost
        of movement.
    */
    private int backtrack(int i, int j, int m, int n, int[][] grid, int[][] visited, int cost) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == -1) return Integer.MAX_VALUE;
        if (i==m-1 && j==n-1) return cost;

        visited[i][j] = -1;

        //up
        int uc = backtrack(i-1, j, m, n, grid, visited, cost+(grid[i][j] == 4 ? 0: 1)); //if moving up when was suposed to move up, no extra cost.

        //down
        int dc = backtrack(i+1, j, m, n, grid, visited, cost+(grid[i][j] == 3 ? 0: 1));

        //left
        int lc = backtrack(i, j-1, m, n, grid, visited, cost+(grid[i][j] == 2 ? 0: 1));

        //right
        int rc = backtrack(i, j+1, m, n, grid, visited, cost+(grid[i][j] == 1 ? 0: 1));

        visited[i][j] = 0;

        return Math.min(uc, Math.min(dc, Math.min(lc, rc)));

    }
}

class Pai224 {
    int cost;
    int[] loc;

    public Pai224(int cost, int[] loc) {
        this.cost = cost;
        this.loc = loc;
    }
}