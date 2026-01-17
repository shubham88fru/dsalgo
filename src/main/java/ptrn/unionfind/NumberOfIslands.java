package ptrn.unionfind;

//@link - https://leetcode.com/problems/number-of-islands/description/
//@strvr - https://takeuforward.org/data-structure/number-of-distinct-islands/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5996925221601280
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
         return revise(grid); //using dfs.

    }

    //2. Using union find.
    //Edctv's soln is based on union find, which, as always, is hell confusing.
    //Approach -

    /**
     To recap, the solution to this problem can be divided into the following three parts:

         1. Count all occurrences of cell 1s in the grid.

         2. Traverse the grid and if a cell 1 is encountered,
         perform the union operation between the neighboring cell
         1s to connect them into a single component.

         3. Decrement count by 1 only if the current element and its
         neighboring cell 1s aren’t already part of a connected component.
     */

    //1. Using simple dfs
    private int revise(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int islands = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1') {
                    mark(grid, i, j, m, n);
                    islands += 1;
                }
            }
        }

        return islands;
    }

    private void mark(char[][] grid, int i, int j, int m, int n) {

        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;

        grid[i][j] = '#';
        mark(grid, i-1, j, m, n);
        mark(grid, i, j+1, m, n);
        mark(grid, i+1, j, m, n);
        mark(grid, i, j-1, m, n);
    }
}
