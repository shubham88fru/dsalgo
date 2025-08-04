package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/fruit-into-baskets/description/
public class FruitIntoBaskets {
    public int totalFruit(int[] fruits) {
        return pass1(fruits);
    }

    /* *
        My soln.
     */
    private int pass1(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> window = new HashMap<>();

        int l = 0;
        int r = 0;
        int max = 1;
        while (r < n) {
            while (r < n && window.size() <= 2) {
                window.put(fruits[r], window.getOrDefault(fruits[r], 0)+1);
                if (window.size() <= 2) max = Math.max(max, r-l+1);
                r += 1;
            }

            int rem = fruits[l];
            window.put(rem, window.get(rem)-1);
            if (window.get(rem) == 0) window.remove(rem);
            l += 1;
        }

        return max;
    }
}
