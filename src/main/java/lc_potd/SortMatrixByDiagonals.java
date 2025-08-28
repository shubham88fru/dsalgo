package lc_potd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-matrix-by-diagonals/
public class SortMatrixByDiagonals {
    public int[][] sortMatrix(int[][] grid) {
        return pass1(grid);
    }

    /*
        My soln.
     */
    private int[][] pass1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, PriorityQueue<Integer>> mp = new HashMap<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!mp.containsKey(i-j)) {
                    if (i >= j) {
                        mp.put(i-j, new PriorityQueue<>(Collections.reverseOrder()));
                    } else mp.put(i-j, new PriorityQueue<>());

                }
                mp.get(i-j).add(mat[i][j]);
            }
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                mat[i][j] = mp.get(i-j).remove();
            }
        }

        return mat;
    }
}
