package ptrn.dp;

import java.util.Map;

//@link - https://leetcode.com/problems/unique-paths/description/
//@strvr - https://takeuforward.org/data-structure/grid-unique-paths-count-paths-from-left-top-to-the-right-bottom-of-a-matrix/
//       - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6508644687675392
public class UniquePaths {
    public int uniquePaths(int row, int col) {
        //return numWaysDP(row, col, 0, 0, new HashMap<String, Integer>());
        return numWaysOptimal(row, col);
    }

    //1) Optimal Solution: Using combinatorics.
    private int numWaysOptimal(int row, int col) {
        //From top-left corner to reach to bottom-right
        //corner and considering that the bot can only
        //move right and down, each valid path will always
        //have exactly row-1+col-1 moves
        int n = row + col - 2; //(row-1) + (col-1)

        //Out of the above `n` possible moves,
        //each valid path will have exactly `row-1` down moves
        //and remaining (col-1) moves will right moves.
        //So, if from all `n` moves, we select the moves which
        //have `row-1` down moves or `col-1` right moves, we'll have our ans.
        int r = row-1;
        return nCr(n, r);
    }

    private int nCr(int n, int r) {
        double res = 1.0;
        for (int i = 1; i <= r; i++) {
            res = res * (n-r+i)/i;
        }

        return (int)res;
    }


    //2) DP Solution. Recursive soln is Brute force approach for this question
    //and time-complexity wise exponential (O(2^(M*N))). DP solution optimizes the
    //time-complexity to (O(N*M)) but also needs extra (O(N*M)) space.
    private int numWaysDP(int m, int n, int currM, int currN, Map<String, Integer> memo) {
        if (currM >=m || currN >= n) return 0;
        if (currM == m-1 && currN == n-1) return 1;

        String key = currM + "-" + currN;
        if (memo.containsKey(key)) return memo.get(key);

        int moveDown = numWaysDP(m, n, currM, currN+1, memo);
        int moveRight = numWaysDP(m, n, currM+1, currN, memo);

        memo.put(key, moveDown + moveRight);
        return memo.get(key);
    }

    /*
        Revise dp soln. Top-down dp, though.
     */
    private int revise(int m, int n, int i, int j, Integer[][] memo) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;

        if (i==m-1 && j==n-1) return 1;

        if (memo[i][j] != null) return memo[i][j];

        int down = 0;
        down += revise(m, n, i+1, j, memo);

        int right = 0;
        right += revise(m, n, i, j+1, memo);

        memo[i][j] = (down+right);
        return (down+right);
    }

    /*
        Seeing the constraints on the question, my og intuition
        was to use backtracking. However, that gives TLE.
     */
    private void backtracking(int m, int n, int i, int j, int[] count, int[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n) return ;
        if (visited[i][j] == -1) return;

        if (i==m-1 && j==n-1) {
            count[0] +=1 ;
            return;
        }

        visited[i][j] = -1;
        backtracking(m, n, i+1, j, count, visited);
        backtracking(m, n, i, j+1, count, visited);

        visited[i][j] = 0;
    }
}
