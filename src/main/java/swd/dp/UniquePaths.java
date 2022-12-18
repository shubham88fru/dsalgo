package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/unique-paths/description/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        return numWays(m, n, 0, 0, new HashMap<String, Integer>());
    }

    private int numWays(int m, int n, int currM, int currN, Map<String, Integer> memo) {
        if (currM >=m || currN >= n) return 0;
        if (currM == m-1 && currN == n-1) return 1;

        String key = currM + "-" + currN;
        if (memo.containsKey(key)) return memo.get(key);

        int moveDown = numWays(m, n, currM, currN+1, memo);
        int moveRight = numWays(m, n, currM+1, currN, memo);

        memo.put(key, moveDown + moveRight);
        return memo.get(key);
    }
}
