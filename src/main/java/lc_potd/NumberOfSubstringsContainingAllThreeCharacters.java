package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
public class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        // return pass1(s);
        return pass2(s);
    }

    /*
        My soln.
        But used a different way of writing sliding window.
        My usual style of sliding window (coded in pass1)
        was giving a hard time. Need to compare pass2 and pass1.
    */
    private int pass2(String s) {
        int n = s.length();
        int l = 0;
        int r = 0;

        int count = 0;
        Map<Character, Integer> freq = new HashMap<>();

        while (r < n) {
            char ch = s.charAt(r);
            freq.put(ch, freq.getOrDefault(ch, 0)+1);

            /*
                Once we have the required number of chars
                keep counting and releasing untill it becomes
                invalid.
            */
            while (freq.size() == 3) {
                count += (n-r);
                char toRem = s.charAt(l);
                freq.put(toRem, freq.get(toRem)-1);
                if (freq.get(toRem) == 0) freq.remove(toRem);
                l += 1;
            }


            r += 1;
        }

        return count;
    }

    private int pass1(String s) {
        int n = s.length();
        int l = 0;
        int r = 0;

        int count = 0;
        Map<Character, Integer> freq = new HashMap<>();
        while (r < n) {
            char ch = s.charAt(r);
            if (freq.size() < 3) {
                freq.put(ch, freq.getOrDefault(ch, 0)+1);

                if (freq.size() == 3) {
                    // System.out.println("A MP" + freq + " " + (n-r) + " L: " + l + " R: " + r);
                    count += (n-r);
                }
                r += 1;
            } else {
                char toRem = s.charAt(l);
                freq.put(toRem, freq.get(toRem)-1);
                if (freq.get(toRem) == 0) freq.remove(toRem);

                if (freq.size() == 3) {
                    // System.out.println("R MP" + freq + " " + (n-r) + " L: " + l + " R: " + r);

                    count += (n-r);
                }
                l += 1;
            }
        }

        while (l < n) {
            char toRem = s.charAt(l);
            freq.put(toRem, freq.get(toRem)-1);
            if (freq.get(toRem) == 0) freq.remove(toRem);

            if (freq.size() == 3) {
                // System.out.println("R MP" + freq + " " + (n-r) + " L: " + l + " R: " + r);

                count += 1;
            } else break;
            l += 1;
        }

        return count;

    }
}
