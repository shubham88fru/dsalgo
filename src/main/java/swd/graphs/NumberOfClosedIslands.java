package swd.graphs;

//@link - https://leetcode.com/problems/number-of-closed-islands/description/
public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int closedIslands = 0;

        //ATQ: 1 - water, 0 - land.
        //Since the question doesn't mention what lies otside the
        //grid, we have to assume that the islands which touch the edge
        //are not surrounded by water on the side outside the grid. Therefore,
        //a completely close island cannot lie on the edges. Below two loops
        //find the islands that touch the edges and sink them (ie. mark them 1)
        //so that we're  only left with islands in the interior, which will be the
        //actual islands that will be completely surronded by 1.
        for (int currCol = 0; currCol < n; currCol += 1) {
            if (grid[0][currCol] == 0) {
                sinkIslandsOnEdge(grid, m, n, 0, currCol, visited);
            }

            if (grid[m-1][currCol] == 0) {
                sinkIslandsOnEdge(grid, m, n, m-1, currCol, visited);
            }
        }

        for (int currRow = 1; currRow < m-1; currRow += 1) {
            if (grid[currRow][0] == 0) {
                sinkIslandsOnEdge(grid, m, n, currRow, 0, visited);
            }

            if (grid[currRow][n-1] == 0) {
                sinkIslandsOnEdge(grid, m, n, currRow, n-1, visited);
            }
        }

        //Once we've sinked (marked 1) the islands touching edges,
        //we'll now be only left with islands in the interior, which will
        //be completely closed islands. Note: starting the loop only
        //from interior, because at this point, the're no point of looking
        //at the edges.
        for (int currCol = 1; currCol < n-1; currCol += 1) {
            for (int currRow = 1; currRow < m-1; currRow += 1) {
                //Assuming each position in the matrix is a vertex
                //in the graph, run a dfs from each vertex if that vertex
                //is not already part of an already accounted component.
                //No. of islands will be equal to no. of disconnected components
                if (visited[currRow][currCol] != true && grid[currRow][currCol] != 1) {
                    dfs(grid, m, n, currRow, currCol, visited);
                    closedIslands += 1;
                }
            }
        }

        return closedIslands;
    }

    private void sinkIslandsOnEdge(int[][] grid, int m, int n, int i, int j, boolean[][] visited) {
        //out of bounds
        if (i < 0 || i >= m || j < 0 || j >= n) return;

        //already visited or a water('1')
        if (visited[i][j] || grid[i][j] == 1) return;

        visited[i][j] = true;
        grid[i][j] = 1;

        int row, col;

        //Each vertex is connected to up, down, left and right.
        //up
        row = i-1;
        col = j;
        dfs(grid, m, n, row, col, visited);

        //down
        row = i+1;
        col = j;
        dfs(grid, m, n, row, col, visited);

        //right
        row = i;
        col = j+1;
        dfs(grid, m, n, row, col, visited);

        //left
        row = i;
        col = j-1;
        dfs(grid, m, n, row, col, visited);
    }

    //DFS on a graph.
    private void dfs(int[][] grid, int m, int n, int i, int j, boolean[][] visited) {
        //out of bounds
        if (i < 0 || i >= m || j < 0 || j >= n) return;

        //already visited or a water('1')
        if (visited[i][j] || grid[i][j] == 1) return;

        visited[i][j] = true;

        int row, col;

        //Each vertex is connected to up, down, left and right.
        //up
        row = i-1;
        col = j;
        dfs(grid, m, n, row, col, visited);

        //down
        row = i+1;
        col = j;
        dfs(grid, m, n, row, col, visited);

        //right
        row = i;
        col = j+1;
        dfs(grid, m, n, row, col, visited);

        //left
        row = i;
        col = j-1;
        dfs(grid, m, n, row, col, visited);
    }
}
