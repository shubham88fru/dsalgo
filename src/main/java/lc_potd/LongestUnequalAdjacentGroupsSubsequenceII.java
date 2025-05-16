package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@link - https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/description/
//@check - https://www.youtube.com/watch?v=geXrFFZXQTc&ab_channel=codestorywithMIK
public class LongestUnequalAdjacentGroupsSubsequenceII {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        // return pass1(words, groups);
        return pass2(words, groups);
    }

    //1) Mik's soln.
    //This is basically a variant for LIS problem where
    //instead of just returning the length, we need to
    //return the LIS itself.
    //LIS is optimally solved using bottomup dp. Topdown
    //dp soln for LIS doesn't work very well.
    //Following is mik's soln. The code is basically
    //that of bottom up LIS with slight modification.
    private List<String> pass2(String[] words, int[] groups) {
        int n = words.length;

        int[] dp = new int[n]; //dp[i] = length of longest subseq ending at index i.
        Arrays.fill(dp, 1);

        int[] parent = new int[n]; //for path construction - backtracking.
        Arrays.fill(parent, -1);

        int longestSubsequence = 1;
        int longestSubsequenceIdx = 0;
        for (int i=0; i < n; i++) {
            for (int j=0; j< i; j++) {
                if (condition(groups[j], groups[i], words[i], words[j])) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        parent[i] = j;

                        if (longestSubsequence < dp[i]) {
                            longestSubsequence = dp[i];
                            longestSubsequenceIdx = i;
                        }


                    }
                }
            }
        }

        List<String> ans = new ArrayList<>();
        while (longestSubsequenceIdx != -1) {
            ans.add(words[longestSubsequenceIdx]);
            longestSubsequenceIdx = parent[longestSubsequenceIdx];
        }

        Collections.reverse(ans);

        return ans;
    }

    private boolean condition(int grpi, int grpj, String wordi, String wordj) {
        return (grpj != grpi) && hammingOne(wordi, wordj);
    }

    //2) My topdown dp/backtracking approach - TLE
    private List<String> pass1(String[] words, int[] groups) {

        List<String> ans = new ArrayList<>();
        dp(words, groups, 0, -1, new ArrayList<>(), ans);

        return ans;
    }

    private void dp(String[] words, int[] groups, int i, int prev, List<String> curr, List<String> ans) {
        if (i >= words.length) {
            if (curr.size() > ans.size()) {
                ans.clear();
                ans.addAll(curr);

            }
            return;
        }

        if (prev == -1 || (groups[i] != groups[prev] && hammingOne(words[i], words[prev]))) {
            curr.add(words[i]);
            dp(words, groups, i+1, i, curr, ans);
            curr.remove(curr.size()-1);
        }

        dp(words, groups, i+1, prev, curr, ans);

    }

    private boolean hammingOne(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        if (n1 != n2) return false;

        int hd = 0;
        for (int i=0; i<n1; i++) {
            if (word1.charAt(i) != word2.charAt(i)) hd += 1;
            if (hd > 1) return false;
        }

        return hd == 1;
    }
}
