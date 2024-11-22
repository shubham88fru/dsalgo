package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/
public class CountUnguardedCellsInTheGrid {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] matrix = new int[m][n];

        for (int i=0; i<guards.length; i++) {
            matrix[guards[i][0]][guards[i][1]] = -1;
        }

        for (int i=0; i<walls.length; i++) {
            matrix[walls[i][0]][walls[i][1]] = -2;
        }

        // printMatrix(matrix);

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == -1) {
                    mark(matrix, i, j);
                }
            }
        }


        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) count += 1;
            }
        }

        return count;
    }

    private void mark(int[][] matrix, int i, int j) {
        int jj = j;
        while (jj-1>=0 && ((matrix[i][jj-1] != -1) && (matrix[i][jj-1] != -2))) {
            matrix[i][jj-1] = -3;
            jj -= 1;
        }

        jj = j;
        while ((jj+1 < matrix[0].length) && (matrix[i][jj+1] != -1) && (matrix[i][jj+1] != -2)) {
            matrix[i][jj+1] = -3;
            jj += 1;
        }

        int ii = i;
        while (ii-1>=0 && (matrix[ii-1][j] != -1) && (matrix[ii-1][j] != -2)) {
            matrix[ii-1][j] = -3;
            ii -= 1;
        }

        ii = i;
        while (ii+1<matrix.length && (matrix[ii+1][j] != -1) && (matrix[ii+1][j] != -2)) {
            matrix[ii+1][j] = -3;
            ii += 1;
        }
    }


    private void printMatrix(int[][] matrix) {
        for (int i=0; i<matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
