package lc_potd;

import algorithms.KMP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/
public class CountPrefixAndSuffixPairsI {
    public int countPrefixSuffixPairs(String[] words) {
        // return idkIfOptimal(words);
        // return brute(words);
        return usingTrie(words);
    }

    //ig bruteforce is the best and cleanest soln for this q.
    private int brute(String[] words) {
        int count = 0;
        for (int i=0; i<words.length; i++) {
            for (int j=i+1; j<words.length; j++) {
                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {
                    count += 1;
                }
            }
        }

        return count;
    }

    //using kmp. overkill.
    private int idkIfOptimal(String[] words) {
        int count = 0;
        KMP kmp = new KMP();
        for (int i=0; i<words.length; i++) {
            for (int j=i+1; j<words.length; j++) {
                if (words[j].length() < words[i].length()) continue;
                List<Integer> matches = kmp.search(words[i], words[j]);
                if (matches.size() < 1) continue;
                int f = matches.get(0);
                int l = matches.get(matches.size()-1);

                if (f == 0 && (l + words[i].length()) == (words[j].length())) count += 1;
            }
        }

        return count;
    }

    //using trie.
    private int usingTrie(String[] words) {
        int count = 0;
        for (int i=0; i<words.length; i++) {
            for (int j=i+1; j<words.length; j++) {
                if (words[j].length() < words[i].length()) continue;
                TrieNode44 pTrie = new TrieNode44(); //prefix trie.
                TrieNode44 sTrie = new TrieNode44(); //suffix trie.
                insertToTrie(pTrie, words[j]);
                if (!searchInTrie(pTrie, words[i])) continue;
                insertToTrie(sTrie, (new StringBuffer(words[j])).reverse().toString());
                if (searchInTrie(sTrie, (new StringBuffer(words[i])).reverse().toString())) count += 1;
            }
        }

        return count;
    }

    private void insertToTrie(TrieNode44 root, String word) {
        TrieNode44 curr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.node.containsKey(ch)) {
                curr.node.put(ch, new TrieNode44());
            }
            curr = curr.node.get(ch);
        }
    }

    private boolean searchInTrie(TrieNode44 root, String pattern) {
        TrieNode44 curr = root;
        for (int i=0; i<pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (!curr.node.containsKey(ch)) return false;
            curr = curr.node.get(ch);
        }

        return true;
    }
}

class TrieNode44 {
    Map<Character, TrieNode44> node = new HashMap<>();
    char ch;
    public TrieNode44() {
    }
}