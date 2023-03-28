package swd.recursionbacktracking;

public class NQueensII {
    public int totalNQueens(int n) {
        return placeQueens(new int[n][n], 0, n);
    }

    private int placeQueens(int[][] board, int i, int size) {

        //If at any point, we happen to have
        //completed all rows, mean we have a possible arrangement.
        if (i == size) return 1;

        //Solve row wise. At every row, we have size no. of options.
        //On a row, before placing a queen at a col, we'll first
        //check if that position is valid (a postion is valid if no other
        //queen already exists either in the same colum, same row, or diagonally)
        //If valid, we'll place the queen (make it 1) and then move to next row.
        //If an arrangement is possible, we'll simply add it. Whether an arrangement
        //is possible or not, we'll backtrack so that we can try next column on the same row.
        int possibilities = 0;
        for (int col = 0; col < size; col++) {
            if (isValidMove(board, i, col, size)) {
                board[i][col] = 1;
                int move = placeQueens(board, i+1, size);

                if (move != 0) {
                    possibilities += move;
                }

                board[i][col] = 0;
            }
        }

        return possibilities;
    }


    //a move is valid if curr value is not present in current row, current col
    //and submatrix.
    private boolean isValidMove(int[][] board, int i, int j, int size) {
        return isRowValid(board, i, size) &&
                isColumnValid(board, j, size) &&
                isDiagonalValid(board, i, j, size);
    }

    //checks if value is present anywhere in given row.
    private boolean isRowValid(int[][] board, int i, int size) {
        for (int col = 0; col < size; col++) {
            if (board[i][col] == 1) return false;
        }
        return true;
    }

    //checks if value is present anywhere in given col.
    private boolean isColumnValid(int[][] board, int j, int size) {
        for (int row = 0; row < size; row++) {
            if (board[row][j] == 1) return false;
        }
        return true;
    }

    //checks if value is present along the diagonals.
    private boolean isDiagonalValid(int[][] board, int i, int j, int size) {
        //north west.
        for (int col = j, row = i; col >= 0 && row >= 0; col--, row--) {
            if (board[row][col] == 1) return false;
        }

        //north east.
        for (int col = j, row = i; col < size && row >= 0; col++, row--) {
            if (board[row][col] == 1) return false;
        }

        //south east.
        for (int col = j, row = i; col < size && row < size; col++, row++) {
            if (board[row][col] == 1) return false;
        }

        //south west.
        for (int col = j, row = i; col >= 0 && row < size; col--, row++) {
            if (board[row][col] == 1) return false;
        }

        return true;
    }
}
