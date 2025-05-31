package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/snakes-and-ladders/description/
//@check - https://www.youtube.com/watch?v=26IT3FYm5h8&ab_channel=codestorywithMIK
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        return mikssol(board);
    }

    /*
        I had a vague idea, but couldn't come up
        with the mapping coordinates part. I also
        thought on the lines of DP, but that didn't
        work out.

        Idea is to do a simple BFS to get the least
        number of steps to reach the target.

        Following is based on miks approach but
        he didn't explain how the code handles
        the part where we don't have to
        move to another ladder if the destination
        of a ladder is source of another ladder.
        Will have to check some other solutions as well.
    */
    private int mikssol(int[][] board) {
        int n = board.length;

        Deque<Integer> q = new ArrayDeque<>();
        int[][] visited = new int[n][n];

        q.addLast(1);
        visited[n-1][0] = -1;
        int rolls = 0;
        while (!q.isEmpty()) {
            int sz = q.size();

            while (sz > 0) {
                int node = q.removeFirst();

                if (node == n*n) return rolls;

                int rMin = node + 1;
                int rMax = Math.min(node + 6, n*n);

                for (int i=rMin; i<=rMax; i++) {
                    int[] pair = getCoords(i, n);
                    int r = pair[0];
                    int c = pair[1];

                    if (visited[r][c] == -1) continue;

                    visited[r][c] = -1;

                    if (board[r][c] != -1) {
                        // int[] jump = getCoords(board[r][c], n);
                        // visited[jump[0]][jump[1]] = -1;
                        q.addLast(board[r][c]);
                    } else {
                        q.addLast(i);
                    }

                }

                sz -= 1;

            }
            rolls += 1;
        }

        return -1;
    }


    private int[] getCoords(int num, int n) {

        int rNormal = (num-1)/n; // -1 coz 1 based indexing.
        int rBottom = (n-1) - rNormal; // mapped to numbering from bottom.

        int colNormal = (num-1)%n; // -1 coz 1 based indexing.
        int colBoustrophedon = colNormal; // mapped to boustrophedon numbering.

        //tricky - mapping to zig-zag
        if ((rBottom%2 == 0 && n%2 == 0) || (rBottom%2 != 0 && n%2 != 0)) {
            colBoustrophedon = (n-1) - colBoustrophedon;
        }

        return new int[] {rBottom, colBoustrophedon};
    }
}
