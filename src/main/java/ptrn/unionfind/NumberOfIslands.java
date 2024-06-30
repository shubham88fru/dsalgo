package ptrn.unionfind;

//@link - https://leetcode.com/problems/number-of-islands/description/
//@strvr - https://takeuforward.org/data-structure/number-of-distinct-islands/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5996925221601280
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
         return sol1(grid); //using dfs.

    }

    //2. Using union find.
    //Edctv's soln is based on union find, which, as always, is hell confusing.

    //1. Using simple dfs
    private int sol1(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int islands = 0;

        //This problem basically boils down to finding
        //no. of disconnected components. Bunch of 1's connected
        //together (up, left, down, right) form one island.
        for (int i = 0; i < m; i += 1) {
            for (int j = 0; j < n; j += 1) {
                //Assuming each position in the matrix is a vertex
                //in the graph, run a dfs from each vertex if that vertex
                //is not already part of an already accounted component.
                //No. of islands will be equal to no. of disconnected components
                if (visited[i][j] != true && grid[i][j] != '0') {
                    dfs(grid, m, n, i, j, visited);
                    islands += 1;
                }
            }
        }
        return islands;
    }

    //DFS on a graph.
    private void dfs(char[][] grid, int m, int n, int i, int j, boolean[][] visited) {
        //out of bounds
        if (i < 0 || i >= m || j < 0 || j >= n) return;

        //already visited or a water('0')
        if (visited[i][j] || grid[i][j] == '0') return;

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
