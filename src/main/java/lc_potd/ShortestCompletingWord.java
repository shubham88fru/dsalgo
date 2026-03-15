package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/shortest-completing-word/
public class ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        return pass1(licensePlate, words);
    }

    /*
        My soln.
     */
    private String pass1(String licensePlate, String[] words) {
        int n = words.length;
        String lower = licensePlate.toLowerCase();

        Map<Character, Integer> freq = new HashMap<>();
        for (int i=0; i<lower.length(); i++) {
            char ch = lower.charAt(i);
            if (Character.isDigit(ch) || !Character.isLetterOrDigit(ch)) continue;
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        int sz = freq.size(), minLen = Integer.MAX_VALUE, mini = -1;
        for (int j=0; j<n; j++) {
            boolean has = false;
            int currSz = 0;
            Map<Character, Integer> f = new HashMap<>();
            for (int i=0; i<words[j].length(); i++) {
                char ch = words[j].charAt(i);
                if (freq.containsKey(ch)) {
                    f.put(ch, f.getOrDefault(ch, 0)+1);
                    if (f.get(ch) == freq.get(ch)) currSz += 1;
                }
                if (currSz >= sz) {
                    has = true;
                    break;
                }
            }
            if (has && words[j].length() < minLen) {
                minLen = words[j].length();
                mini = j;
            }
        }

        return words[mini];
    }
}
