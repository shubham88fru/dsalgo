package lc_potd;

import java.util.Map;

//@link - https://leetcode.com/problems/best-sightseeing-pair
public class BestSightSeeingPair {
    public int maxScoreSightseeingPair(int[] values) {
        Integer[][] dp = new Integer[values.length+1][3];
        return dfs1(values, 0, 2, dp);
    }

    /**
     Both of the following are my soln,
     but dfs1 works with memo, but dfs2 doesn't
     */

    private int dfs1(int[] values, int idx, int sign, Integer[][] dp) {
        if (sign <= 0) return 0;
        if (idx >= values.length) return -999999999;

        if (dp[idx][sign] != null) return dp[idx][sign];
        int plus = values[idx] + ((int)Math.pow(-1,sign) * idx) + dfs1(values, idx+1, sign-1,  dp);

        int notPick = dfs1(values, idx+1, sign, dp);
        dp[idx][sign] = Math.max(plus, notPick);

        return Math.max(plus, notPick);
    }

    private int dfs2(int[] values, int idx, int count, int sum, Map<String, Integer> cache) {
        if (count <= 0) return sum;
        if (idx >= values.length) return -1;

        String key = sum + "_"  + idx + "_" + count;
        if (cache.containsKey(key)) return cache.get(key);

        int plus = 0;
        if (count > 1) {
            plus = dfs2(values, idx+1, count-1, sum + values[idx] + idx, cache);
        }

        int minus = dfs2(values, idx+1, count-1, sum + values[idx] - idx, cache);

        int notPick = dfs2(values, idx+1, count, sum, cache);

        cache.put(key, Math.max(plus, Math.max(minus, notPick)));

        return cache.get(key);
    }
}
