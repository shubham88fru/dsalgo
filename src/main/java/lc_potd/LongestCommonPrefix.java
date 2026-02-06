package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // return brute(strs);
        // return trie(strs);
        return optimal(strs);
    }

    private String optimal(String[] strs) {
        int n = strs.length;

        int maxi = -1;
        for (int i=0; i<strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            boolean end = false;
            for (int j=1; j<strs.length; j++) {
                if (strs[j].length() < i+1 || strs[j].charAt(i) != ch) {
                    end = true;
                    break;
                }
            }

            if (end) break;
            maxi = i;
        }

        if (maxi == -1) return "";

        return strs[0].substring(0, maxi+1);
    }

    /**
     This is my trie soln.
     However, I don't think this
     anyhow helps in TC, infact
     SC wise its worse.
     */
    private String trie(String[] strs) {
        int n = strs.length;

        //add first string.
        Trie root = new Trie();
        Trie curr = root;
        int maxi = strs[0].length();
        for (int j=0; j<strs[0].length(); j++) {
            char ch = strs[0].charAt(j);
            Trie node = new Trie();
            curr.data.put(ch, node);
            curr = curr.data.get(ch);
        }


        //match the remaining.
        for (int i=1; i<n; i++) {
            curr = root;
            boolean none = true;
            int j = 0;
            for (; j<strs[i].length(); j++) {
                char ch = strs[i].charAt(j);
                if (!curr.data.containsKey(ch)) {
                    break;
                }
                curr = curr.data.get(ch);
                none = false;
            }

            if (none) return "";
            maxi = Math.min(maxi, j);
        }

        return strs[0].substring(0, maxi);

    }

    private String brute(String[] strs) {
        int n = strs[0].length();

        int maxi = -1;
        for (int i=0; i<n; i++) {
            boolean prefix = true;
            for (int k=1; k<strs.length; k++) {
                if ((strs[k].length() < i+1) || !strs[k].startsWith(strs[0].substring(0, i+1))) {
                    prefix = false;
                    break;
                }
            }

            if (prefix) {
                maxi = i;
            } else break;
        }

        if (maxi == -1) return "";
        return strs[0].substring(0, maxi+1);
    }
}

class Trie {
    Map<Character, Trie> data = new HashMap<>();
}