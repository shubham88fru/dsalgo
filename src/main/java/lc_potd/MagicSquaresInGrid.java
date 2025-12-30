package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/magic-squares-in-grid/
public class MagicSquaresInGrid {
    public int numMagicSquaresInside(int[][] grid) {
        return brute(grid);
    }

    //approach 1
    private int brute(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxRow = m-3;
        int maxCol = n-3;

        int count = 0;
        for (int i=0; i <= maxRow; i++) {
            for (int j=0; j <= maxCol; j++) {
                if (isMagicGrid(i, j, grid)) count += 1;
            }
        }

        return count;
    }

    //approach 2
    private int brute2(int[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        for (int i=0; i<m-2; i+=1) {
            for (int j=0; j<n-2; j+=1) {
                if (isMagic(grid, i, j)) count += 1;
            }
        }

        return count;
    }

    private boolean isMagic(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        Set<Integer> st = new HashSet<>();
        for (int ii=i; ii<i+3; ii++) {
            for (int jj=j; jj<j+3; jj++) {
                if (st.contains(grid[ii][jj])) return false;
                if (grid[ii][jj] < 1 || grid[ii][jj] > 9) return false;
                st.add(grid[ii][jj]);
            }
        }

        int r1 = grid[i][j] + grid[i][j+1] + grid[i][j+2];
        int r2 = grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2];
        int r3 = grid[i+2][j] + grid[i+2][j+1] + grid[i+2][j+2];

        int c1 = grid[i][j] + grid[i+1][j] + grid[i+2][j];
        int c2 = grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1];
        int c3 = grid[i][j+2] + grid[i+1][j+2] + grid[i+2][j+2];

        int d1 = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
        int d2 = grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j];

        return r1 == r2 && r2 == r3 && r3 == c1 && c1 == c2 && c2 == c3 && c3 == d1 && d1 == d2;
    }

    private boolean isMagicGrid(int i, int j, int[][] grid) {
        Set<Integer> gridNums = new HashSet<>();

        int prevRowSum = 0;
        for (int ii=i; ii<i+3; ii++) {
            int sum = 0;
            for (int jj=j; jj<j+3; jj++) {
                if (grid[ii][jj] > 9 || grid[ii][jj] <= 0) return false;
                sum += grid[ii][jj];
                gridNums.add(grid[ii][jj]);
            }
            if (prevRowSum == 0) prevRowSum = sum;
            else if (sum != prevRowSum) return false;
        }

        if (gridNums.size() < 9) return false;

        int prevColSum = 0;
        for (int jj=j; jj<j+3; jj++) {
            int sum = 0;
            for (int ii=i; ii<i+3; ii++) {
                sum += grid[ii][jj];
            }
            if (prevColSum == 0) prevColSum = sum;
            else if (sum != prevColSum) return false;
        }



        int fDiagSum = grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2];
        int sDiagSum = grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j];

        if (fDiagSum != sDiagSum) return false;

        return (prevColSum == prevRowSum) && (prevRowSum == fDiagSum);


    }
}
