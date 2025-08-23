package lc_potd;

//@link - https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii/description/
//@check - https://www.youtube.com/watch?v=DCGX28SMch4&ab_channel=codestorywithMIK
public class FindTheMinimumAreaToCoverAllOnesII {
    public int minimumSum(int[][] grid) {
        return mikssol(grid);
    }

    /**
     Coded by me, completely
     based on mik's explanation.
     */
    private int mikssol(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int result = Integer.MAX_VALUE;

        //Case 1 & 2
        for (int rowSplit = 1; rowSplit < m; rowSplit += 1) {
            for (int colSplit = 1; colSplit < n; colSplit += 1) {
                int top = smallestRect(0, rowSplit, 0, n, grid);
                int bottomLeft = smallestRect(rowSplit, m, 0, colSplit, grid);
                int bottomRight = smallestRect(rowSplit, m, colSplit, n, grid);

                int topLeft = smallestRect(0, rowSplit, 0, colSplit, grid);
                int topRight = smallestRect(0, rowSplit, colSplit, n, grid);
                int bottom = smallestRect(rowSplit, m, 0, n, grid);

                result = Math.min(result,
                        Math.min(top+bottomLeft+bottomRight, topLeft+topRight+bottom));
            }
        }

        for (int rowSplit1 = 1; rowSplit1 < m; rowSplit1 += 1) {
            for (int rowSplit2 = rowSplit1+1; rowSplit2 < m; rowSplit2 += 1) {
                int top = smallestRect(0, rowSplit1, 0, n, grid);
                int middle = smallestRect(rowSplit1, rowSplit2, 0, n, grid);
                int bottom = smallestRect(rowSplit2, m, 0, n, grid);

                result = Math.min(result, top+middle+bottom);
            }
        }

        int[][] rotatedGrid = rotateClockwise(grid);
        m = rotatedGrid.length;
        n = rotatedGrid[0].length;

        for (int rowSplit = 1; rowSplit < m; rowSplit += 1) {
            for (int colSplit = 1; colSplit < n; colSplit += 1) {
                int top = smallestRect(0, rowSplit, 0, n, rotatedGrid);
                int bottomLeft = smallestRect(rowSplit, m, 0, colSplit, rotatedGrid);
                int bottomRight = smallestRect(rowSplit, m, colSplit, n, rotatedGrid);

                int topLeft = smallestRect(0, rowSplit, 0, colSplit, rotatedGrid);
                int topRight = smallestRect(0, rowSplit, colSplit, n, rotatedGrid);
                int bottom = smallestRect(rowSplit, m, 0, n, rotatedGrid);

                result = Math.min(result,
                        Math.min(top+bottomLeft+bottomRight, topLeft+topRight+bottom));
            }
        }

        //Case 3
        for (int rowSplit1 = 1; rowSplit1 < m; rowSplit1 += 1) {
            for (int rowSplit2 = rowSplit1+1; rowSplit2 < m; rowSplit2 += 1) {
                int top = smallestRect(0, rowSplit1, 0, n, rotatedGrid);
                int middle = smallestRect(rowSplit1, rowSplit2, 0, n, rotatedGrid);
                int bottom = smallestRect(rowSplit2, m, 0, n, rotatedGrid);

                result = Math.min(result, top+middle+bottom);
            }
        }

        return result;
    }

    private int[][] rotateClockwise(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] tr = transpose(grid);
        reverseGrid(tr);

        return tr;
    }

    private int[][] transpose(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] tr = new int[n][m];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                tr[j][i] = grid[i][j];
            }
        }

        return tr;
    }

    private void reverseGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int[] row: grid) {
            int l = 0;
            int r = n-1;
            while (l <= r) {
                int temp = row[r];
                row[r] = row[l];
                row[l] = temp;

                l += 1;
                r -= 1;
            }
        }
    }

    private int smallestRect(int is, int ie, int js, int je, int[][] grid) {
        int cs = Integer.MAX_VALUE;
        int ce = -1;
        int rs = Integer.MAX_VALUE;
        int re = -1;

        for (int i=is; i<ie; i++) {
            for (int j=js; j<je; j++) {
                if (grid[i][j] == 1) {
                    cs = Math.min(cs, j);
                    ce = Math.max(ce, j);

                    rs = Math.min(rs, i);
                    re = Math.max(re, i);
                }
            }
        }

        return (Math.abs(cs-ce)+1) * (Math.abs(rs-re)+1);
    }
}
