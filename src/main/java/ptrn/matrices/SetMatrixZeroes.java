package ptrn.matrices;

//@link - https://leetcode.com/problems/set-matrix-zeroes/
//@strvr - https://takeuforward.org/data-structure/set-matrix-zero/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5199127820107776
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        //setZeroesBrute(matrix);
        //setZeroBetter(matrix);
        setZeroOptimal(matrix);
    }

    //1) Optimal approach. T: O(m*n), S: O(1)
    private void setZeroOptimal(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0HasAZero = 1;

        //we'll use the cells of 1st column
        //to indicate if the corresponding row has a zero or not.
        //Similarly, we'll use the cells of 1st row to indicate if
        //the corresponding columns have a zero of not.
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; //row i has a zero.

                    if (j!=0)
                        matrix[0][j] = 0; //col j has a zero.
                    else
                        col0HasAZero = 0;
                }
            }
        }

        /**
         * Note that, even though at this point, we know
         * the values of matrix[0][0] and col0HasAZero, we
         * still won't update the first row and first col
         * with zeros (if repective vals are zeros). That is
         * so because, the values in first row and col are used
         * for lookup whilst updating the rest of matrix. If
         * we set zeros in first row and col right away, entire
         * matrix will end up being zero. So, we wait to update
         * the first row and col with zeros (if needed) till the end.
         */

        //start iterating from row 1 and col 1
        //NOTE: row 0 and col 0 are now special.
        //they'll be used for lookup and solving
        //the rest of matrix whether it should be
        //marked zero or not.
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                //if current row has a zero anywhere
                //of if curr col has a zero anywhere,
                //the entire row or col respectively will
                //be zero.
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }

        //now solve the row 0 seperately.
        if (matrix[0][0] == 0) {
            //if true, then everything in first row
            //will be zero.
            for (int j=1; j<n; j++) {
                matrix[0][j] = 0;
            }
        }

        //and then solve col 0.
        if (col0HasAZero == 0) {
            //if true then everyingthing in the first
            //column will be zero.
            for (int i=0; i<m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    //2) Better approach. T: O(m*n), S: O(m+n)
    private void setZeroBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //if a index is 1, means that row
        //or col has a zero in the matrix.
        boolean[] rowHasAZero = new boolean[m];
        boolean[] colHasAZero = new boolean[n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    rowHasAZero[i] = true; //row i has a zero.
                    colHasAZero[j] = true; //col j has a zero.
                }
            }
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                //if current row has a zero anywhere
                //of if curr col has a zero anywhere,
                //the entire row or col respectively will
                //be zero.
                if (rowHasAZero[i] || colHasAZero[j]) matrix[i][j] = 0;
            }
        }
    }

    //3) Brute-force approach. T: O((m*n)^2), S: O(1)
    private void setZeroesBrute(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //Iterate through the matrix,
        //when we see a zeros, we mark
        //its correspoinding row and colums with
        //say -1.
        for (int i=0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (matrix[i][j] == 0)
                    mark(i, j, matrix);
            }
        }

        //Once all marking done,
        //iterate again and replace all
        //-1s with zero.
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }

    //4) Brute force 2.
    //One more brute force approach which has a workaround (of -1 issue) for the
    //above brute force approach is to use a second matrix. First, copy all the
    //elements of first matrix to the second matrix. Then iterate the first (og)
    //matrix and if we encounter a zero, set entire corresponding row and column in
    //the copied matrix to zero. Once done iterating the first matrix, copy all
    //elements of second matrix to first matrix, which will be our answer.
    //However, ATQ, this question needs to be solved in O(1) space, so this isn't
    //the desired approach.

    private void mark(int i, int j, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int k=0; k<m; k++) {
            for (int l=0; l<n; l++) {
                //mark only if curr elemtn in the row/col
                //is not a zero. Otherwise, we'll screw up a zero
                //which was supposed to be considered originally.
                if ((k==i || l==j) && matrix[k][l] != 0) matrix[k][l] = -1;
            }
        }
    }
}
