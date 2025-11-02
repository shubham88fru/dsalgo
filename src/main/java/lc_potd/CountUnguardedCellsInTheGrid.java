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

    //revise
    private int revise(int m, int n, int[][] guards, int[][] walls) {
        int[][] mat = new int[m][n];

        for (int[] guard: guards) {
            mat[guard[0]][guard[1]] = 1;
        }

        for (int[] wall: walls) {
            mat[wall[0]][wall[1]] = 2;
        }

        for (int[] guard: guards) {
            markLeft(mat, guard[0], guard[1]-1);
            markUp(mat, guard[0]-1, guard[1]);
            markRight(mat, guard[0], guard[1]+1);
            markDown(mat, guard[0]+1, guard[1]);
        }

        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (mat[i][j] == 0) count += 1;
            }
        }

        return count;
    }

    private void markLeft(int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;

        for (int col=j; col >=0; col--) {
            if (mat[i][col] == 2 || mat[i][col] == 1) break;
            mat[i][col] = -1;
        }
    }

    private void markUp(int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;

        for (int row=i; row >=0; row--) {
            if (mat[row][j] == 2 || mat[row][j] == 1) break;
            mat[row][j] = -1;
        }
    }

    private void markRight(int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;

        for (int col=j; col < n; col++) {
            if (mat[i][col] == 2 || mat[i][col] == 1) break;
            mat[i][col] = -1;
        }
    }

    private void markDown(int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;

        for (int row=i; row < m; row++) {
            if (mat[row][j] == 2 || mat[row][j] == 1) break;
            mat[row][j] = -1;
        }
    }
}
