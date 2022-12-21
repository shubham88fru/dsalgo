package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/maximal-square/description/
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> memo = new HashMap<String, Integer>();
        int area = 0;
        for (int currentRow = 0; currentRow < m; currentRow++) {
            for (int currentCol = 0; currentCol < n; currentCol++) {
                if (matrix[currentRow][currentCol] == '1') {
                    int sideLength = maximumSize(matrix, currentRow, currentCol, m, n, memo);
                    area = Math.max(area, sideLength*sideLength);
                }
            }
        }
        return area;
    }

    private int maximumSize(char[][] matrix, int currentRow, int currentCol, int m, int n, Map<String, Integer> memo) {
        if (currentRow < 0 || currentRow >= m ||
                currentCol < 0 || currentCol >= n ||
                matrix[currentRow][currentCol] == '0') return 0;

        String key = currentRow + "-" + currentCol;
        if (memo.containsKey(key)) return memo.get(key);
        int rightExpansion = 1 + maximumSize(matrix, currentRow, currentCol + 1, m, n, memo);
        int downExpansion = 1 + maximumSize(matrix, currentRow+1, currentCol, m, n, memo);
        int rightDiagExpansion = 1 + maximumSize(matrix, currentRow+1, currentCol+1, m, n, memo);

        memo.put(key, Math.min(rightExpansion, Math.min(downExpansion, rightDiagExpansion)));
        return memo.get(key);

    }
}
