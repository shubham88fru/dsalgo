package lc_potd;

//@link - https://leetcode.com/problems/increment-submatrices-by-one/description/?
//@check - https://www.youtube.com/watch?v=GluIvp5NaGw
public class IncrementSubmatricesByOne {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        // return brute(n, queries);
        return optimal(n, queries);
    }

    /**
     Using difference arrays technique
     on 2D matrix.
     */
    private int[][] optimal(int n, int[][] queries) {
        int[][] mat = new int[n][n];

        for (int[] query: queries) {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            for (int i=r1; i<=r2; i++) {
                mat[i][c1] += 1;

                if (c2 + 1 < n) mat[i][c2+1] -= 1;
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=1; j<n; j++) {
                mat[i][j] += mat[i][j-1]; //cumulative sum
            }
        }

        return mat;
    }

    private int[][] brute(int n, int[][] queries) {
        int[][] mat = new int[n][n];

        for (int[] query: queries) {
            int r1 = query[0];
            int c1 = query[1];
            int r2 = query[2];
            int c2 = query[3];

            for (int i=r1; i<=r2; i++) {
                for (int j=c1; j<=c2; j++) {
                    mat[i][j] += 1;
                }
            }
        }

        return mat;
    }
}
