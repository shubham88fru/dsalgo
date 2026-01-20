package lc_potd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//@link - https://leetcode.com/problems/design-authentication-manager/?

/*
    This is my intial intuition based soln.
    But this can be done in more optimal way
    using LRU cache technique using DLL.
*/
public class DesignAuthenticationManager {
    private int ttl;
    private Map<String, Integer> cache; //token v/s expiry.

    public DesignAuthenticationManager(int timeToLive) {
        this.ttl = timeToLive;
        this.cache = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        cache.put(tokenId, currentTime + ttl);
    }

    public void renew(String tokenId, int currentTime) {
        if (!cache.containsKey(tokenId)) return;

        int expiry = cache.get(tokenId);
        if (expiry <= currentTime) return;

        cache.put(tokenId, currentTime+ttl);
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;

        /**
         * In the constraints it's given that
         * `currentTime` will only be an increasing
         * value. Therefore, when iterating for a
         * given `currentTime` we can safely
         * delete the older ones because we know
         * that they will be older for the next
         * call also.
         */
        Iterator<Map.Entry<String, Integer>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() <= currentTime) {
                iterator.remove();
                continue;
            }
            count += 1;
        }

        return count;
    }
}
