package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/
public class FindMostFrequentVowelAndConsonant {
    public int maxFreqSum(String s) {
        return pass1(s);
    }

    private int pass1(String s) {
        int n = s.length();

        int vc = 0;
        int cc = 0;
        Map<Character, Integer> mp = new HashMap<>();

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            mp.put(ch, mp.getOrDefault(ch, 0)+1);
            if (isVowel(ch)) vc = Math.max(vc, mp.get(ch));
            else cc = Math.max(cc, mp.get(ch));
        }
        return vc + cc;
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
}
