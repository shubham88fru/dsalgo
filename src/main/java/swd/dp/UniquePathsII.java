package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/unique-paths-ii/description/
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return waysAfterAvoidingObstacles(obstacleGrid, 0, 0, new HashMap<String, Integer>());
    }

    private int waysAfterAvoidingObstacles(int[][] obstacleGrid, int currRow, int currCol, Map<String, Integer> memo) {
        int maxCol = obstacleGrid[0].length;
        int maxRow = obstacleGrid.length;
        if (currRow >= maxRow || currCol >= maxCol || obstacleGrid[currRow][currCol] == 1) return 0;
        if ((currRow == (maxRow - 1)) && (currCol == (maxCol - 1))) return 1;

        if (obstacleGrid[currRow][currCol] == 1) return 0;

        String key = currRow + "-" + currCol;
        if (memo.containsKey(key)) return memo.get(key);

        int downMoves = waysAfterAvoidingObstacles(obstacleGrid, currRow+1, currCol, memo);
        int rightMoves = waysAfterAvoidingObstacles(obstacleGrid, currRow, currCol+1, memo);

        memo.put(key, (downMoves + rightMoves));
        return memo.get(key);
    }
}
