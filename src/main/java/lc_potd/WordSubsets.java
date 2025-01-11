package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/word-subsets/
public class WordSubsets {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // return brute(words1, words2);
        return pass2(words1, words2);
    }

    /*
        My soln. Mik had similar soln.
     */
    private List<String> pass2(String[] words1, String[] words2) {
        Map<Character, Integer> total = new HashMap<>();
        for (String word2: words2) {
            Map<Character, Integer> local = new HashMap<>();
            for (int i=0; i<word2.length(); i++) {
                char ch = word2.charAt(i);
                local.put(ch, local.getOrDefault(ch, 0) + 1);

                total.put(ch, Math.max(local.get(ch), total.getOrDefault(ch, 0)));
            }
        }


        List<String> ans = new ArrayList<>();
        for (String word1: words1) {
            Map<Character, Integer> local = new HashMap<>();
            for (int i=0; i<word1.length(); i++) {
                char ch = word1.charAt(i);
                local.put(ch, local.getOrDefault(ch, 0) + 1);
            }

            boolean matched = true;
            for (Map.Entry<Character, Integer> entry: total.entrySet()) {
                char key = entry.getKey();
                int value = entry.getValue();
                if (!local.containsKey(key) || (value > local.get(key))) {
                    matched = false;
                    break;
                }
            }

            if (matched) ans.add(word1);
        }

        return ans;
    }
}
