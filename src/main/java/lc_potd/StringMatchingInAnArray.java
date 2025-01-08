package lc_potd;

import algorithms.KMP;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/string-matching-in-an-array/
public class StringMatchingInAnArray {
    public List<String> stringMatching(String[] words) {
        // return revise(words);
        return optimal(words);
    }

    private List<String> revise(String[] words) {
        List<String> ans = new ArrayList<>();
        String concatenated = String.join("_", words);

        for (String word: words) {
            if (concatenated.indexOf(word) != concatenated.lastIndexOf(word)) {
                ans.add(word);
            }
        }

        return ans;
    }

    //optimal using the KMP algorithm.
    private List<String> optimal(String[] words) {
        KMP kmp = new KMP();
        List<String> ans = new ArrayList<>();

        StringBuilder text = new StringBuilder();
        for (int i=0; i<words.length; i++) {
            text.setLength(0);
            for (int j=0; j<words.length; j++) {
                if (i==j) continue;
                text.append(words[j]).append("_");
            }

            List<Integer> matches = kmp.search(words[i], text.toString());
            if (matches.size() > 0) ans.add(words[i]);
        }

        return ans;
    }
}
