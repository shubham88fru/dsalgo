package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/vowel-spellchecker/
public class VowelSpellchecker {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        return pass1(wordlist, queries);
    }

    /**
     My initial intuition was to use trie
     but turns out this brute forceish soln is the best
     approach. Mik had the same soln.
     */
    private String[] pass1(String[] wordlist, String[] queries) {
        int m = wordlist.length;
        int n = queries.length;

        Set<String> exact = new HashSet<>();
        Map<String, String> caseMap = new HashMap<>();
        Map<String, String> vowelMap = new HashMap<>();

        for (int i=0; i<m; i++) {
            String word = wordlist[i];
            exact.add(word);
            if (!caseMap.containsKey(word.toLowerCase())) {
                caseMap.put(word.toLowerCase(), word);
            }

            StringBuilder sb = new StringBuilder();
            for (int j=0; j<word.length(); j++) {
                char ch = word.toLowerCase().charAt(j);
                if (isVowel(ch)) {
                    sb.append("*");
                } else sb.append(ch);
            }

            if (!vowelMap.containsKey(sb.toString())) {
                vowelMap.put(sb.toString(), word);
            }
        }

        String[] ans = new String[n];
        for (int i=0; i<n; i++) {
            String word = queries[i];

            if (exact.contains(word)) ans[i] = word;
            else if (caseMap.containsKey(word.toLowerCase())) ans[i] = caseMap.get(word.toLowerCase());
            else {
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<word.length(); j++) {
                    char ch = word.toLowerCase().charAt(j);
                    if (isVowel(ch)) {
                        sb.append("*");
                    } else sb.append(ch);
                }
                if (vowelMap.containsKey(sb.toString())) ans[i] = vowelMap.get(sb.toString());
                else ans[i] = "";
            }

        }

        return ans;
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
}
