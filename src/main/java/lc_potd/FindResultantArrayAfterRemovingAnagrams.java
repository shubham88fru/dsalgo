package lc_potd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/?
public class FindResultantArrayAfterRemovingAnagrams {
    public List<String> removeAnagrams(String[] words) {
        return revise(words);
    }

    private List<String> revise(String[] words) {
        int n = words.length;

        Deque<String> stack = new ArrayDeque<>();
        for (String word: words) {
            if (stack.isEmpty() || !isAnagram(stack.peekFirst(), word)) {
                stack.addFirst(word);
            }
        }

        List<String> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.removeLast());
        }

        return ans;
    }

    private boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int[] freq = new int[26];

        for (int i=0; i<word1.length(); i++) {
            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);

            freq[ch1-'a'] += 1;
            freq[ch2-'a'] -= 1;
        }

        for (int i=0; i<26; i++) {
            if (freq[i] != 0) return false;
        }

        return true;
    }
}
