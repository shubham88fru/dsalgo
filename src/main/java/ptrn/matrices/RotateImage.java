package strvr.arrays2;

//@link - https://leetcode.com/problems/rotate-image/description/
//@strvr - https://takeuforward.org/data-structure/rotate-image-by-90-degree/
public class RotateImage {
    public void rotate(int[][] matrix) {
        rotate90(matrix);
    }

    //1) Optimal approach: T: O(N^2), S: O(1)
    private void rotate90(int[][] matrix) {
        //transpose the matrix
        transpose(matrix);

        //and then reverse each row.
        reverseEachRow(matrix);
    }

    //transpose of matrix: each row becomes a column and the columns become row.
    private void transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

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

    //2) Brute force: T: O(N^2), S: O(N^2)
    //Take one more matrix of same dimension.
    //copy 1st row of orig matrix to last column of new matrix,
    //copy 2nd row of orig matrix to second last column of new matrix,
    //copy 3rd row of orig matrix to 3rd last column of new matrix..
    //..and so on.
}
