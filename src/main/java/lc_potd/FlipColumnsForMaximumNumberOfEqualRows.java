package lc_potd;

//@link - https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/
//@check - https://www.youtube.com/watch?v=SOI-ZzYemoc&t=1267s&ab_channel=codestorywithMIK
public class FlipColumnsForMaximumNumberOfEqualRows {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        return miksol1(matrix);
    }

    private int miksol1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxRows = 0;

        for (int[] currRow: matrix) {
            int[] inverted = new int[n];

            for (int col=0; col<n; col += 1) {
                inverted[col] = (currRow[col] == 0) ? 1: 0; //flip
            }

            int count = 0;
            for (int[] row: matrix) {
                boolean similarFound = true;
                boolean invertedFound = true;
                for (int i=0; i<n; i++) {
                    if (row[i] != currRow[i]) {
                        similarFound = false;
                    }

                    if (row[i] != inverted[i]) {
                        invertedFound = false;
                    }
                }

                if (similarFound || invertedFound) count += 1;
            }

            maxRows = Math.max(maxRows, count);
        }

        return maxRows;
    }
}
