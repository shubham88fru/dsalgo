package lc_potd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/
//@check - https://www.youtube.com/watch?v=NvdOTWO02ZI&t=1643s&ab_channel=codestorywithMIK
public class MinimumOperationsToMakeAUniValuedGrid {
    public int minOperations(int[][] grid, int x) {
        return pass1(grid, x);
    }

    /*
        I was in the right direction, just handn't
        reached till the conclusion that we need to
        target the median and why that will be the
        target.

        Since median is mid, it makes sense that
        its distance from each number in the array will
        be the least. Moreover, if we make the target to
        be any number outside the min/max of the given set,
        we'll definitely get a total distance larger
        than what we get in median.
    */
    private int pass1(int[][] grid, int x) {
        List<Integer> lst = new ArrayList<>();
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                lst.add(grid[i][j]);
            }
        }

        Collections.sort(lst);
        int mid = lst.get((lst.size()-1)/2);

        int minOps = 0;
        for (int i=0; i<lst.size(); i++) {
            int dist = Math.abs(mid-lst.get(i));
            if (dist%x != 0) return -1;
            minOps += (dist/x);
        }

        return minOps;
    }
}
