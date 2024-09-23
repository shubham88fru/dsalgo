package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/extra-characters-in-a-string/description/
public class ExtraCharactersInAString {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));

        Map<String, Integer> cache = new HashMap<>();
        return min(s, dict, cache);
    }

    //My dp solution.
    private int min(String s, Set<String> dict, Map<String, Integer> cache) {
        if (s.isEmpty()) return 0;

        if (cache.containsKey(s)) return cache.get(s);

        int min = Integer.MAX_VALUE;
        for (int i=0; i<s.length(); i++) {
            int contains = Integer.MAX_VALUE;
            int notContains = Integer.MAX_VALUE;
            String sub = s.substring(0, i+1);
            String rest = s.substring(i+1);
            if (!dict.contains(sub)) {
                contains = sub.length() + min(rest, dict, cache);
            } else {
                notContains = min(rest, dict, cache);
            }

            min = Math.min(min, Math.min(contains, notContains));
        }

        cache.put(s, min);
        return cache.get(s);
    }
}
