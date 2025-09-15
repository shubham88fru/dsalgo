package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/maximum-number-of-words-you-can-type/?
public class MaximumNumberOfWordsYouCanType {
    public int canBeTypedWords(String text, String brokenLetters) {
        return revise(text, brokenLetters);
    }

    /**
     * My soln.
     * Probably a better of doing this problem
     * is to not split the text, we can just
     * write some checks for space to see if
     * we have hit a new word.
     */
    private int revise(String text, String brokenLetters) {
        Set<Character> st = new HashSet<>();
        for (int i=0; i<brokenLetters.length(); i++) {
            char ch = brokenLetters.charAt(i);
            st.add(ch);
        }

        int count = 0;
        String[] words = text.split(" ");
        for (String word: words) {
            boolean can = true;
            for (int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if (st.contains(ch)) {
                    can = false;
                    break;
                }
            }

            if (can) count += 1;
        }

        return count;
    }
}
