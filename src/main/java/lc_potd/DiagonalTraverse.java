package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/diagonal-traverse/description/
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        return mikssol(mat);
    }

    /**
     * Coded by me but based on mik's
     * explanation. I'm not sure if this
     * is the most optimal soln though.
     */
    private int[] mikssol(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!mp.containsKey(i+j)) {
                    mp.put(i+j, new ArrayList<>());
                }
                mp.get(i+j).add(mat[i][j]);
            }
        }

        int i = 0;
        List<Integer> ans = new ArrayList<>();
        while (i <= (m-1+n-1)) {
            if (i%2 == 0) {
                Collections.reverse(mp.get(i));
            }
            for (int item: mp.get(i)) {
                ans.add(item);
            }
            i += 1;
        }

        return ans.stream().mapToInt(j -> j).toArray();
    }
}
