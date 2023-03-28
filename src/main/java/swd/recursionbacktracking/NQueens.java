package swd.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/n-queens/solutions/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i<n; i++) {
            for (int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }
        return placeQueens(board, 0, n);
    }

    private List<List<String>> placeQueens(char[][] board, int i, int size) {

        if (i == size) {
            List<String> allLevelsAsStr = new ArrayList<>();
            List<List<String>> ans = new ArrayList<List<String>>();
            for (int row = 0; row < size; row++) {
                char[] level = board[row];
                String levelStr = new String(level);
                allLevelsAsStr.add(levelStr);
            }
            ans.add(allLevelsAsStr);
            return ans;
        }

        List<List<String>> allMoves = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if (isValidMove(board, i, col, size)) {
                board[i][col] = 'Q';
                List<List<String>> listOfMoves = placeQueens(board, i+1, size);

                if (listOfMoves.size() != 0) {
                    //allMoves = new ArrayList<>();
                    allMoves.addAll(listOfMoves);
                }

                board[i][col] = '.';
            }
        }

        return allMoves;
    }


    //a move is valid if curr value is not present in current row, current col
    //and submatrix.
    private boolean isValidMove(char[][] board, int i, int j, int size) {
        return isRowValid(board, i, size) &&
                isColumnValid(board, j, size) &&
                isDiagonalValid(board, i, j, size);
    }

    //checks if value is present anywhere in given row.
    private boolean isRowValid(char[][] board, int i, int size) {
        for (int col = 0; col < size; col++) {
            if (board[i][col] == 'Q') return false;
        }
        return true;
    }

    //checks if value is present anywhere in given col.
    private boolean isColumnValid(char[][] board, int j, int size) {
        for (int row = 0; row < size; row++) {
            if (board[row][j] == 'Q') return false;
        }
        return true;
    }

    private boolean isDiagonalValid(char[][] board, int i, int j, int size) {
        //north west.
        for (int col = j, row = i; col >= 0 && row >= 0; col--, row--) {
            if (board[row][col] == 'Q') return false;
        }

        //north east.
        for (int col = j, row = i; col < size && row >= 0; col++, row--) {
            if (board[row][col] == 'Q') return false;
        }

        //south east.
        for (int col = j, row = i; col < size && row < size; col++, row++) {
            if (board[row][col] == 'Q') return false;
        }

        //south west.
        for (int col = j, row = i; col >= 0 && row < size; col--, row++) {
            if (board[row][col] == 'Q') return false;
        }

        return true;
    }
}
