package strvr.arrays3;

import java.util.Map;

//@link - https://leetcode.com/problems/unique-paths/description/
//@strvr - https://takeuforward.org/data-structure/grid-unique-paths-count-paths-from-left-top-to-the-right-bottom-of-a-matrix/
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
}
