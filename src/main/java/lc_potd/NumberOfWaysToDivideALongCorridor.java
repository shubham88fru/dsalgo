package lc_potd;

//@link - https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/description/
public class NumberOfWaysToDivideALongCorridor {
    public int numberOfWays(String corridor) {
        // return pass1(corridor);
        return pass2(corridor);
    }

    /**
     People were saying that this
     soln was very easy to come up with,
     but I couldn't come up with this
     approach myself.
     Approach is easy but didn't come
     to my mind directly.

     Coded by me based on hints from LC.
     Idea is to find the num of plans b/w
     two adjacent groups of seat pairs.
     */
    private int pass2(String corridor) {
        int n = corridor.length();

        int scount = 0;
        for (int i=0; i<n; i++) {
            if (corridor.charAt(i) == 'S') scount += 1;
        }
        if (scount == 0 || scount%2 != 0) return 0;

        int i = 0;
        long ans = 1l;
        scount = 0;
        while (i < n) {
            while (i < n && scount < 2) {
                if (corridor.charAt(i) == 'S') scount += 1;
                i += 1;
            }

            int pcount = 0;
            while (i < n && corridor.charAt(i) != 'S') {
                pcount += 1;
                i += 1;
            }

            if (i < n) ans = (ans * (pcount+1))%1000000007;
            scount = 0;
        }

        return (int)ans;
    }

    /**
     My first intuition was DP
     because of partition type problem
     but even with memo, it gives TLE
     */
    private int pass1(String corridor) {
        int n = corridor.length();
        int scount = 0;
        for (int i=0; i<n; i++) {
            if (corridor.charAt(i) == 'S') scount += 1;
        }
        if (scount == 0 || scount%2 != 0) return 0;
        // Map<Integer, Long> cache = new HashMap<>();
        Long[] cache = new Long[n+1];
        return (int)dp(0, n, corridor, cache);
    }

    private long dp(int i, int n, String corridor, /**Map<Integer, Long> cache*/Long[] cache) {
        if (i >= n) return 1;

        // if (cache.containsKey(i)) return cache.get(i);
        if (cache[i] != null) return cache[i];

        int cnt = 0;
        long splits = 0;
        for (int j=i; j<n; j++) {
            char ch = corridor.charAt(j);
            if (ch == 'S') cnt += 1;

            if (cnt == 2) {
                splits = (splits + dp(j+1, n, corridor, cache))%1000000007;
            } else if (cnt > 2) break;
        }

        // cache.put(i, splits);
        cache[i] = splits;
        return splits;
    }
}
