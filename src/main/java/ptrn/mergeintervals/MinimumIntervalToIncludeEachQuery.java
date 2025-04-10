package ptrn.mergeintervals;

import java.util.Arrays;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-interval-to-include-each-query/
//@check - https://www.youtube.com/watch?v=5hQ5WWW5awQ&t=1107s&ab_channel=NeetCode
public class MinimumIntervalToIncludeEachQuery {
    public int[] minInterval(int[][] intervals, int[] queries) {
        return ncsol(intervals, queries);
    }

    /*
    * Coded by me based on nc's explanation.
    * */
    private int[] ncsol(int[][] intervals, int[] queries) {
        int n = queries.length;
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        int[][] sortedQueries = new int[n][2];
        for (int i=0; i<n; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (q1, q2) -> q1[0] - q2[0]);

        PriorityQueue<int[]> pair = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]);

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        int j = 0;
        for (int i=0; i<n; i++) {

            while (j < intervals.length) {
                if (intervals[j][0] <= sortedQueries[i][0] && sortedQueries[i][0] <= intervals[j][1]) {
                    pair.add(new int[] {intervals[j][1] - intervals[j][0] + 1, intervals[j][1] });
                } else if (intervals[j][0] > sortedQueries[i][0]) break;
                j += 1;
            }

            while (!pair.isEmpty() && pair.peek()[1] < sortedQueries[i][0]) {
                pair.remove();
            }

            if (!pair.isEmpty()) {
                ans[sortedQueries[i][1]] = pair.peek()[0];
            }

        }

        return ans;
    }
}
