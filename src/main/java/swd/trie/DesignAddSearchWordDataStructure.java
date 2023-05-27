package swd.trie;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
class DesignAddSearchWordDataStructure {
    TrieNode root;

    public DesignAddSearchWordDataStructure() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode crawler = this.root;
        //iterate through every charachter in the string
        for (char ch: word.toCharArray()) {
            //if curr char not child of curr node,
            //add it as a child to curr node.
            if (!crawler.children.containsKey(ch)) {
                TrieNode trieNode = new TrieNode();
                crawler.children.put(ch, trieNode);
            }

            //and eitherways, move to the next node in the trie.
            crawler = crawler.children.get(ch);
        }

        //after all words done, mark the node as
        //end of word node.
        crawler.isEndOfAWord = true;
    }

    public boolean search(String word) {
        return searchRecursive(word, 0, this.root);
    }

    private boolean searchRecursive(String word, int index, TrieNode root) {
        //if done with all chars of string, return true
        //if we're on a node that is a last word node.
        if (index >= word.length()) return root.isEndOfAWord;

        char currChar = word.charAt(index);

        //if curr char is a `.`, it can be matched with
        //any node, so..
        if ('.' == currChar) {
            boolean ans = false;
            //for each child of curr node..
            for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
                //...check if word can be found
                //by moving to next char in the string.
                ans = searchRecursive(word, index+1, entry.getValue());

                //if word found along any child, return true.. no
                //need to check further.
                if (ans) return true;
            }

            //if here, means the word not found along any
            //path from the node, return false. Not possible to find
            //the word.
            return false;
        }

        //otherwise, if curr char not contained in curr node's children,
        //then also the word cannot be present in the trie, so return false.
        if (!root.children.containsKey(currChar)) return false;

        //otherwise, curr char is curr node's child, so simply move to next
        //char of the string and keep searching.
        return searchRecursive(word, index+1, root.children.get(currChar));
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
 * Your DesignAddSearchWordDataStructure object will be instantiated and called as such:
 * DesignAddSearchWordDataStructure obj = new DesignAddSearchWordDataStructure();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */