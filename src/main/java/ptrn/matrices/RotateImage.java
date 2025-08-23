package ptrn.matrices;

//@link - https://leetcode.com/problems/rotate-image/description/
//@strvr - https://takeuforward.org/data-structure/rotate-image-by-90-degree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5199127820107776
public class RotateImage {
    public void rotate(int[][] matrix) {
        rotate90(matrix);
    }

    //1) Optimal approach: T: O(N^2), S: O(1)
    private void rotate90(int[][] matrix) {
        /**
         * Note, as a general rule, remember that
         * transposing a matrix and then reversing
         * each row, effectively rotates the matrix
         * by 90 decree in clockwise direction.
         * How would you then rotate 90 decree in
         * anticlockwise direction - ponder.
         */
        //transpose the matrix
        transpose(matrix);

        //and then reverse each row.
        reverseEachRow(matrix);
    }

    //transpose of matrix: each row becomes a column and the columns become row.
    private void transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        /*
        * Note that this works inplace
        * only because for this q, the
        * matrix is given to be n*n. For
        * general matrices (n*m), we'll need
        * a separate transpose matrix of size
        * m*n and copy the i,j from og matrix
        * to j,i of the transpose matrix.
        * */
        for (int i = 0; i<m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reverseEachRow(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            int left = 0;
            int right = n-1;

            while (left <= right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left += 1;
                right -= 1;
            }
        }
    }

    //2) Check edctv illustrations for a different O(N^2) approach.

    //3) Brute force: T: O(N^2), S: O(N^2)
    //Take one more matrix of same dimension.
    //copy 1st row of orig matrix to last column of new matrix,
    //copy 2nd row of orig matrix to second last column of new matrix,
    //copy 3rd row of orig matrix to 3rd last column of new matrix..
    //..and so on.
}
