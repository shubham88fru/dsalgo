package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/first-completely-painted-row-or-column/description/
//@check - https://www.youtube.com/watch?v=nqmR3aDwjwU&t=1713s&ab_channel=codestorywithMIK
public class FirstCompletelyPaintedRowOrColumn {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        // return pass1(arr, mat);
        return pass2(arr, mat);
    }

    /*
        My soln. Mik also showed this soln.
        This is better than the brute force,
        but still not the optimal solution
        coz space complexity is pretty high.
    */
    private int pass1(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, Integer> rows = new HashMap<>();
        Map<Integer, Integer> cols = new HashMap<>();
        Map<Integer, int[]> matEls = new HashMap<>();

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                matEls.put(mat[i][j], new int[] {i, j});
            }
        }

        for (int i=0; i < m*n; i++) {
            int[] pos = matEls.get(arr[i]);
            if (!rows.containsKey(pos[0])) rows.put(pos[0], 0);
            rows.put(pos[0], rows.get(pos[0])+1);

            if (rows.get(pos[0]) == n) return i;

            if (!cols.containsKey(pos[1])) cols.put(pos[1], 0);
            cols.put(pos[1], cols.get(pos[1])+1);

            if (cols.get(pos[1]) == m) return i;
        }

        return -1;
    }

    /*
        Optimal approach. Coded by me but based on mik's
        explanation.
    */
    private int pass2(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, Integer> index = new HashMap<>();
        for (int i=0; i<m*n; i++) {
            index.put(arr[i], i);
        }

        int minIdx = Integer.MAX_VALUE;
        //Ask each row, when is the earliest it can get
        //filled.
        for (int i=0; i<m; i++) {
            int min = -1;
            for (int j=0; j<n; j++) {
                min = Math.max(min, index.get(mat[i][j]));
            }

            //select the row which filled earliest.
            minIdx = Math.min(minIdx, min);
        }

        //Ask each col, when is the earliest it can get
        //filled.
        for (int j=0; j<n; j++) {
            int min = -1;
            for (int i=0; i<m; i++) {
                min = Math.max(min, index.get(mat[i][j]));
            }

            //select the col that filled earliest.
            minIdx = Math.min(minIdx, min);
        }

        return minIdx;
    }
}
