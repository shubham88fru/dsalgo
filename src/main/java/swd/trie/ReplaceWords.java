package swd.trie;

import java.util.List;

//@link - https://leetcode.com/problems/replace-words/description/
public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNodeOfAlphabets root = new TrieNodeOfAlphabets();

        //Insert the dictionary words (prefixes) instead
        //of the sentence words, because when we search
        //for a sentence word, we can end the search immediately
        //and replace the sentence word with that prefix. This will
        //help us acheive the requirement of replacing a word with the
        //smallest prefix.
        for (String word: dictionary) {
            insertWord(word, root);
        }

        //Search each sentence word in the trie of prefixes
        //and replace the word if we find a prefix.
        //a return of null from  the search method means, we did not
        //find a prefix for the word and hence no need to replace it
        //with any other word. Simply move to next word in that case.
        //Otherwise, if search call returns a word, replace the curr word
        //in array with the returned word.
        String[] swords = sentence.split(" ");
        for (int i=0; i<swords.length; i++) {
            String res = search(swords[i], root);
            if (res != null) {
                swords[i] = res;
            }
        }

        //join the new array of replaces words and return.
        return String.join(" ", swords);
    }

    //simple creation of a trie.
    private void insertWord(String word, TrieNodeOfAlphabets root) {
        TrieNodeOfAlphabets crawler = root;

        for (char ch: word.toCharArray()) {
            if (crawler.children[ch-'a'] == null) {
                crawler.children[ch-'a'] = new TrieNodeOfAlphabets();
            }

            crawler = crawler.children[ch-'a'];
        }

        //at the end of iteration, insert the entire
        //word so that when we are searching, and we see that
        //crawler.word is not null, we know that we are at the valid
        //prefix and the word being searched can be replaced with this prefix.
        crawler.word = word;
    }

    private String search(String word, TrieNodeOfAlphabets root) {
        TrieNodeOfAlphabets crawler = root;

        //search the word, if at any point we don't find
        //a letter of word in the trie we return null.
        //Otherwise, as soon we find a end of word in the trie, we know that
        //this is smallest prefix and we return the prefix so that caller can
        //replace the original word with this prefix.
        for (char ch: word.toCharArray()) {
            if (crawler.children[ch-'a'] == null) return null;
            if (crawler.children[ch-'a'].word != null) return crawler.children[ch-'a'].word;
            crawler = crawler.children[ch-'a'];
        }

        return null;
    }
}

//Note that here, we'll insert the entire word
//as indicator of end of word instead of true-false.
//Doing this will help us in knowing with what word should
//a word in the sentence be replaced with.
class TrieNodeOfAlphabets {
    TrieNodeOfAlphabets[] children;
    String word;

    public TrieNodeOfAlphabets() {
        children = new TrieNodeOfAlphabets[26];
    }
}
