package ptrn.trie;

import java.util.*;

public class SearchSuggestionSystem {
    private SSSTrieNode root = new SSSTrieNode();
    private SSSTrie trie = new SSSTrie(root);

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        //insert all the wordsd to trie.
        for (String product: products) {
            trie.insert(product);
        }

        List<List<String>> ans = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (char ch: searchWord.toCharArray()) {
            //after each key prefix, for a new prefix,
            //and search top 3 results for that prefix.
            sb.append(ch);
            ans.add(trie.search(sb.toString()));
        }

        return ans;

    }
}

class SSSTrie {
    private SSSTrieNode root;

    public SSSTrie(SSSTrieNode root) {
        this.root = root;
    }

    public void insert(String word) {
        SSSTrieNode curr = root;
        for (char ch: word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new SSSTrieNode());
            }
            curr = curr.children.get(ch);

            //each node will store all the the words that fan-out from it.
            curr.words.add(word);
        }
        curr.end = true;
    }

    public List<String> search(String prefix) {
        SSSTrieNode curr = root;
        for (char ch: prefix.toCharArray()) {
            //If the prefix doesn't pair up with any
            //word, return empty (ATQ)
            if (!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(ch);
        }

        return curr.getTop(3);
    }
}

class SSSTrieNode {
    Map<Character, SSSTrieNode> children;
    PriorityQueue<String> words; //each node will store all the the words that fan-out from it.
    boolean end;
    public SSSTrieNode() {
        this.children = new HashMap<>();
        this.words = new PriorityQueue<>((s1, s2) -> s1.compareTo(s2));
    }

    public List<String> getTop(int k) {
        List<String> topK = new ArrayList<>();
        while (!words.isEmpty() && k > 0) {
            topK.add(words.remove());
            k -= 1;
        }

        return topK;
    }

    public String toString() {
        return words.toString();
    }
}
