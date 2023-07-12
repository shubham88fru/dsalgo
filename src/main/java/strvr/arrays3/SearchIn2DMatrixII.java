package strvr.arrays3;

//@link - https://leetcode.com/problems/search-a-2d-matrix-ii/description/
//@strvr - https://takeuforward.org/data-structure/search-in-a-sorted-2d-matrix/
public class SearchIn2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        return searchInRowAndColumnWiseSortedMatrix(matrix, target);
    }

    private boolean searchInRowAndColumnWiseSortedMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        //start from the top right corner (last element in first row)
        int currRow = 0;
        int currCol = n-1;

        //as long as a valid position and target not found..
        //(NOTE: no need to check if currRow < 0 or if currCol < n
        //becaause after the initial value currRow is only getting incremented
        //in the below loop while currCol is only getting decremented.)
        while (currRow < m && currCol >= 0) {

            //if curr position is target, return immediately.
            if (matrix[currRow][currCol] == target) return true;

                //otherwise, if target is smaller than curr element,
                //then then it can only lie to the left of curr element
                //because the elements in the same column and below the curr
                //element will all be greater than the curr element itself (as per question)
                //so they'll difinitely be greter than target also and so no point checking in the
                //current column.
            else if (target < matrix[currRow][currCol]) {
                currCol -= 1;
            } else { //otherwise, target it greater than curr element
                //and so, it can certainly not lie in curr row
                //because all the elments in curr row will be smaller
                //than the curr element as per question.
                currRow += 1;
            }
        }

        return false;
    }
}
