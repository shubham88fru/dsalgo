package lc_potd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//@link - https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/?
//@check - https://www.youtube.com/watch?v=pqYk8mUEK18
public class MaximumScoreOfNonOverlappingIntervals {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        return mikssol(intervals);
    }

    /**
     * A pretty nasty problem to code.
     * Coded by me completely based on
     * mik's explanation.
     */
    private int[] mikssol(List<List<Integer>> intervals) {
        int n = intervals.size();

        //mutating the given intervals
        //to store the og index, maybe
        //not the best idea, if in interview,
        //ask, and if interviewer not ok,
        //then create a copy of intervals
        //and store the index there.
        for (int i=0; i<n; i++) {
            intervals.get(i).add(i);
        }

        Comparator<List<Integer>> cmp1 = (l1, l2) -> l1.get(0) - l2.get(0);
        Comparator<List<Integer>> cmp2 = (l1, l2) -> l1.get(1) - l2.get(1);
        Collections.sort(intervals, cmp1.thenComparing(cmp2));

        //pre-compute the next index.
        for (int i=0; i<n; i++) {
            intervals.get(i).add(binarySearchForFirstNext(intervals, i+1, n-1, intervals.get(i).get(1)));
        }

        Answer[][] memo = new Answer[n+1][5];
        Answer result = dp(intervals, 0, 4, memo);

        return result.lst.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Answer dp(List<List<Integer>> intervals, int i, int k, Answer[][] memo) {
        if (i >= intervals.size() || k <= 0) return new Answer();

        if (memo[i][k] != null) return memo[i][k];

        Answer skip = dp(intervals, i+1, k, memo);

        int wt = intervals.get(i).get(2);
        int ogi = intervals.get(i).get(3);
        int ni = intervals.get(i).get(4);
        Answer pick = dp(intervals, ni, k-1, memo);

        Answer take = new Answer();
        take.sum = (long)wt + pick.sum;
        take.lst.addAll(pick.lst);
        take.lst.add(ogi);
        Collections.sort(take.lst);

        Answer ans = null;

        if (skip.sum > take.sum) {
            ans = skip;
        } else if (take.sum > skip.sum) {
            ans = take;
        } else {
            ans = skip;
            int cmp = compareLists(skip.lst, take.lst);
            if (cmp > 0) ans = take;
        }

        memo[i][k] = ans;
        return ans;
    }

    private int binarySearchForFirstNext(List<List<Integer>> intervals, int start, int end, int target) {
        int l = start, r = end;

        int index = end+1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            int s = intervals.get(mid).get(0);
            if (s > target) {
                // index = intervals.get(mid).get(3);
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return index;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int minLen = Math.min(a.size(), b.size());
        for (int i = 0; i < minLen; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }
        return Integer.compare(a.size(), b.size());
    }
}

class Answer {
    long sum = 0;
    List<Integer> lst = new ArrayList<>();

    public String toString() {
        return lst.toString() + " = " + sum;
    }
}
