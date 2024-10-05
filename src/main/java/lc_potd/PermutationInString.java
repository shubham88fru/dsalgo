package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/permutation-in-string/description/
//@check - https://www.youtube.com/watch?v=iTwwvsyUsi4&ab_channel=codestorywithMIK
public class PermutationInString {

    /*
        My soln. Mik's soln was on the same lines but looked cleaner.
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> mp = new HashMap<>();
        for (int i=0; i<s1.length(); i++) {
            mp.put(s1.charAt(i), mp.getOrDefault(s1.charAt(i), 0)+1);
        }

        int l = 0;
        int r = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (r < s2.length()) {
            char charr = s2.charAt(r);
            if (
                    mp.containsKey(charr)
            ) {
                r += 1;
                window.put(charr, window.getOrDefault(charr, 0)+1);
                mp.put(charr, mp.getOrDefault(charr, 0)-1);
                if (mp.get(charr) == 0) mp.remove(charr);
                if (mp.size() == 0) return true;
            } else {

                //shorten the window one-char from the left at a time.
                char toRemove = s2.charAt(l);
                if (window.containsKey(toRemove)) {
                    mp.put(toRemove, mp.getOrDefault(toRemove, 0)+1);
                    window.put(toRemove, window.get(toRemove)-1);
                    if (window.get(toRemove) == 0) window.remove(toRemove);
                }

                l += 1;
                if (l >= r) r += 1; //the catch.
            }
        }

        return false;
    }
}
