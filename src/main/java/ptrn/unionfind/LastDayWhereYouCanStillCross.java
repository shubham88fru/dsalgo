package ptrn.unionfind;

//@link - https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4918162916376576
public class LastDayWhereYouCanStillCross {
    public int latestDayToCross(int row, int col, int[][] cells) {
         return pass1(row, col, cells);
         //return dsu(row, col, cells);
    }

    /**
     1.
     This is my initial intuition apart from
     brute force. Mik also had the same approach.
     */
    private int pass1(int row, int col, int[][] cells) {
        int n = cells.length, ans = 1, l = 0, r = n-1;

        while (l <= r) {
            int mid = l + (r-l)/2;
            int[][] island = new int[row][col];
            fill(island, cells, mid);
            boolean possible = false;
            for (int j=0; j<col; j++) {
                if (island[0][j] == 0 && dfs(island, row, col, 0, j)) {
                    possible = true;
                    break;
                }
            }
            if (possible) {
                l = mid + 1;
                ans = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    //2. DSU Soln. Copied from edctv.
    /**
     * As usual, again copy pasta from edctv. @check, has good visual explanation for the problem.
     * It also has explanation for solving this problem using a combination of Binary search
     * and BFS or DFS.
     */
    private int dsu(int rows, int cols, int[][] waterCells) {
        // create a variable to keep track of the number of days
        int day = 0;

        // create the matrix that needs to be crossed
        int[][] matrix = new int[rows][cols];

        // create the two virtual nodes, one before the first column
        // and the other after the last column of the matrix
        int leftNode = 0;
        int rightNode = rows * cols + 1;

        // specify the directions where water can move - 8 directions.
        int[][] waterDirections = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // convert the waterCells from 1-based to 0-based array for the convenience
        int[][] convertedWaterCells = new int[waterCells.length][2];
        for (int i = 0; i < waterCells.length; i++) {
            convertedWaterCells[i] = new int[]{waterCells[i][0] - 1, waterCells[i][1] - 1};
        }

        // initialize the UnionFind5 object, this will create the
        // disjoint set union datastructure, an array - reps
        UnionFind5 uf = new UnionFind5(rows * cols + 2);

        // On each day, one cell of the matrix will get flooded
        for (int[] cell : convertedWaterCells) {
            int row = cell[0];
            int col = cell[1];

            // change the matrix's cell from land (0) to water (1)
            matrix[row][col] = 1;

            // check if the recently flooded cell connects with any of the existing water cells
            for (int[] direction : waterDirections) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (withinBounds(newRow, newCol, rows, cols) && matrix[newRow][newCol] == 1) {
                    uf.union(findIndex(row, col, cols), findIndex(newRow, newCol, cols));
                }
            }

            if (col == 0) {
                uf.union(findIndex(row, col, cols), leftNode);
            }
            if (col == cols - 1) {
                uf.union(findIndex(row, col, cols), rightNode);
            }

            // check if we got a series of connected water cells
            // from the left to the right side of the matrix
            if (uf.find(leftNode) == uf.find(rightNode)) {
                break;
            }
            day++;
        }
        return day;
    }

    //3. Brute force.
    //Flood each day and for each day
    //traverse the grid to see a path exists.


    //Helpers
    private boolean dfs(int[][] island, int m, int n, int i, int j) {
        if (i >= m) return true;

        if (i < 0 || j < 0 || j >= n || island[i][j] != 0) return false;

        island[i][j] = -1;
        boolean up = dfs(island, m, n, i-1, j);
        boolean right = dfs(island, m, n, i, j+1);
        boolean down = dfs(island, m, n, i+1, j);
        boolean left = dfs(island, m, n, i, j-1);

        return up || right || down || left;
    }

    private void fill(int[][] island, int[][] cells, int idx) {
        for (int i=0; i<=idx; i++) {
            island[cells[i][0]-1][cells[i][1]-1] = 1;
        }
    }

    // maps the index of the element in 2-D matrix to an index of the 1-D array (reps)
    private int findIndex(int currentRow, int currentCol, int cols) {
        return currentRow * cols + (currentCol + 1);
    }

    // checks whether the water cells to be connected are
    // within the bounds of the matrix as per given dimensions
    private boolean withinBounds(int row, int col, int rows, int cols) {
        return col >= 0 && col < cols && row >= 0 && row < rows;
    }
}

class UnionFind5 {
    private int[] reps;

    public UnionFind5(int n) {
        reps = new int[n];

        for (int i = 0; i < n; i++) {
            reps[i] = i;
        }
    }

    public int find(int x) {
        if (reps[x] != x) {
            reps[x] = find(reps[x]);
        }
        return reps[x];
    }

    public void union(int v1, int v2) {
        reps[find(v1)] = find(v2);
    }
}