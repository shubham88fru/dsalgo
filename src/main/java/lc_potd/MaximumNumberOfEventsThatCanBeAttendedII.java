package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
public class MaximumNumberOfEventsThatCanBeAttendedII {
    public int maxValue(int[][] events, int k) {
        return pass1(events, k);
    }

    private int pass1(int[][] events, int k) {
        Arrays.sort(events, (e1, e2) -> e1[0] - e2[0]);
        // Map<String, Integer> memo = new HashMap<>();
        // return dp(events, k, 0, -1, memo);
        // return dp2(events, k, 0, memo);
        Integer[][] memo = new Integer[events.length+1][k+1];
        return dp3(events, k, 0, memo);
    }

    /*
        1. 2d top-down DP with binary search.
     */
    private int dp3(int[][] events, int k, int curr, Integer[][] memo) {
        if (k <= 0) return 0;
        if (curr >= events.length) return 0;

        if (memo[curr][k] != null) return memo[curr][k];

        int next = binarySearch(events, curr);

        int pick = events[curr][2] + dp3(events, k-1, next, memo);

        int npick = dp3(events, k, curr+1, memo);

        memo[curr][k] = Math.max(pick, npick);
        return Math.max(pick, npick);
    }

    private int binarySearch(int[][] events, int curr) {
        int l = curr+1;
        int r = events.length-1;

        int next = events.length+1;
        while (l <= r) {
            int mid = l + (r-l)/2;

            if (events[mid][0] > events[curr][1]) {
                next = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return next;
    }

    /*
        2. Top-down dp, when picking a event and moving to
        the next event, we loop and directly jump to
        the event thats' feasible (this makes it a 2d dp).
        Still TLE.
    */
    private int dp2(int[][] events, int k, int curr, Map<String, Integer> memo) {
        if (k <= 0) return 0;
        if (curr >= events.length) return 0;

        String key = k + "_" + curr;
        if (memo.containsKey(key)) memo.get(key);

        int j = curr+1;
        for (;j<events.length; j++) {
            if (events[j][0] > events[curr][1]) break;
        }
        int pick = events[curr][2] + dp2(events, k-1, j, memo);  ;

        int npick = dp2(events, k, curr+1, memo);

        memo.put(key, Math.max(pick, npick));
        return Math.max(pick, npick);
    }

    /*
        3. Simple Top-down dp, with keeping track of prev picked
        event (this makes it a 3d dp).
        TLE.
     */
    private int dp(int[][] events, int k, int curr, int pi, Map<String, Integer> memo) {
        if (k <= 0) return 0;
        if (curr >= events.length) return 0;

        String key = k + "_" + curr + "_" + pi;
        if (memo.containsKey(key)) memo.get(key);

        int pick = 0;
        if (pi == -1 || events[curr][0] > events[pi][1]) {
            pick += events[curr][2] + dp(events, k-1, curr+1, curr, memo);
        }

        int npick = dp(events, k, curr+1, pi, memo);

        memo.put(key, Math.max(pick, npick));
        return Math.max(pick, npick);
    }
}
