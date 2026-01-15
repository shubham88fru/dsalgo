package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/?
public class MaximumAreaOfSquareHoleInGrid {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        return hintedsol(n, m, hBars, vBars);
    }

    /**
     * Coded by me, but after hit and trial
     * and slight hint that sorting is needed.
     */
    private int hintedsol(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        //longest consecutive horizontal.
        int i = 0, maxH = 1;
        while (i < hBars.length) {
            int j = i+1;
            while (j < hBars.length && hBars[j] == hBars[j-1]+1) {
                j += 1;
            }
            maxH = Math.max(maxH, j-i);
            i = j;
        }

        //longest consecutive vertical.
        int k = 0, maxV = 1;
        while (k < vBars.length) {
            int l = k+1;
            while (l < vBars.length && vBars[l] == vBars[l-1]+1) {
                l += 1;
            }
            maxV = Math.max(maxV, l-k);
            k = l;
        }

        int min = Math.min(maxH, maxV)+1; //possible sq will have length of min.

        return min*min;
    }
}
