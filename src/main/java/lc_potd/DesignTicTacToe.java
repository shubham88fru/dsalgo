package lc_potd;

//@link - https://leetcode.com/problems/design-tic-tac-toe/

/*
 Optimal
* */
class TicTacToe {
    int n;
    int[] rowSum, colSum;
    int leftDiag, rightDiag;
    public TicTacToe(int n) {
        this.n = n;
        rowSum = new int[n];
        colSum = new int[n];
    }

    //TC: O(1)
    public int move(int row, int col, int player) {
        int add = player == 1 ? 1: -1;

        rowSum[row] += add;
        colSum[col] += add;
        if (row == col) leftDiag += add;
        if ((row+col) == n-1) rightDiag += add;

        if (Math.abs(rowSum[row]) == n
                || Math.abs(colSum[col]) == n
                || Math.abs(leftDiag) == n
                || Math.abs(rightDiag) == n) return player;

        return 0;
    }
}

/*
Brute force
 */
public class DesignTicTacToe {
    int n;
    int[][] board;
    public DesignTicTacToe(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    //TC: O(n)
    public int move(int row, int col, int player) {
        board[row][col] = player;

        boolean rowFill = true, colFill = true, leftDiag = true, rightDiag = true;
        for (int i=0; i<n; i++) {
            if (board[row][i] != player) rowFill = false;
            if (board[i][col] != player) colFill = false;
            if (board[i][i] != player) leftDiag = false; //i==j
            if (board[i][n-1-i] != player) rightDiag = false;//i+j==n-1
        }

        if (rowFill || colFill || leftDiag || rightDiag) return player;

        return 0;
    }
}
