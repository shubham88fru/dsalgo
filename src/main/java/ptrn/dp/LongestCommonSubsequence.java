package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-common-subsequence/
//@strvr - https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5474901982707712
public class LongestCommonSubsequence {
    //1) Bottom-up approach.
    public int longestCommonSubsequence(String s1, String s2) {
        return lengthOfLCS(s1, s2);
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




    //2) Top-down approach. (SWD and Edctv soln)
    public int longestCommonSubsequence2(String s1, String s2) {
        // For a string of length `n`:
        // No. of substrings = n*(n+1)/2 (substrings don't include empty string). Neither deletion/nor order change allowed.
        // No. of subsequences = 2^n (subsequences include empty string). Deletion of chars allowed, but can't change order.

        return lengthOfLCS2(s1, s2, 0, 0, s1.length(), s2.length(), new HashMap<String, Integer>());
    }

    private int lengthOfLCS2(String s1, String s2, int firstStringIndex, int secondStringIndex, int s1Length, int s2Length, Map<String, Integer> memo) {
        // Only empty string (length 0) is the possible ans.
        if (firstStringIndex >= s1Length || secondStringIndex >= s2Length) return 0;

        int ans = 0;
        String key = firstStringIndex + "-" + secondStringIndex;
        if (memo.containsKey(key)) return memo.get(key);

        //Not matter what, the positions where firstString and secondString
        //are same, will certainly be part of the longest common subsequence.
        if (s1.charAt(firstStringIndex) == s2.charAt(secondStringIndex)) {
            ans = 1 + lengthOfLCS2(s1, s2, firstStringIndex+1, secondStringIndex+1, s1Length, s2Length, memo);
        } else {
            //When the characters in the strings are diff, try exploring the possibilities by moving
            //to next character in each string and chose the max.
            int a = lengthOfLCS2(s1, s2, firstStringIndex, secondStringIndex+1, s1Length, s2Length, memo);
            int b = lengthOfLCS2(s1, s2, firstStringIndex+1, secondStringIndex, s1Length, s2Length, memo);
            ans = Math.max(a, b);
        }

        memo.put(key, ans);
        return memo.get(key);
    }
}
