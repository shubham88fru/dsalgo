package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-ways-to-build-good-strings/description/
public class CountWaysToBuildGoodString {
    public int countGoodStrings(int low, int high, int zero, int one) {
        Map<Integer, Integer> cache = new HashMap<>();

        int ans = (dfs(low, high, zero, one, 0, cache));
        return ans;
    }

    private int dfs(int low, int high, int zero, int one, int len, Map<Integer, Integer> cache) {
        if (len > high) return 0;

        if (cache.containsKey(len)) return cache.get(len);

        //My soln, but calling both the dfs at same place
        //didn't quite click and could only come up with this
        //after failing a couple of times. So check mik also once
        //for (perhaps) a better explanation, if this is a recurring
        //problem for some company.
        int zeroLen = 0;
        if ((len) <= high) {
            zeroLen = ((len >= low) ? 1: 0) + dfs(low, high, zero, one, len+zero, cache) + dfs(low, high, zero, one, len+one, cache);
        }

        // long oneLen = 0;
        // if ((len) <= high) {
        //     oneLen = (((len >= low) ? 1: 0) + dfs(low, high, zero, one, len+one, cache));
        // }

        cache.put(len, zeroLen%1000000007);
        return cache.get(len);

    }
}
