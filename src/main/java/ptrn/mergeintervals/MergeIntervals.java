package ptrn.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//@link - https://leetcode.com/problems/merge-intervals/solutions/
//@strvr - https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6354788758585344
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        //return mergeIntervalsBrute(intervals);
        return mergeIntervalsOptimal(intervals);
    }

    //1) Optimal soln. T: O(NlogN), S: O(N)
    //one pass soln.
    private int[][] mergeIntervalsOptimal(int[][] intervals) {
        //first sort the intervals array
        //such that if start of intervals are same, compare the end of intervals.
        Arrays.sort(intervals, Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));

        //will store only the intervals to be returned.
        List<List<Integer>> ans = new ArrayList<>();

        //for each interval of the intervals array..
        for (int i=0; i<intervals.length; i++) {
            //if we have a valid interval and curr interval's start is lower than
            //last valid interval's end, swallow the curr interval and expand the
            //range of last interval if necessary.
            if (ans.size() > 0 && intervals[i][0] <= ans.get(ans.size()-1).get(1)) {
                //max used to handle edge case.
                //eg: [[15, 18], [16, 17]] should result in [15, 18], not [15, 17]
                ans.get(ans.size()-1).set(1, Math.max(intervals[i][1], ans.get(ans.size()-1).get(1)));
            } else {
                //otherwise, we either don't have a interval yet
                //or the curr interval's start doesn't lie in the last
                //valid interval. In either case we need to create a new
                //interval and push to valid intervals.
                List<Integer> invl = new ArrayList<>();
                invl.add(intervals[i][0]);
                invl.add(intervals[i][1]);
                ans.add(invl);
            }
        }

        //copy the list to an array ATQ.
        int[][] ansArr = new int[ans.size()][2];
        int i = 0;
        for (List<Integer> invl: ans) {
            ansArr[i][0] = invl.get(0);
            ansArr[i][1] = invl.get(1);
            i += 1;
        }


        return ansArr;
    }


    //2) Brute force T: O(NlogN) + O(3N), S: O(N)
    //Sort the intervals array so that all intervals fall in order
    //and then accumulate.
    private int[][] mergeIntervalsBrute(int[][] intervals) {
        //first sort the intervals array
        //such that if start of intervals are same, compare the end of intervals.
        Arrays.sort(intervals, Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));

        //will store only the intervals to be returned.
        List<List<Integer>> ans = new ArrayList<>();

        //for each interval of the intervals array..
        for (int i=0; i<intervals.length; i++) {
            //if we have found an interval
            //and the last interval's range exceeds current intervals,
            //means the current intervals is already merged and we don't need
            //to do anything for this interval.
            if (ans.size() > 0 && intervals[i][1] <= ans.get(ans.size()-1).get(1)) continue;

            //otherwise, we have a potential new interval, so we
            //start looking for the intervals that we can merge to current
            //interval, starting the next interval of the current.
            //If the next interval's start is smaller than curr interval's
            //end, mean the next interval can be swallowed by current interval.
            //so, update current interval's end to next interval's end (expand the current interval)
            //if next interval's end is higher.
            int j = i+1;
            while(j<intervals.length && intervals[j][0] <= intervals[i][1]) {
                //need to use max because of edge case.
                //eg: [[15, 18], [16, 17]] should result in [15, 18], not [15, 17]
                intervals[i][1] = Math.max(intervals[i][1], intervals[j][1]);
                j += 1;
            }

            //when hear, we have accumulated an interval,
            //so create and push a new interval.
            List<Integer> interval = new ArrayList<>();
            interval.add(intervals[i][0]);
            interval.add(intervals[i][1]);
            ans.add(interval);
        }

        //copy the list to an array ATQ.
        int[][] ansArr = new int[ans.size()][2];
        int i = 0;
        for (List<Integer> interval: ans) {
            ansArr[i][0] = interval.get(0);
            ansArr[i][1] = interval.get(1);
            i += 1;
        }


        return ansArr;
    }
}
