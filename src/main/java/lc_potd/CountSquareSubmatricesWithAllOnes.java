package lc_potd;

//@link - https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
//@check - https://www.youtube.com/watch?v=y3kdowdyNMM&ab_channel=codestorywithMIK
public class CountSquareSubmatricesWithAllOnes {
    /**
        Coded by me but intuition and approach by mik.
        Mik also showed bottom up approach, so if this is
        a recurring problem for some company - @check.

        @see  swd.dp.MaximalSquare
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Integer[][] memo = new Integer[m][n];

        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 1) {
                    count += dfs(matrix, m, n, i, j, memo);
                }
            }
        }

        return count;
    }

    /*
        For each starting point (i.e. matrix[i][j] == 1), this method gives
        the largest square that the current cell will be part of. Therefore, if
        say, this func returns x, then it means from point i, j on the matrix, we
        can draw a square of size x*x such that all of the elements of the square are 1s.
        This also means, that if we are able to draw a x*x square consisting of all 1s,
        then we will certainly be able to draw squares of size x-1, x-2, x-3...1
        (because they'll all just have 1s in them). Concretely, if this func returns x,
        then we know that the count of squares that can be drawn from current point will
        also be x.
    */
    private int dfs(int[][] matrix, int m, int n, int i, int j, Integer[][] memo) {
        if (i >= m || j >= n || matrix[i][j] == 0) return 0;

        if (memo[i][j] != null) return memo[i][j];

        int onRight = dfs(matrix, m, n, i, j+1, memo); //expand on the right.
        int onDown = dfs(matrix, m, n, i+1, j, memo); //expand down.
        int onDiag = dfs(matrix, m, n, i+1, j+1, memo); //expand diagonally.

        memo[i][j] = 1 + Math.min(onRight, Math.min(onDown, onDiag)); //Math.min because we are looking for a square.

        return memo[i][j];
    }
}
