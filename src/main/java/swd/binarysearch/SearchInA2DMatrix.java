package swd.binarysearch;

//@link - https://leetcode.com/problems/search-a-2d-matrix/description/
public class SearchInA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        return binarySearch1(matrix, target);
    }

    //1) (log(row*col))
    private boolean binarySearch1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        //consider the matrix as a large 1d array
        //and calculate the indexes with that consideration.
        int start  = 0;
        int end = (m * n) - 1; //last element of matrix.
        while (start <= end) {
            int mid = (start + (end-start)/2);

            int map1dRowTo2d = mid / n; //convert 1d row index to 2d row index.
            int map1dColTo2d = mid % n; //convert 1d col index to 2d col index.

            if (matrix[map1dRowTo2d][map1dColTo2d] == target) return true;

            if (target < matrix[map1dRowTo2d][map1dColTo2d]) end = mid - 1;
            else start = mid + 1;
        }

        return false;
    }

    //2) O(row*log(col))
    private boolean binarySearch2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;

        //Binary search in each row, and return as soon as found.
        while (row <= m-1) {
            int[] nums = matrix[row];
            int start = 0;
            int end = n-1;

            while (start <= end) {
                int mid = (start + (end-start)/2);

                if (nums[mid] == target) return true;
                if (target < nums[mid]) end = mid - 1;
                else start = mid + 1;
            }
            row += 1;
        }

        return false;
    }
}
