package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/sum-of-prefix-scores-of-strings/
//@check - https://www.youtube.com/watch?v=PRHedN3h2Gc&t=1328s&ab_channel=codestorywithMIK
public class SumOfPrefixScoresOfStrings {
    public int[] sumPrefixScores(String[] words) {
        return trie(words);
    }

    /**
     * My soln, but took slight hint from Mik.
     */
    private int[] trie(String[] words) {
        TrieNode2 root = insertInTrie(words);

        List<Integer> ans = new ArrayList<>();
        for (String word: words) {
            ans.add(countPrefix(word, root));
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    private int countPrefix(String word, TrieNode2 root) {

        TrieNode2 curr = root;
        int cnt = 0;
        /**
         For each prefix of the current word,
         get the information from trie as to how many
         times that prefix occurred amond all the words
         of the array.
         */
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.node.containsKey(ch)) return cnt;
            curr = curr.node.get(ch);

            /**
             Count how many times current prefix ocurred
             in all the words of the array. This information
             was pre calculated and stored when we were inserting
             the words in trie.
             */
            cnt += curr.countPrefix;
        }

        return cnt;

    }

    private TrieNode2 insertInTrie(String[] words) {
        TrieNode2 root = new TrieNode2();
        for (String word: words) {
            TrieNode2 curr = root;
            for (int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if (!curr.node.containsKey(ch)) {
                    curr.node.put(ch, new TrieNode2());
                }
                curr = curr.node.get(ch);

                /**
                 Each time we visit a node, we are visiting
                 it a for a different word of the array. It means
                 that each time we visit a node, that node is actually
                 a prefix of that word.
                 */
                curr.countPrefix += 1;

            }
            curr.endOfWord = true;
        }

        return root;
    }
}

class TrieNode2 {
    Map<Character, TrieNode2> node = new HashMap<>();
    boolean endOfWord;
    int countPrefix; //how many times this prefix has occurred.
}