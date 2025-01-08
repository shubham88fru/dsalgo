package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/unique-length-3-palindromic-subsequences/
//@check - https://www.youtube.com/watch?v=EdEvy2_o5OE&t=946s&ab_channel=codestorywithMIK
public class UniqueLength3PalindromicSubsequence {
    public int countPalindromicSubsequence(String s) {
        // Set<String> st = new HashSet<>();
        // dfs(s, "", 0, st);
        // return st.size();

        // return approach2(s);
        return approach3(s);
    }

    //TLE
    private void dfs(String og, String s, int curr, Set<String> st) {
        if (s.length() == 3 && s.charAt(0) == s.charAt(s.length()-1)) {
            st.add(s);
        }

        if (curr >= og.length()) return;

        dfs(og, s+Character.toString(og.charAt(curr)), curr+1, st);
        dfs(og, s, curr+1, st);
    }

    //TLE
    private int approach2(String s) {
        Map<Character, Integer> index = new HashMap<>();
        Map<Character, Integer> ans = new HashMap<>();
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (index.containsKey(ch) && (i-index.get(ch)+1)>=3) {
                int start = index.get(ch)+1;
                int end = i-1;
                Set<Character> st = new HashSet<>();
                for (int j=start; j<=end; j++) {
                    st.add(s.charAt(j));
                }

                count += (st.size() - ans.getOrDefault(ch, 0));

                ans.put(ch, st.size());
            }

            if (!index.containsKey(ch)) index.put(ch, i);
        }
        return count;
    }

    //based on Mik's approach (coded by me)
    private int approach3(String s) {
        Map<Character, Integer> fIndex = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (!fIndex.containsKey(ch)) {
                fIndex.put(ch, i); //first ocurrence of each char.
            }
        }

        Map<Character, Integer> lIndex = new HashMap<>();
        for (int i=s.length()-1; i>=0; i--) {
            char ch = s.charAt(i);
            if (!lIndex.containsKey(ch)) {
                lIndex.put(ch, i); //last ocurrence of each char.
            }
        }

        int count = 0;
        for (Map.Entry<Character, Integer> entry: fIndex.entrySet()) {
            char key = entry.getKey();
            int sI = entry.getValue();
            int lI = lIndex.getOrDefault(key, -1);
            if (lI == -1 || lI-sI < 2) continue;

            Set<Character> st = new HashSet<>();
            for (int i=sI+1; i<=lI-1; i++) {
                st.add(s.charAt(i));
            }

            count += st.size();
        }

        return count;
    }
}
