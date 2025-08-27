package lc_potd;

//@link - https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/description/
public class LengthOfLongestVShapedDiagonalSegment {
    public int lenOfVDiagonal(int[][] grid) {
        return pass1(grid);
    }

    /**
     Code by me after small hints.
     This problem ain't that difficult.
     Only a slight tricky part is the way we
     need to manage clockwise movement.
     */
    private int pass1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs90 = {{1,1}, {1,-1}, {-1,-1}, {-1, 1}}; //consecutive dirs are 90 deg clockwise.
        Integer[][][][] memo = new Integer[m+1][n+1][5][3]; //i, j, d, canTurn
        int max = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    for (int d=0; d<4; d++) {
                        max = Math.max(max,
                                1 + dp(m, n, grid, dirs90, i+dirs90[d][0], j+dirs90[d][1], d, 2, 1, memo)
                        );
                    }
                }
            }
        }

        return max;
    }

    private int dp(int m, int n, int[][] grid, int[][] dirs90, int i, int j, int d, int target, int canTurn, Integer[][][][] memo) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != target) return 0;

        /**
         Still a bit confused why 4d dp works.
         Without taking `target` (which makes it 5d dp)
         in consideration. mik said that its redundant to
         use `target` coz its fixed in a sense that 2 and 0
         are alternating, i.e. after 2 its going to be 0 and
         after 0, its going to be 2 and so on. But I'm not
         100% sure on this.
         */
        if (memo[i][j][d][canTurn] != null) return memo[i][j][d][canTurn];

        int move = 1 + dp(m, n, grid, dirs90, i+dirs90[d][0], j+dirs90[d][1], d, target==2?0:2, canTurn, memo);

        int turn = 0;
        if (canTurn == 1) {
            int d_ = (d+1)%4; //the 90 deg clockwise dir.
            turn += (1 + dp(m, n, grid, dirs90, i+dirs90[d_][0], j+dirs90[d_][1], d_, target==2?0:2, 0, memo));
        }

        memo[i][j][d][canTurn] = Math.max(move, turn);
        return memo[i][j][d][canTurn];
    }
}
