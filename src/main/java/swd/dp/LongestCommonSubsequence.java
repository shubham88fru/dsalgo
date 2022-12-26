package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String s1, String s2) {
        // For a string of length `n`:
        // No. of substrings = n*(n+1)/2 (substrings don't include empty string). Neither deletion/nor order change allowed.
        // No. of subsequences = 2^n (subsequences include empty string). Deletion of chars allowed, but can't change order.

        return lengthOfLCS(s1, s2, 0, 0, s1.length(), s2.length(), new HashMap<String, Integer>());
    }

    private int lengthOfLCS(String s1, String s2, int firstStringIndex, int secondStringIndex, int s1Length, int s2Length, Map<String, Integer> memo) {
        // Only empty string (length 0) is the possible ans.
        if (firstStringIndex >= s1Length || secondStringIndex >= s2Length) return 0;

        int ans = 0;
        String key = firstStringIndex + "-" + secondStringIndex;
        if (memo.containsKey(key)) return memo.get(key);

        if (s1.charAt(firstStringIndex) == s2.charAt(secondStringIndex)) {
            ans = 1 + lengthOfLCS(s1, s2, firstStringIndex+1, secondStringIndex+1, s1Length, s2Length, memo);
        } else {
            int a = lengthOfLCS(s1, s2, firstStringIndex, secondStringIndex+1, s1Length, s2Length, memo);
            int b = lengthOfLCS(s1, s2, firstStringIndex+1, secondStringIndex, s1Length, s2Length, memo);
            ans = Math.max(a, b);
        }

        memo.put(key, ans);
        return memo.get(key);
    }
}
