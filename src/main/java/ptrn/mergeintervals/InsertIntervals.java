package ptrn.mergeintervals;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/insert-interval/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6471579656781824
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        return insertInterval(intervals, newInterval);
        //return insertIntervalBrute(intervals, newInterval);
    }

    //1) Optimal solution
    private int[][] insertInterval(int[][] intervals, int[] newInterval) {
        /**
         * Note that, in general, for merge interval pattern to work,
         * we need to ensure two things -
         * 1) The og intervals array must be sorted (if not, then sort yourself.)
         * 2) The og intervals array must be non overlapping itself.
         *
         * Only when these conditions are met, can we proceed with the rest of
         * merge intervals algorithm.
         */
        //For this question, no need to sort, since it is ensured
        //that the intervals arrays is sorted in ascending order.

        //will store only the intervals to be returned.
        List<List<Integer>> ans = new ArrayList<>();

        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int i=0;
        int n = intervals.length;

        //copy all the intervals of og array
        //which start before the newstart into ans.
        while (i < n && intervals[i][0] < newStart) {
            List<Integer> invl = new ArrayList<>();
            invl.add(intervals[i][0]);
            invl.add(intervals[i][1]);
            ans.add(invl);

            i += 1;
        }

        //at this point, if ans is empty means newStart (or the newInterval)
        //starts before all the intervals of og array. Else, if ans is not empty
        //then there could be two situations - First, the newStart could be greater
        //the last inserted interval, in which case, we'll simply add the newInterval as
        //a brand new interval. Second, the newStart could be smaller than the last interval,
        //in which case, we'll merge the last interval and the newInterval.
        if (ans.size() == 0 || ans.get(ans.size()-1).get(1) < newStart) {
            List<Integer> invl = new ArrayList<>();
            invl.add(newInterval[0]);
            invl.add(newInterval[1]);
            ans.add(invl);
        } else {
            ans.get(ans.size()-1).set(1, Math.max(ans.get(ans.size()-1).get(1), newEnd));
        }

        //From here on, we are sure that the newInterval has been adjusted properly
        //in the ans. And so, we continued in the rest of intervals array to adjust
        //the remaining intervals if they got disturbed due to this new addtion.
        while (i < n) {

            if (intervals[i][0] <= ans.get(ans.size()-1).get(1)) {
                //max used to handle edge case.
                //eg: [[15, 18], [16, 17]] should result in [15, 18], not [15, 17]
                ans.get(ans.size()-1).set(1, Math.max(intervals[i][1], ans.get(ans.size()-1).get(1)));
            } else {

                List<Integer> invl = new ArrayList<>();
                invl.add(intervals[i][0]);
                invl.add(intervals[i][1]);
                ans.add(invl);
            }

            i += 1;
        }

        //copy the list to an array ATQ.
        int[][] ansArr = new int[ans.size()][2];
        int j = 0;
        for (List<Integer> invl: ans) {
            ansArr[j][0] = invl.get(0);
            ansArr[j][1] = invl.get(1);
            j += 1;
        }


        return ansArr;
    }

    //2) Kinda brute force.
    //insert the interval in the given intervals (in order)
    private int[][] insertIntervalBrute(int[][] intervals, int[] newInterval) {
        int num = intervals.length;
        int[][] ans = new int[num+1][2];
        int pos = -1;

        //find the place, where the newInterval should be inserted.
        //and copy the the original intervals array to the new one (to accomodate the extra element)
        for (int i=0; i<num; i++) {
            if (intervals[i][0] > newInterval[0] && pos == -1) {
                pos = i;

            }

            ans[i][0] = intervals[i][0];
            ans[i][1] = intervals[i][1];
        }

        int curra = newInterval[0];
        int currb = newInterval[1];
        //if we didn't find a valid position (because newInterval[0] is greater than all)
        //insert the new interval at the end directly.
        if (pos == -1) {
            ans[num][0] = curra;
            ans[num][1] = currb;
            return mergeIntervalsOptimal(ans);
        }

        //insert the new interval (in sorted order)
        for (int i=0; i<num+1; i++) {

            int nexta = 0;
            int nextb = 0;
            if (i >= pos) {
                nexta = ans[i][0];
                nextb = ans[i][1];
                ans[i][0] = curra;
                ans[i][1] = currb;
                curra = nexta;
                currb = nextb;
            }
        }

        //finally, call the merge interval algorithm
        //on the sorted intervals array.
        return mergeIntervalsOptimal(ans);
    }


    //one pass soln.
    private int[][] mergeIntervalsOptimal(int[][] intervals) {
        //For this question, no need to sort, since it is ensured
        //that the intervals arrays is sorted in ascending order.
        //Arrays.sort(intervals, Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));

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
}
