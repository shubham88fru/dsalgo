package ptrn.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Tries are also known as Prefix trees.
 */
//@link - https://leetcode.com/problems/implement-trie-prefix-tree/description/
//@strvr - https://takeuforward.org/data-structure/implement-trie-1/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6733522330189824
class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode crawler = this.root;
        //iterate through every character in the string
        for (char ch: word.toCharArray()) {
            //if curr char not child of curr node,
            //add it as a child to curr node.
            if (!crawler.children.containsKey(ch)) {
                TrieNode trieNode = new TrieNode();
                crawler.children.put(ch, trieNode);
            }

            //and either ways, move to the next node in the trie.
            crawler = crawler.children.get(ch);
        }

        //after the word is done inserting, mark the node as
        //end of word node.
        crawler.isEndOfAWord = true;
    }

    public boolean search(String word) {
        return searchInTrie(word, false);
    }

    public boolean startsWith(String prefix) {
        return searchInTrie(prefix, true);
    }

    private boolean searchInTrie(String word, boolean isPrefixSearch) {
        TrieNode crawler = this.root;
        //iterate through every character in the string
        for (char ch: word.toCharArray()) {
            if (!crawler.children.containsKey(ch)) return false;

            crawler = crawler.children.get(ch);
        }

        //if searching entire word, the last word must
        //be the end of word otherwise, if searching just for
        //the prefix and here means answer is already true.
        return isPrefixSearch || crawler.isEndOfAWord;
    }
}

class TrieNode {
    public char ch;
    public Map<Character, TrieNode> children = new HashMap<>();
    public boolean isEndOfAWord;

    public TrieNode() {

    }
}

/// /////////////////////////
/*
My revise soln using array instead of
hashmap
 */
class Trie3 {
    TrieNode2 root = new TrieNode2('\0');
    public Trie3() {

    }

    public void insert(String word) {
        int n = word.length();
        TrieNode2 curr = root;

        for (int i=0; i<n; i++) {
            char ch = word.charAt(i);
            if (curr.nodes[ch-'a'] == null) {
                TrieNode2 node = new TrieNode2(ch);
                curr.nodes[ch-'a'] = node;
            }
            curr = curr.nodes[ch-'a'];
        }
        curr.isEndOfWord = true;
    }

    public boolean search(String word) {
        int n = word.length();
        TrieNode2 curr = root;

        for (int i=0; i<n; i++) {
            char ch = word.charAt(i);
            if (curr.nodes[ch-'a'] == null) return false;
            curr = curr.nodes[ch-'a'];
        }

        return curr.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        int n = prefix.length();
        TrieNode2 curr = root;

        for (int i=0; i<n; i++) {
            char ch = prefix.charAt(i);
            if (curr.nodes[ch-'a'] == null) return false;
            curr = curr.nodes[ch-'a'];
        }

        return true;
    }
}

class TrieNode2 {
    char ch;
    TrieNode2[] nodes = new TrieNode2[26];
    boolean isEndOfWord;

    public TrieNode2(char ch) {
        this.ch = ch;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */