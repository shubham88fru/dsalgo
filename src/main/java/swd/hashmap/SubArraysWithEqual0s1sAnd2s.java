package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/equal-0-1-and-23208/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class SubArraysWithEqual0s1sAnd2s {
    long getSubstringWithEqual012(String s) {
        // code here
        long ans = 0;

        int z0 = 0;
        int z1 = 0;
        int z2 = 0;

        Map<String, Integer> memo = new HashMap<>();
        memo.put((z1-z0) + "#" + (z2-z1), 1); //putting (0#0, 1) initially.

        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '0') z0 += 1;
            else if (s.charAt(i) == '1') z1 += 1;
            else z2 += 1;

            String exp = (z1-z0) + "#" + (z2-z1);

            if (memo.containsKey(exp)) {
                ans += memo.get(exp);
                memo.put(exp, memo.get(exp)+1);
            } else memo.put(exp, 1);
        }
        return ans;
    }
}
