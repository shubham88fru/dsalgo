package ptrn.matrices;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/where-will-the-ball-fall/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6571451911766016
public class WhereWillTheBallFall {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];

        for (int i=0; i<n; i++) {
            //each ball starts at respective column
            //and row 0 with a direction of down ('d').
            char dir = 'd';
            int col = i;
            int row = 0;

            //we store this combination to checking if a
            //ball is stuck. If for a ball, we reach to a cell
            //with the same direction as we had reached before, means,
            //the ball is oscillating between the same states.
            String comb = (dir + "_" + row + "_" + col);
            Set<String> combs = new HashSet<>();
            combs.add(comb);

            //flag to break the loop.
            boolean stuck = false;
            while (!stuck) {

                //if ball is moving down..
                if (dir == 'd') {
                    //and curr board is 1,
                    //the ball will be deflected
                    //to the next col in same row and its
                    //direction will become r.

                    if (grid[row][col] == 1) {
                        col += 1;
                        dir = 'r';
                    }
                    //Or if board is -1, then the ball
                    //will be deflected to left.
                    else if (grid[row][col] == -1) {
                        col -= 1;
                        dir = 'l';
                    }
                }

                //Else if the ball is moving right,
                else if (dir == 'r') {
                    //and the board is 1, the ball
                    //will be deflected down.
                    if (grid[row][col] == 1) {
                        row += 1;
                        dir = 'd';
                    }
                    //or if the board is -1, the ball
                    //will be delfected to left.
                    else if (grid[row][col] == -1) {
                        col -= 1;
                        dir = 'l';
                    }
                }

                //Else if the ball is moving left,
                else if (dir == 'l') {
                    //and board is 1, the ball will be
                    //deflected to right.
                    if (grid[row][col] == 1) {
                        col += 1;
                        dir = 'r';
                    }
                    //or if the board is -1, the ball will
                    //be deflected down.
                    else if (grid[row][col] == -1) {
                        row += 1;
                        dir = 'd';
                    }
                }

                //if next row is `m` and ball is moving
                //down, means the ball has crossed the grid.
                if (row == m && dir=='d') break;

                comb = dir + "_" + row + "_" + col;
                stuck = checkStuck(dir, row, col, m, n, combs, comb);
                combs.add(comb);
            }

            //if stuck, the ball will be trapped.
            if (stuck) ans[i] = -1;
            else ans[i] = col;
        }

        return ans;
    }

    private boolean checkStuck(char dir, int row, int col, int m, int n, Set<String> combs, String comb) {
        //check if the ball is not oscillating between
        //same positions. Basically if ball is trapped
        //by V shape boards junction, the ball will
        //keep oscillating left and right.
        if (combs.contains(comb)) return true;

        //or if the new position is within bounds.
        //If the ball is deflected to edge, the ball will
        //be trapped in the V shape formed by the wall of the grid
        //and the board.
        return row < 0 || col < 0 || row >= m || col >= n;
    }
}
