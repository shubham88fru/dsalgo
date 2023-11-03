package strvr.trie;

//@link - https://www.codingninjas.com/studio/problems/count-distinct-substrings_985292
//@strvr - https://takeuforward.org/data-structure/number-of-distinct-substrings-in-a-string-using-trie/
public class CountDistinctSubstringUsingTrie {
    int cnt = 0;

    public int countDistinctSubstrings(String s) {
        CNTrieNode root = new CNTrieNode();
        for (int i=0; i<s.length(); i++) {
            CNTrieNode node = root;
            for (int j=i; j<s.length(); j++) {
                if (!node.containsKey(s.charAt(j))) {
                    node.put(s.charAt(i), new CNTrieNode());
                    cnt += 1;
                }
                node = node.get(s.charAt(j));
            }
        }
        return cnt+1; //+1 for the empty string.
    }
}
