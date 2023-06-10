package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-palindromic-subsequence/description/
public class LongestPalindromicSubsequence {
    //1) Bottom-up approach.
    public int longestPalindromeSubseq(String s) {
        return lengthOfLPS(s);
    }

    private int lengthOfLPS(String s) {
        //Longest palindromic susbsequence in a string is nothing
        //but longest common subsequence in `s` and reverse of `s`
        return lengthOfLCS(s, new StringBuilder(s).reverse().toString());
    }

    private int lengthOfLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i=1; i < m+1; i++) {
            for (int j=1; j < n+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];

                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[m][n];
    }


    //2) Top-down approach.
    public int longestPalindromeSubseq2(String s) {
        // For a string of length `n`:
        // No. of substrings = n*(n+1)/2 (substrings don't include empty string). Neither deletion/nor order change allowed.
        // No. of subsequences = 2^n (subsequences include empty string). Deletion of chars allowed, but can't change order.
        String s2 = new StringBuffer(s).reverse().toString();
        return lengthOfLPS2(s, s2, 0, 0, s.length(), s2.length(), new HashMap<String, Integer>());
    }

    private int lengthOfLPS2(String s1, String s2, int firstStringIndex, int secondStringIndex, int s1Length, int s2Length, Map<String, Integer> memo) {
        // Only empty string (length 0) is the possible ans.
        if (firstStringIndex >= s1Length || secondStringIndex >= s2Length) return 0;

        int ans = 0;
        String key = firstStringIndex + "-" + secondStringIndex;
        if (memo.containsKey(key)) return memo.get(key);

        if (s1.charAt(firstStringIndex) == s2.charAt(secondStringIndex)) {
            ans = 1 + lengthOfLPS2(s1, s2, firstStringIndex+1, secondStringIndex+1, s1Length, s2Length, memo);
        } else {
            int a = lengthOfLPS2(s1, s2, firstStringIndex, secondStringIndex+1, s1Length, s2Length, memo);
            int b = lengthOfLPS2(s1, s2, firstStringIndex+1, secondStringIndex, s1Length, s2Length, memo);
            ans = Math.max(a, b);
        }

        memo.put(key, ans);
        return memo.get(key);
    }
}
