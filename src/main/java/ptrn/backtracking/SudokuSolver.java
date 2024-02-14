package ptrn.backtracking;

//@link - https://leetcode.com/problems/sudoku-solver/description/
//@strvr - https://takeuforward.org/data-structure/sudoku-solver/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5073145066422272
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        // sudokuSolver1(board, 0, 0, board.length);
        sudokuSolver2(board);
    }

    //1) SWD solution.
    private boolean sudokuSolver1(char[][] board, int i, int j, int size) {
        //If row index is `size`, means we have
        //traversed the entire grid, hence we must
        //already have filled the grid by this time.
        if (i == size) return true;

        //update the row and col index for recursive call.
        int nextI;
        int nextJ;
        //if on we last column, need to rollover
        //to next row's 0th column.
        if (j != size-1) {
            nextI = i;
            nextJ = j + 1;
        } else {
            nextI = i+1;
            nextJ = 0;
        }

        //if element are curr grid location is not a '.', means it already
        //has a value and so, we simply move to next position and continue solving.
        if (board[i][j] != '.') return sudokuSolver1(board, nextI, nextJ, size);

        //else if curr grid element isn't '.', means we need to tryout the possible
        //values.
        for (int val = 1; val <= 9; val++) {
            //if valid move, then replace the grid element
            //with the new value and continue solving.
            if (isValidMove(board, i, j, size, val)) {
                board[i][j] = Character.forDigit(val, 10);
                //if solved, we need not do anything futher, so return.
                boolean solved = sudokuSolver1(board, nextI, nextJ, size);
                if (solved) return true;
                //otherwise, if not solved with current value, backtrack
                //and try next value.
                board[i][j] = '.';
            }
        }

        //if we've exhausted all valued (1-9) for curren't grid position
        //and still couldn't solve, return false to caller.
        return false;
    }

    //2) Edctv soln.
    private boolean sudokuSolver2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == '.') {
                    for (int val = 1; val <= 9; val++) {
                        if (isValidMove(board, i, j, board.length, val)) {
                            board[i][j] = Character.forDigit(val, 10);

                            if (sudokuSolver2(board)) return true;

                            board[i][j] = '.';
                        }
                    }

                    return false;
                }
            }
        }

        //if here means we reached the last cell.
        //ans so we were able to solve the sudoku.
        return true;
    }


    //a move is valid if curr value is not present in current row, current col
    //and submatrix.
    private boolean isValidMove(char[][] board, int i, int j, int size, int val) {
        return isRowValid(board, i, size, val) &&
                isColumnValid(board, j, size, val) &&
                isSubMatrixValid(board, i, j, size, val);
    }

    //checks if value is present anywhere in given row.
    private boolean isRowValid(char[][] board, int i, int size, int val) {
        for (int col = 0; col < size; col++) {
            if (board[i][col] == Character.forDigit(val, 10)) return false;
        }
        return true;
    }

    //checks if value is present anywhere in given col.
    private boolean isColumnValid(char[][] board, int j, int size, int val) {
        for (int row = 0; row < size; row++) {
            if (board[row][j] == Character.forDigit(val, 10)) return false;
        }
        return true;
    }

    //checks if value is present anywhere in the submatrix which the
    //element lies in.
    private boolean isSubMatrixValid(char[][] board, int i, int j, int size, int val) {
        //eg: for a 9*9 mat, sub mat will be 3*3 and so on.
        int sizeOfSubMat = (int) Math.sqrt(size);

        //the section of submatrix current
        //element lies in.
        int rowBlock = i / sizeOfSubMat;
        int colBlock = j / sizeOfSubMat;

        //iterate over sub matrix and see if value already present.
        for (int subMatCol = (colBlock * sizeOfSubMat); subMatCol < ((colBlock + 1) * sizeOfSubMat); subMatCol++) {
            for (int subMatRow = (rowBlock * sizeOfSubMat); subMatRow < ((rowBlock + 1) * sizeOfSubMat); subMatRow++) {
                if (board[subMatRow][subMatCol] == Character.forDigit(val, 10)) return false;
            }
        }

        return true;
    }
}
