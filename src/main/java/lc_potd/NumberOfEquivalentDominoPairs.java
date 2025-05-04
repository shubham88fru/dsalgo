package lc_potd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        return revise(dominoes);
    }


    /* *
        My soln. I think this is also a constant space
        soln (because i and j are limited) but mik was like
        since we're using maps this ain't a constant space
        soln.
        Mik showed another approach using a fixed sized
        array.
     */
    private int revise(int[][] dominoes) {
        Map<String, Integer> mp = new HashMap<>();

        int count = 0;
        for (int[] domino: dominoes) {
            Arrays.sort(domino);
            String key = Arrays.toString(domino);

            if (mp.containsKey(key)) {
                count += mp.get(key);
            }

            mp.put(key, mp.getOrDefault(key, 0)+1);
        }

        return count;
    }
}
