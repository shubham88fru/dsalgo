package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
public class FindTheLengthOfLongestCommonPrefix {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        return trie(arr1, arr2);
    }

    private int trie(int[] arr1, int[] arr2) {
        //put all nums of arr1 to trie.
        TrieNode root = insertInTrie(arr1);

        //then try each num of arr2 for
        //a prefix match against trie formed.
        int maxPref = 0;
        for (int num: arr2) {
            TrieNode curr = root;
            String numS = String.valueOf(num);
            for (int i=0; i<numS.length(); i++) {
                char ch = numS.charAt(i);
                if (curr.nodes.containsKey(ch)) {
                    curr = curr.nodes.get(ch);
                    maxPref = Math.max(maxPref, i+1); //record the max prefix so far.
                } else break;
            }
        }

        return maxPref;
    }

    private TrieNode insertInTrie(int[] arr1) {
        TrieNode root = new TrieNode();

        for (int num: arr1) {
            TrieNode curr = root;
            String numS = String.valueOf(num);
            for (int i=0; i<numS.length(); i++) {
                char ch = numS.charAt(i);
                if (!curr.nodes.containsKey(ch)) {
                    curr.nodes.put(ch, new TrieNode());
                }
                curr = curr.nodes.get(ch);

            }
            curr.endOfWord = true;
        }

        return root;
    }
}

class TrieNode {
    Map<Character, TrieNode> nodes = new HashMap<>();
    boolean endOfWord;
}