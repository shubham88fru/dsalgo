package ptrn.greedy;

import java.util.TreeMap;

//@link - https://leetcode.com/problems/hand-of-straights/description/
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n%groupSize != 0) return false;

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h: hand) {
            tm.put(h, tm.getOrDefault(h, 0)+1);
        }

        while (!tm.isEmpty()) {
            int key = tm.firstKey();
            int sz = groupSize;
            while (sz > 0) {
                if (!tm.containsKey(key)) return false;
                tm.put(key, tm.get(key)-1);
                if (tm.get(key) == 0) tm.remove(key);
                key += 1;
                sz -= 1;
            }
        }

        return true;

    }
}
