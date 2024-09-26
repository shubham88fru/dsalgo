package ptrn.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

//@link - https://leetcode.com/problems/non-overlapping-intervals/description/
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Comparator<int[]> cmp1 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[1] - a2[1];


        Arrays.sort(intervals, cmp1);
        return greedy(intervals);

        // Map<String, Integer> memo = new HashMap<>();
        // return dp(intervals, 0, Integer.MIN_VALUE, memo);
    }

    //1) My greedy soln.
    private int greedy(int[][] intervals ) {
        int prevEnd = intervals[0][1];

        int count = 0;
        /**
         For every interval (assuming the intervals array is sorted at this point),
         check if the current interval in overlapping with the previous interval.
         If so, then chose the interval that has shorted end, because it is less
         likely to overlapp with the next interval.

         */
        for (int i=1; i<intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                if (intervals[i][1] < prevEnd) {
                    prevEnd = intervals[i][1];
                }
                count += 1;
            } else {
                //otherwise, if current interval is not overlapping
                //with the previous interval, update the end to
                //be the current interval's end.
                prevEnd = intervals[i][1];
            }
        }

        return count;
    }

    //My Dp + Memoization soln, gives TLE.
    private int dp(int[][] intervals, int curr, int prevEnd, Map<String, Integer> memo) {
        if (curr >= intervals.length) return 0;

        String key = curr + "_" + prevEnd;
        if (memo.containsKey(key)) return memo.get(key);

        int pick = 999999;
        if (intervals[curr][0] >= prevEnd) {
            pick = dp(intervals, curr+1, intervals[curr][1], memo);
        }

        int notPick = 1 + dp(intervals, curr+1, prevEnd, memo);

        memo.put(key, Math.min(pick, notPick));
        return memo.get(key);
    }
}
