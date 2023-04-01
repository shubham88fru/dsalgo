package swd.recursionbacktracking;

import java.util.ArrayList;
import java.util.Scanner;


//@link - https://www.pepcoding.com/resources/online-java-foundation/recursion-backtracking/knights-tour-official/ojquestion
//@link - https://www.codingninjas.com/codestudio/problems/knight-tour_1170518

//Recursion and backtracking video day 4.
//Not sure if the soln works, all platforms other than LC are
//nothing more than a crap.
public class KnightsTourProblem {
    //Pepcoding
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int r = scn.nextInt();
        int c = scn.nextInt();
        int[][] chess = new int[n][n];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                chess[i][j] = -1;
            }
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        solve(r, c, 0, 0, 0, chess);
    }

    public static void solve(int n, int m, int i, int j, int moveNum, int[][] board) {
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != -1) return;

        if (moveNum == (n * m - 1)) {
            displayBoard(board);
            return;
        }
        // int nextRow, nextCol;
        // if (j == m-1) {
        //     nextRow = i + 1;
        //     nextCol = 0;
        // } else {
        //     nextRow = i;
        //     nextCol = j + 1;
        // }


        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                board[row][col] = moveNum;

                //up right
                solve(n, m, row - 2, col + 1, moveNum + 1, board);

                //up left
                solve(n, m, row - 2, col - 1, moveNum + 1, board);

                //right up
                solve(n, m, row - 1, col + 2, moveNum + 1, board);

                //right down
                solve(n, m, row + 1, col + 2, moveNum + 1, board);

                //down right
                solve(n, m, row + 2, col + 1, moveNum + 1, board);

                //down left
                solve(n, m, row + 2, col - 1, moveNum + 1, board);

                //left down
                solve(n, m, row + 1, col - 2, moveNum + 1, board);

                //left up
                solve(n, m, row - 1, col - 2, moveNum + 1, board);

                board[row][col] = -1;
            }
        }
    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
