package lc_potd;

//@link - https://leetcode.com/problems/total-characters-in-string-after-transformations-i/description/
//@check - https://www.youtube.com/watch?v=reX7--RYbzw&ab_channel=codestorywithMIK
public class TotalCharactersInStringAfterTransformationsI {
    public int lengthAfterTransformations(String s, int t) {
        return pass2(s, t);
    }

    /*
        1) Using hashmap.
        Coded by me, but took hint from mik. I was thinking on
        the lines of a Math soln, still think that
        is the most optimal soln and this aint.
    */
    private int pass2(String s, int t) {
        int[] freq = new int[26];
        int n = s.length();

        for (int i=0; i<n; i++) {
            freq[s.charAt(i)-'a'] += 1;
        }

        while (t > 0) {
            //note we can't update in the same freq
            //arr over which we are iterating.
            int[] next = new int[26];
            for (int i=0; i<26; i++) {
                if (freq[i] != 0) {

                    if (i != 25) {
                        next[i+1] = (next[i+1] + freq[i])%1000000007;
                    } else {
                        next[0] = (next[0] + freq[i])%1000000007;
                        next[1] = (next[1] + freq[i])%1000000007;
                    }

                    freq[i] = 0;
                }
            }

            freq = next;
            t -= 1;
        }

        int len = 0;
        for (int i=0; i<26; i++) {
            len = (len + freq[i])%1000000007;
        }

        return len;
    }

    //2) Brute force
    //keep generating strings by replacement.
}
