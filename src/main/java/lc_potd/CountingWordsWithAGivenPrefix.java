package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/counting-words-with-a-given-prefix/
public class CountingWordsWithAGivenPrefix {
    public int prefixCount(String[] words, String pref) {
        return usingTrie(words, pref);
    }

    private int usingTrie(String[] words, String pref) {
        TrieNode45 root = new TrieNode45();
        insertInTrie(words, root);

        TrieNode45 curr = root;
        int cnt = 0;
        for (int i=0; i<pref.length(); i++) {
            char ch = pref.charAt(i);
            if (!curr.node.containsKey(ch)) return 0;
            cnt = curr.node.get(ch).cnt;
            curr = curr.node.get(ch);
        }

        return cnt;
    }

    private void insertInTrie(String[] words, TrieNode45 root) {
        for (String word: words) {
            TrieNode45 curr = root;
            for (int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if (!curr.node.containsKey(ch)) {
                    TrieNode45 tn = new TrieNode45();
                    curr.node.put(ch, tn);
                } else {
                    curr.node.get(ch).cnt += 1;
                }

                curr = curr.node.get(ch);
            }
        }
    }
}

class TrieNode45 {
    Map<Character, TrieNode45> node = new HashMap<>();
    int cnt = 1;
    public TrieNode45() {

    }
}