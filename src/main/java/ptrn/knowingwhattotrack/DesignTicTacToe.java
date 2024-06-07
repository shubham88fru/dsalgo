package ptrn.knowingwhattotrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5214223602024448
//@check - https://www.naukri.com/code360/problems/design-tic-tac-toe_1265038

//1) Mt soln - Not brute but not optimal too.
public class DesignTicTacToe {
    int[] rowCnts;
    int[] colCnts;
    int leftDiagCnt;
    int rightDiagCnt;

    int[][] ttt;
    int N;

    public DesignTicTacToe(int n) {
        this.rowCnts = new int[n];
        this.colCnts = new int[n];
        this.leftDiagCnt = 0;
        this.rightDiagCnt = 0;

        this.ttt = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ttt[i][j] = -1;
            }
        }

        this.N = n;
    }

    public int move(int row, int col, int player) {
        ttt[row][col] = player;
        if (row == col) {
            leftDiagCnt += 1;
            if (leftDiagCnt == N) {
                if (checkLeftDiag() == player) return player;
            }
        }

        if (row+col == N-1) {
            rightDiagCnt += 1;
            if (rightDiagCnt == N) {
                if (checkRightDiag() == player) return player;
            }
        }

        rowCnts[row] += 1;
        if (rowCnts[row] == N) {
            if (checkRow(row) == player) return player;
        }


        colCnts[col] += 1;
        if (colCnts[col] == N) {
            if (checkCol(col) == player) return player;
        }

        return 0;

    }

    private int checkLeftDiag() {
        int player = ttt[0][0];
        for (int i=0; i<N; i++) {
            if (ttt[i][i] != player) return 0;
        }

        return player;
    }

    private int checkRightDiag() {
        int col = N-1;
        int player = ttt[0][col];

        for (int i=0; i < N; i++) {
            if (ttt[i][col] != player) return 0;
            col -= 1;
        }

        return player;
    }

    private int checkRow(int row) {
        int player = ttt[row][0];
        for (int j=0; j<N; j++) {
            if (ttt[row][j] != player) return 0;
        }

        return player;
    }

    private int checkCol(int col) {
        int player = ttt[0][col];
        for (int i=0; i<N; i++) {
            if (ttt[i][col] != player) return 0;
        }

        return player;
    }
}

//2) Edctv soln.
//check on link.
//algorithm -
/**
 * 1. Initialize two arrays to the track counts of marks made in rows and columns. Initialize both arrays to 0
 * 2. Initialize two variables to count the marks made along the diagonal and the anti-diagonal.
 * 3. For every move (𝑖,𝑗), made by Player increment rows[i] and cols[j] as well
 *    as diagonal and antiDiagonal, when applicable. For moves by Player 2 , decrement the relevant counts.
 * 4. If, at any point, the updated value in rows or in cols equals 𝑛
 * , or if either of two variables counting marks on the diagonals equals 𝑛, return the current player as the winner.
 *
 * 5. Otherwise, the game is a tie, and we return 0.
 */
class TicTacToe {
    List<Integer> rows;
    List<Integer> cols;
    int diagonal;
    int antiDiagonal;

    // TicTacToe class contains rows, cols, diagonal,
    // and anti_diagonal to create a board.
    // Constructor is used to create a board of size n * n.
    public TicTacToe(int n) {
        this.rows = new ArrayList<>(Collections.nCopies(n, 0));
        this.cols = new ArrayList<>(Collections.nCopies(n, 0));
        diagonal = 0;
        antiDiagonal = 0;
    }

    // move function will allow the players to play the game
    // for given row and col.
    public int move(int row, int col, int player) {
        int currentPlayer = (player == 1) ? 1 : -1;
        int n = rows.size();

        rows.set(row, rows.get(row) + currentPlayer);
        cols.set(col, cols.get(col) + currentPlayer);

        if (row == col) {
            diagonal += currentPlayer;
        }
        if (col == (n - row - 1)) {
            antiDiagonal += currentPlayer;
        }

        if (Math.abs(rows.get(row)) == n || Math.abs(cols.get(col)) == n ||
                Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
            return player;
        }

        return 0;
    }
}