package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/minimum-falling-path-sum/description/
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int min = 1000001;
        for (int i=0; i<matrix[0].length; i++) {
            int res = minFallPathSum(matrix, matrix.length, matrix[0].length, 0, i, new HashMap<String, Integer>());
            if (res < min) {
                min = res;
            }
        }
        return min;
    }

    private int minFallPathSum(int[][] matrix, int maxRow, int maxCol, int currRow, int currCol, Map<String, Integer> memo) {
        if (currRow >= maxRow || currCol >= maxCol || currCol < 0) return 1000001;
        if (currRow == (maxRow - 1)) return matrix[currRow][currCol];

        String key = currRow + "-" + currCol;
        if (memo.containsKey(key)) return memo.get(key);
        int down = matrix[currRow][currCol] + minFallPathSum(matrix, maxRow, maxCol, currRow+1, currCol, memo);
        int downLeft = matrix[currRow][currCol] + minFallPathSum(matrix, maxRow, maxCol, currRow+1, currCol-1, memo);
        int downRight = matrix[currRow][currCol] + minFallPathSum(matrix, maxRow, maxCol, currRow+1, currCol+1, memo);

        memo.put(key, Math.min(down, Math.min(downLeft, downRight)));
        return memo.get(key);
    }
}
