package lc_potd;

//@link - https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/
public class MinimumDeletionsToMakeStringKSpecial {
    public int minimumDeletions(String word, int k) {
        return brute(word, k);
    }

    /*
        Based on mik's explanation.
        Code is simple to understand.
        Idea is that for each char of the
        string, we'll try to fix the remaining
        chars by deleting appropriate count so
        that the remaining chars are valid as per
        curr char. And then, we'll select the
        one that leads to min deletions.
        While this guarantees that wrt to curr char
        all the remaining chars will be in the permissible
        limits, however, I don't understand how this guarantees
        that the remaining chars are within permissible limits
        of each other.
    */
    private int brute(String word, int k) {
        int n = word.length();

        int[] freq = new int[26];
        int res = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            freq[word.charAt(i)-'a'] += 1;
        }

        //For each char, every other
        //char's freq should at max be
        //freq[char] + k
        for (int i=0; i<26; i++) {
            int dels = 0;
            if (freq[i] == 0) continue;
            for (int j=0; j<26; j++) {
                if (i==j) continue;
                if (freq[j] < freq[i]) {
                    dels += freq[j];
                } else if (Math.abs(freq[i]-freq[j]) > k) {
                    dels += (Math.abs(freq[i]-freq[j]) - k);
                }
            }
            res = Math.min(res, dels);
        }

        return res;
    }
}
