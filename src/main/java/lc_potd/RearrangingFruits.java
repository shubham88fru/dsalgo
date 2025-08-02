package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/rearranging-fruits/description/
//@check - https://www.youtube.com/watch?v=xveEovkFjBU&ab_channel=codestorywithMIK
public class RearrangingFruits {
    public long minCost(int[] basket1, int[] basket2) {
        return mikssol(basket1, basket2);
    }

    /*
        Coded by me, completely based on mik's
        explanation.
     */
    private long mikssol(int[] basket1, int[] basket2) {
        int min = Integer.MAX_VALUE;

        Map<Integer, Integer> mp = new HashMap<>();
        for (int num: basket1) {
            mp.put(num, mp.getOrDefault(num, 0)+1);
            min = Math.min(min, num);
        }

        for (int num: basket2) {
            mp.put(num, mp.getOrDefault(num, 0)-1);
            min = Math.min(min, num);
        }

        List<Integer> swappable = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            int abs = Math.abs(entry.getValue());
            if (abs%2 != 0) return -1;

            int num = entry.getKey();
            for (int i=0; i<abs/2; i++) {
                swappable.add(num);
            }
        }

        Collections.sort(swappable);

        long cost = 0;
        for (int i=0; i<swappable.size()/2; i++) {
            cost += (long)Math.min(swappable.get(i), (long)min*2);
        }

        return cost;
    }
}
