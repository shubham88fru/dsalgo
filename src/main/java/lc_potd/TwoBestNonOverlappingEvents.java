package lc_potd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/two-best-non-overlapping-events/description/
//@check - https://www.youtube.com/watch?v=XXWPM1DdnYk&t=1106s&ab_channel=codestorywithMIK
public class TwoBestNonOverlappingEvents {
    public int maxTwoEvents(int[][] events) {
        // return dp(events, null, 0, 2);

        Arrays.sort(events, (a1, a2) -> a1[0]-a2[0]);
        return dpModified(events, 0, 2, new HashMap<>());
    }

    /*
        First off, I could never think (probably) that this
        could be solved using knapsack pattern. However, mik
        said so and I tried. Look how the below soln would run but
        then there's no way I could have possibly memoized it.

        Then came the crazy modification of this approach. @see dpModified
    */
    private int dp(int[][] events, int[] selected, int curr, int left) {
        if (curr >= events.length) return 0;

        int attend = 0;
        if (left > 0 && canTake(selected, events[curr])) {
            attend = events[curr][2] + dp(events, events[curr], curr+1, left-1);
        }

        int skip = dp(events, selected, curr+1, left);

        return Math.max(attend, skip);
    }

    /*
        This is probably first of a kind of a question where to optimize the
        changing values in the recursion call, I have to use binary search
        in a recursive code. Crazy.

        Written by me but completely based on mik's approach.
    */
    private int dpModified(int[][] events, int curr, int left, Map<String, Integer> cache) {
        if (curr >= events.length || curr == -1) return 0;

        String key = curr + "_" + left;
        if (cache.containsKey(key)) return cache.get(key);

        int attend = 0;
        if (left > 0) {
            /**
             * Optimization:
             * Since the events array is sorted by start time,
             * instead of just doing `i+1` we'd rather do a
             * bs and find the first event after curr whose
             * start time is after curr's end time.
             */
            attend = events[curr][2] + dpModified(events, bs(events, events[curr][1]), left-1, cache);
        }

        int skip = dpModified(events, curr+1, left, cache);

        cache.put(key, Math.max(attend, skip));
        return Math.max(attend, skip);
    }

    private int bs(int[][] events, int end) {
        int l = 0;
        int r = events.length-1;

        int nextIdx = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (events[mid][0] > end) {
                nextIdx = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return nextIdx;
    }

    private boolean canTake(int prevEnd, int start) {
        if (prevEnd == -1) return true;

        return start > prevEnd;
    }

    private boolean canTake(int[] selected, int[] curr) {
        if (selected == null) return true;

        //trick to check if two intervals are overlapping or not.
        //doesn't care the order of first and second internval.
        //gives the right answer no matter what.
        return !(selected[0] <= curr[1] && curr[0] <= selected[1]);
    }
}
