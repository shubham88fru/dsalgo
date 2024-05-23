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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */