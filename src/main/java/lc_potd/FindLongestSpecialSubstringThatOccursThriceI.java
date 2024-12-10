package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/description/
//@check - https://www.youtube.com/watch?v=fDUPCtPqzss&ab_channel=codestorywithMIK
public class FindLongestSpecialSubstringThatOccursThriceI {
    public int maximumLength(String s) {
        return slidingwindow(s);
    }

    /*
        Following is my sliding window approach.
        Mik showed a different approach without using sliding window.
        My soln might not be the most optimized for large test cases,
        so @check mik's approach if this is a recurring problem for
        some company.
    */
    private int slidingwindow(String s) {
        Map<String, Integer> freq = new HashMap<>();
        int maxLen = -1;

        int n = s.length();
        int l = 0;
        int r = 0;

        while (r < n) {
            char chl = s.charAt(l);
            char chr = s.charAt(r);
            if ((chl == chr)) {
                r += 1;
            } else {
                String sub = s.substring(l, r);
                int len = sub.length();
                int count = len;
                char ch = sub.charAt(0);
                //For a string of length l (with all same chars),
                //each such string will contribute a frequency to each
                //smaller length string.
                //e.g. "aaaa" contributes to +1 for length 4, +2 for length 3, +3 for length 2 and so on.
                for (int i=1; i<=len; i++) {
                    String str = ch + String.valueOf(i);
                    freq.put(str, freq.getOrDefault(str, 0)+count); //{a1: 1, a3: 2, b1: 5, ...}
                    count -= 1;
                    if (freq.get(str) >= 3) maxLen = Math.max(maxLen, i);
                }
                l = r;
            }
        }

        //The usual sliding window edge case
        //that I face :(
        String sub = s.substring(l, r);
        int len = sub.length();
        int count = len;
        char ch = sub.charAt(0);
        for (int i=1; i<=len; i++) {
            String str = ch + String.valueOf(i);
            freq.put(str, freq.getOrDefault(str, 0)+count);
            count -= 1;
            if (freq.get(str) >= 3) maxLen = Math.max(maxLen, i);
        }

        return maxLen;
    }
}
