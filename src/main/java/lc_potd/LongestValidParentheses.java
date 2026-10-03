package lc_potd;

//@link - https://leetcode.com/problems/longest-valid-parentheses/?
//@check - https://www.youtube.com/watch?v=uLP67Zh8bxk
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        return mikssol(s);
    }

    /**
     Coded by me based on mik's explanation.
     I was thinking about the same approach
     but missed it slightly.
     */
    private int mikssol(String s) {
        int n = s.length();

        //left to right scan
        int oc = 0, cc = 0, maxLen = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') oc += 1;
            else cc += 1;

            if (cc > oc) {
                oc = 0;
                cc = 0;
            } else if (cc == oc) {
                maxLen = Math.max(maxLen, 2*oc);
            }
        }

        //right to left scan
        oc = 0;
        cc = 0;
        for (int i=n-1; i>=0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') oc += 1;
            else cc += 1;

            if (oc > cc) {
                oc = 0;
                cc = 0;
            } else if (cc == oc) {
                maxLen = Math.max(maxLen, 2*oc);
            }
        }

        return maxLen;
    }
}
