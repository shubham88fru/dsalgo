package lc_challenges;

//@link - https://leetcode.com/problems/score-after-flipping-matrix/
//@check - https://www.youtube.com/watch?v=1c5HnSKNABA&ab_channel=AryanMittal
public class ScoreAfterFlippingMatrix {
    public int matrixScore(int[][] grid) {
        //Couldn't solve all by myself.
        //Check -
        //Sum of rows will be maximised when individual rows are maximum.
        //In binary representation, a num becomes larger as it has one to the
        //MSB side.
        //First digits (MSB) must always be made one. This is because,
        //is a binary representation, the contibution (1*2^(m-1)) of the MSB
        //is larger than sum of contributions from the rest digits. That is,
        //even if we make all digits except the MSB as one, the resultant num will be smaller
        //than the num that we'll get after keeping MSB as one and rest as 0.
        //Therefore, we first iterate rowwise, and for each row, we'll check if MSB is zero,
        //if it is, we'll blindly flip the row, so that MSB in the row becomes one.

        //By this step, we'll have ensured that first column is all ones.
        //And so, we'll start iterating columns 1 and later. For each column,
        //we'll now check if one count in the column is lesser than zero count,
        //if it is, then we flip the colum.

        int m = grid.length;
        int n = grid[0].length;

        //1. Make MSBs in each row one.
        for (int i=0; i<m; i++) {
            int[] row = grid[i];
            if(grid[i][0] == 0) {
                for (int j=0; j<n; j++) grid[i][j] = 1-grid[i][j]; //flip: 1-->0, 0-->1
            }
        }

        //2. Calculate one counts in each column.
        int[] ones = new int[n];
        for (int j=0; j<n; j++) {
            int count = 0;
            for (int i=0; i<m; i++) {
                if (grid[i][j] == 1) count += 1;
            }
            ones[j] = count;
        }

        //3. Iterate from column 1 (we know that column 0 is full 1s by now due to step 1.)
        //and check if one count in the column is less than zero count. If it is, flip the column.
        for (int j=1; j<n; j++) {
            if (ones[j] < m-ones[j]) { //ones + zeros = m --> zeros = m-ones
                for (int i=0; i<m; i++) {
                    grid[i][j] = 1-grid[i][j];
                }
            }
        }



        //4. Sum the rows.
        int sum = 0;
        for (int i=0; i<m; i++) {
            sum += b2d(grid[i]);
        }

        return sum;
    }

    //binary to decimal.
    private int b2d(int[] row) {
        int pow = 0;
        int d = 0;
        for (int i=row.length-1; i>=0; i--) {
            d += ((int)Math.pow(2, pow))*row[i];
            pow += 1;
        }

        return d;
    }
}
