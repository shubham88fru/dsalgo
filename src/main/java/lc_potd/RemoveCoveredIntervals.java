package lc_potd;

import java.util.Arrays;
import java.util.Comparator;

//@link - https://leetcode.com/problems/remove-covered-intervals/description/?
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        return pass1(intervals);
    }

    private int pass1(int[][] intervals) {
        int n = intervals.length;
        Comparator<int[]> cmp1 = (i1, i2) -> i1[0]-i2[0];
        Comparator<int[]> cmp2 = (i1, i2) -> i2[1] - i1[1];
        Arrays.sort(intervals, cmp1.thenComparing(cmp2));

        int cnt = 1;
        int i = 0;

        for (int j=1; j<n; j++) {
            if (intervals[j][1] <= intervals[i][1]) continue;

            cnt += 1;
            i = j;
        }

        return cnt;
    }
}
