package algorithms;

import java.util.ArrayList;
import java.util.List;

/*
    Implementation of the KMP algorithm
    as per explanation by mik.
 */
//@check - https://www.youtube.com/watch?v=qases-9gOpk&t=3451s&ab_channel=codestorywithMIK
public class KMP {

    private int[] getLps(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        lps[0] = 0;

        int i = 1;
        int length = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length += 1;
                lps[i] = length;
                i += 1;
            } else if (length > 0) {
                length = lps[length - 1]; //try for the smaller length.
            } else {
                lps[i] = 0; //no prefix-suffix possible.
                i += 1;
            }
        }

        return lps;
    }

    public List<Integer> search(String pattern, String text) {
        int[] lps = getLps(pattern);

        int m = pattern.length();
        int n = text.length();
        int i = 0;
        int j = 0;

        List<Integer> matches = new ArrayList<>(); //all indexes in text where pattern matches.
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i += 1;
                j += 1;
            } else if (j > 0) {
                j = lps[j-1];
            } else {
                i += 1;
            }

            if (j == m) {
                matches.add(i-j);
                j = lps[j-1];
            }
        }

        return matches;
    }
}
