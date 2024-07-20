package lc_potd;

//@link - https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/description/
//@check - https://www.youtube.com/watch?v=jWTcK-BaLEk&ab_channel=AryanMittal
public class FindValidMatrixGivenRowAndColSums {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        /**
         Important observation to note for this problem is that
         the values in rowSum and colSum can't just be any arbitrary
         values. Since they represent the sum of each row and each
         column respectively, the sum of rowSum and sum of colSum
         must be same because their sums are ultimately the sum
         of the entire matrix. i.e. sum(rowSum) == sum(colSum)

         Following is my soln but influenced by mittal's explanation.
         In case of doubt - @check.
         */
        int m = rowSum.length;
        int n = colSum.length;
        int[][] ans = new int[m][n];

        for (int i=0; i<m; i++) {
            int rsum = rowSum[i];
            for (int j=0; j<n; j++) {
                if (rsum <= colSum[j]) {
                    ans[i][j] = rsum;
                    colSum[j] -= rsum;
                    rsum = 0;
                } else {
                    ans[i][j] = colSum[j];
                    rsum -= colSum[j];
                    colSum[j] = 0;
                }
            }
        }

        return ans;
    }
}
