package swd.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/triangle/description/
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotalSum(triangle, 0, 0, new HashMap<String, Integer>());
    }

    private int minimumTotalSum(List<List<Integer>> triangle, int currRow, int currCol, Map<String, Integer> memo) {
        if (currRow >= triangle.size()) return 1000001;

        List<Integer> row = triangle.get(currRow);
        if (currCol >= row.size()) return 1000001;
        if (currRow == triangle.size()-1) return row.get(currCol);

        String key = currRow + "-" + currCol;
        if (memo.containsKey(key)) return memo.get(key);
        int eye = row.get(currCol) + minimumTotalSum(triangle, currRow+1, currCol, memo);
        int eyePlusOne = row.get(currCol) + minimumTotalSum(triangle, currRow+1, currCol+1, memo);

        memo.put(key, Math.min(eye, eyePlusOne));
        return memo.get(key);

    }
}
