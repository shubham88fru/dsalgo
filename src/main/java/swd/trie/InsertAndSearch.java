package swd.trie;

/*
* With Tries we can do partial word matching.
*
* */

//@link - https://practice.geeksforgeeks.org/problems/trie-insert-and-search0651/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class InsertAndSearch {
    //Function to insert string into TRIE.
    static void insert(GfgTrieNode root, String word) {
        GfgTrieNode crawler = root;

        //Iterate through each char of string (a string is stored a hierarchy of chars in a trie)
        for (char currentChar : word.toCharArray()) {
            //For this question, gfg has used an array indexed
            //such that 0 --> 'a', 1--> 'b'... 25 --> 'z'
            int index = (currentChar - 'a');

            //if currchar is not curr node's child, then
            //insert the char a node and child to curr node.
            if (crawler.children[index] == null) {
                crawler.children[index] = new GfgTrieNode();
            }

            //and either ways, move to next node.
            crawler = crawler.children[index];
        }

        //finally, after we're done storing the string,
        //mark the last char as end of word.
        crawler.isEndOfWord = true;
    }

    //Function to use TRIE data structure and search the given string.
    static boolean search(GfgTrieNode root, String word) {
        GfgTrieNode crawler = root;
        //Iterate through each char of string (a string is stored a hierarchy of chars in a trie)
        for (char currentChar : word.toCharArray()) {
            //For this question, gfg has used an array indexed
            //such that 0 --> 'a', 1--> 'b'... 25 --> 'z'
            int index = (currentChar - 'a'); // 'a' - 'a' = 0, 'd' - 'a' = 3 and so on..

            //if curr char is curr node's child, simply
            //move along that hierarchy.
            if (crawler.children[index] != null) {
                crawler = crawler.children[index];
            } else return false; //else at any point if curr char isn't a child, means the string is not store in trie.
        }

        //if here, means all chars of string are present in the trie,
        //but need to verify if we're at a node that is the end of word.
        //eg: input string may be "bad" and trie may contain "bade"
        return crawler.isEndOfWord;
    }
}


// trie node
class GfgTrieNode {

    static final int ALPHABET_SIZE = 26;

    GfgTrieNode[] children = new GfgTrieNode[ALPHABET_SIZE];

    // isEndOfWord is true if the node represents
    // end of a word
    boolean isEndOfWord;

    GfgTrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < ALPHABET_SIZE; i++) children[i] = null;
    }
};
