package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/count-covered-buildings/description/?
public class CountCoveredBuildings {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // return pass1(n, buildings);
        return pass2(n, buildings);
    }

    /**
     Only store min/max. No sorting needing.
     */
    private int pass2(int n, int[][] buildings) {
        Map<Integer, int[]> xM = new HashMap<>();
        Map<Integer, int[]> yM = new HashMap<>();

        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];

            if (!xM.containsKey(x)) xM.put(x, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            xM.get(x)[0] = Math.min(xM.get(x)[0], y);
            xM.get(x)[1] = Math.max(xM.get(x)[1], y);

            if (!yM.containsKey(y)) yM.put(y, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            yM.get(y)[0] = Math.min(yM.get(y)[0], x);
            yM.get(y)[1] = Math.max(yM.get(y)[1], x);
        }

        int count = 0;
        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];

            /**
             This is a bit clever.
             Just check if the point is at
             start or end to know if
             it is covered or not.
             */
            if ((xM.get(x)[0] != y) &&
                    (xM.get(x)[1] != y) &&
                    (yM.get(y)[0] != x) &&
                    (yM.get(y)[1] != x)) count += 1;
        }

        return count;
    }

    /**
     Sub optimal. Unecessarily stores a list
     when we're only interested in first and last
     (min and max). Which then necessitates sorting.
     */
    private int pass1(int n, int[][] buildings) {
        Map<Integer, List<Integer>> xM = new HashMap<>();
        Map<Integer, List<Integer>> yM = new HashMap<>();

        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];

            if (!xM.containsKey(x)) xM.put(x, new ArrayList<>());
            xM.get(x).add(y);

            if (!yM.containsKey(y)) yM.put(y, new ArrayList<>());
            yM.get(y).add(x);
        }

        for (Map.Entry<Integer, List<Integer>> e: xM.entrySet()) Collections.sort(e.getValue());

        for (Map.Entry<Integer, List<Integer>> e: yM.entrySet()) Collections.sort(e.getValue());

        int count = 0;
        for (int[] building: buildings) {
            int x = building[0];
            int y = building[1];

            int szx = xM.get(x).size();
            int szy = yM.get(y).size();

            /**
             This is a bit clever.
             Just check if the point is at
             start or end to know if
             it is covered or not.
             */
            if ((xM.get(x).get(0) != y) &&
                    (xM.get(x).get(szx-1) != y) &&
                    (yM.get(y).get(0) != x) &&
                    (yM.get(y).get(szy-1) != x)) count += 1;
        }

        return count;
    }
}
