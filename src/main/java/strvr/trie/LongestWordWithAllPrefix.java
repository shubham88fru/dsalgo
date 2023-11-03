package strvr.trie;

/**
 * Code looks okay, but pain to run on CN b/c of some static keyword non-sense.
 */
//@link - https://www.codingninjas.com/studio/problems/complete-string_2687860
//@strvr - https://www.youtube.com/watch?v=AWnBa91lThI
public class LongestWordWithAllPrefix {
    public static String completeString(int n, String[] a) {
        // Write your code here.
        CNTrie trie = new CNTrie();
        for (String word: a) {
            trie.insert(word);
        }

        String longest = "";
        for (String word: a) {
            if (trie.checkIfPrefixExists(word)) {
                if (word.length() > longest.length()) longest = word;
            } else if (word.length() == longest.length()
                    && word.compareTo(longest) < 0) longest = word;
        }

        if (longest.equals("")) return "None";
        return longest;

    }


}

class CNTrie {
    private CNTrieNode root;
    public CNTrie() {
        root = new CNTrieNode();
    }

    public void insert(String word) {
        CNTrieNode node = root;
        for (int i=0; i<word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) node.put(word.charAt(i), new CNTrieNode());
            //move to next next for next iteration
            node = node.get(word.charAt(i));
        }

        //for some reason, if a node has last char of a word,
        //strvr marks the next node as endOfWord.
        node.setEnd();
    }

    boolean checkIfPrefixExists(String word) {
        CNTrieNode node = root;
        boolean flag = true;
        for (int i=0; i<word.length(); i++) {
            //every prefix should be an end of word in trie.
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
                flag &= node.isEndOfWord();
            } else return false;

        }

        return flag;
    }
}

class CNTrieNode {
    CNTrieNode links[] = new CNTrieNode[26];
    boolean isEndOfWord = false;

    boolean containsKey(char ch) {
        return links[ch-'a'] != null;
    }

    CNTrieNode get(char ch) {
        return links[ch-'a'];
    }

    void put(char ch, CNTrieNode node) {
        links[ch-'a'] = node;
    }

    void setEnd() {
        isEndOfWord = true;
    }

    boolean isEndOfWord() {
        return isEndOfWord;
    }
}