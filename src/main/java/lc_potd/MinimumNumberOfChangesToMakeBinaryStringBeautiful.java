package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/
//@check - https://www.youtube.com/watch?v=jvRiCOEflXA&t=1176s
public class MinimumNumberOfChangesToMakeBinaryStringBeautiful {
    public int minChanges(String s) {
        return mikssol(s);
    }

    /*
        Following is Mik's soln. Explanation makes
        sense but I'm not sure how this approach guarantees
        that the flips will be minimum.
    */
    private int mikssol(String s) {
        int i = 0;
        int flips = 0;

        while (i < s.length()) {
            int j = i+1;
            char prev = s.charAt(i);
            while ((j < s.length()) && (s.charAt(j) == prev)) {
                j += 1;
            }

            int sameLen = j-i; //length of string with same character.
            if ((sameLen%2) != 0) {
                flips += 1;
                j += 1;
            }
            i = j;

        }

        return flips;
    }
}
