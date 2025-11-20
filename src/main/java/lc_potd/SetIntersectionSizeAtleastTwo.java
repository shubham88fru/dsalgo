package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/set-intersection-size-at-least-two/description/?
//@check - https://www.youtube.com/watch?v=vkFaZIgO10Y&t=10s
public class SetIntersectionSizeAtleastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        return mikssol(intervals);
    }

    /**
     This question was apparently easy for some folks.
     I'm not sure if this is because I'm too dumb or
     they're too smart. This problem is not too difficult
     too code but coming up with the soln wasn't trivial for me.

     Coded by me completely based on mik's explanation.
     */
    private int mikssol(int[][] intervals) {
        int n = intervals.length;

        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a2[0] - a1[0];
        Arrays.sort(intervals, cmp1.thenComparing(cmp2));


        int f = Integer.MIN_VALUE;
        int s = Integer.MIN_VALUE;
        int res = 0;
        for (int[] interval: intervals) {
            int l = interval[0];
            int r = interval[1];

            if (f >= l) continue;

            if (l > s) {
                res += 2;
                f = r-1;
                s = r;
            } else {
                res += 1;
                f = s;
                s = r;
            }
        }

        return res;
    }
}
