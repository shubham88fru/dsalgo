package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/largest-3-same-digit-number-in-string/
public class Largest3SameDigitNumberInString {
    public String largestGoodInteger(String num) {
        return revise(num);
    }

    /*
        Sliding window is an overkill for this problem.
        Since the length is fixed, we can just do brute force
        which will still have the same TC.
    */
    private String revise(String num) {
        int n = num.length();

        int l = 0;
        int r = 0;
        int max = 0;
        int ms = -1;
        int me = -1;
        Map<Character, Integer> window = new HashMap<>();

        while (r < n) {
            while (r < n && r-l < 3 && (window.isEmpty() || window.containsKey(num.charAt(r)))) {
                window.put(num.charAt(r), window.getOrDefault(num.charAt(r), 0)+1);
                r += 1;
            }

            if (r-l == 3) {
                char ch = num.charAt(l);
                if (Character.getNumericValue(ch) >= max) {
                    max = Character.getNumericValue(ch);
                    ms = l;
                    me = r;
                }
                l = r;
                window.clear();
            } else {
                char ch = num.charAt(l);
                window.put(ch, window.get(ch)-1);
                if (window.get(ch) == 0) window.remove(ch);
                l += 1;
            }
        }

        return ms == -1 ? "": num.substring(ms, me);
    }
}
