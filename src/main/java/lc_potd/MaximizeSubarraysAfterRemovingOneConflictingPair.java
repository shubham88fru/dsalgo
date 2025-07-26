package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/description/
//@check - https://www.youtube.com/watch?v=gX3dCYnHpug&ab_channel=codestorywithMIK
public class MaximizeSubarraysAfterRemovingOneConflictingPair {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        return mikssol(n, conflictingPairs);
    }

    private long mikssol(int n, int[][] conflictingPairs) {
        long subs = 0;

        List<List<Integer>> cfp = new ArrayList<>(); // conflicting pairs. For each end - list of starts.
        for (int i=0; i<=n; i++) cfp.add(new ArrayList<>());

        for (int[] cf: conflictingPairs) {
            int s = Math.min(cf[0], cf[1]);
            int e = Math.max(cf[0], cf[1]);
            cfp.get(e).add(s);
        }

        long[] extras = new long[n+1];
        long maxExtra = 0;

        int mStart = 0; //max start
        int smStart = 0; //second max start
        for (int end = 1; end <= n; end++) {
            for (int start: cfp.get(end)) {
                if (start >= mStart) {
                    smStart = mStart;
                    mStart = start;
                } else if (start > smStart) {
                    smStart = start;
                }
            }
            subs += (end - mStart);
            extras[mStart] += (mStart - smStart);
            maxExtra = Math.max(maxExtra, extras[mStart]);
        }

        return subs + maxExtra;
    }
}
