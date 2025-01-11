package lc_potd;

//@link - https://leetcode.com/problems/construct-k-palindrome-strings/
public class ConstructKPalindromeStrings {
    public boolean canConstruct(String s, int k) {
        return pass1(s, k);
    }

    /*
        My soln. Mik had the same solution.
     */
    private boolean pass1(String s, int k) {
        int n = s.length();

        if (n < k) return false; //not possible.
        if (k == n) return true; //all individual chars.

        int[] freq = new int[26];
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            freq[ch-'a'] += 1;
        }

        int oddCnt = 0;
        for (int i=0; i<26; i++) {
            if (freq[i]%2 != 0) oddCnt += 1;
            if (oddCnt > k) return false; //odds can either exist alone or with evens only.
        }

        return (n >= k);
    }
}
