package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/maximum-total-damage-with-spell-casting/description/?
//@check - https://www.youtube.com/watch?v=i0hOOfgEJaU
public class MaximumTotalDamageWithSpellCasting {
    public long maximumTotalDamage(int[] power) {
        return mikssol(power);
    }

    /**
     Coded by me based on mik's explanation.
     This is top-down approach but mik also showed
     bottom-up approach. So revise if this is a faq
     for some company.

     This problem is very similar to https://leetcode.com/problems/delete-and-earn/
     which swd solved back in 2022
     */
    private long mikssol(int[] power) {
        int n = power.length;

        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> lst = new ArrayList<>();
        for (int p: power) {
            if (!freq.containsKey(p)) lst.add(p);
            freq.put(p, freq.getOrDefault(p, 0)+1);
        }
        Collections.sort(lst); //sort so we can do binary search during dp.

        Map<Integer, Long> mp = new HashMap<>();
        return dp(lst, freq, 0, mp);
    }

    private long dp(List<Integer> lst, Map<Integer, Integer> freq, int i, Map<Integer, Long> cache) {
        if (i < 0) return 0;
        if (i >= lst.size()) return 0;

        if (cache.containsKey(i)) return cache.get(i);

        int nextI = binarySearch(lst, lst.get(i)+2, i+1); //next index larger than +2
        long pick = (long)lst.get(i) * freq.get(lst.get(i)) + dp(lst, freq, nextI, cache);
        long nPick = dp(lst, freq, i+1, cache);

        cache.put(i, Math.max(pick, nPick));
        return cache.get(i);
    }

    private int binarySearch(List<Integer> lst, int num, int l) {
        int r = lst.size()-1;

        int idx = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (lst.get(mid) <= num) {
                l = mid + 1;
            } else {
                r = mid - 1;
                idx = mid;
            }
        }

        return idx;
    }
}
