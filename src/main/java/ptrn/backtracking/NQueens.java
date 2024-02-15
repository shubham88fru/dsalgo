package ptrn.backtracking;

import java.util.*;

//@link - https://leetcode.com/problems/n-queens/solutions/
//@strvr - https://takeuforward.org/data-structure/n-queen-problem-return-all-distinct-solutions-to-the-n-queens-puzzle/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5452005473779712
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        return solveRecursive(n);
        //return solveIterative(n);
    }

    //1) Recursive solution.
    private List<List<String>> solveRecursive(int n) {
        //initialize a board with dots.
        char[][] board = new char[n][n];
        for (int i = 0; i<n; i++) {
            for (int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }
        return placeQueens(board, 0, n);
    }

    private List<List<String>> placeQueens(char[][] board, int i, int size) {

        //we have passed the last row. Means we have a valid arrangement.
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
                    allMoves.addAll(listOfMoves);
                }

                //the backtrack.
                board[i][col] = '.';
            }
        }

        return allMoves;
    }

    //2) My nasty iterative solution using stacks.
    //check edctv for a cleaner iterative solution (maybe?)
    private List<List<String>> solveIterative(int n) {

        List<List<String>> ans = new ArrayList<>();
        if (n == 1) ans.add(Arrays.asList("Q"));
        Deque<Integer> stack = new ArrayDeque<>();
        char[][] board = new char[n][n];
        StringBuffer dotStr = new StringBuffer();
        for (int i = 0; i<n; i++) {
            dotStr.append(".");
            for (int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }

        int row = 0;
        board[0][0] = 'Q';
        stack.addFirst(0);
        stack.addFirst(-1);
        row = 1;

        while (!stack.isEmpty()) {
            int j = stack.peekFirst();
            if (j >= 0)
                board[row][j] = '.';
            for (j=j+1; j<n; j++) {
                if (isValidMove(board, row, j, n)) {
                    board[row][j] = 'Q';
                    stack.removeFirst();
                    stack.addFirst(j);
                    stack.addFirst(-1);
                    row += 1;
                    break;
                }


            }

            if (j >= n) {
                stack.removeFirst();
                row -= 1;
            }
            if (row >= n) {
                stack.removeFirst();
                row -= 1;
                List<Integer> lst = new ArrayList<>(stack);
                List<String> config = new ArrayList<>();
                for (int idx: lst) {
                    StringBuffer sb = new StringBuffer(dotStr);
                    sb.setCharAt(idx, 'Q');
                    config.add(sb.toString());
                }
                ans.add(config);
            }
        }
        return ans;

    }


    //a move is valid if curr value is not present in current row, current col
    //and submatrix.
    private boolean isValidMove(char[][] board, int i, int j, int size) {

        return inBounds(board, i, j, size) &&
                isRowValid(board, i, size) &&
                isColumnValid(board, j, size) &&
                isDiagonalValid(board, i, j, size);
    }

    private boolean inBounds(char[][] board, int i, int j, int size) {
        return (i >= 0 && j >= 0 && i < size && j < size);
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
