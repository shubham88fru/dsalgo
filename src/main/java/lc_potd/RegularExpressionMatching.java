package lc_potd;

//@link - https://leetcode.com/problems/regular-expression-matching/description/
//@check - https://www.youtube.com/watch?v=3vbBrl-LeDc&t=2463s&ab_channel=codestorywithMIK
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        // return pass1(s, p);
        return pass2(s, p);
    }

    private boolean pass2(String s, String p) {
        return recursion(s, p);
    }

    /*
        Coded by me, based on mik's explanation.
        There's a slightly better version of this code -
        instead of taking the substrings, use pointers.
    */
    private boolean recursion(String s, String p) {
        if (p.length() == 0) return s.length() == 0;

        boolean match =(s.length() != 0 && ((s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.')));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            boolean takeAsterix = match && recursion(s.substring(1), p);
            boolean dontTakeAsterix = recursion(s, p.substring(2));

            return (takeAsterix || dontTakeAsterix);
        } else {
            return match && recursion(s.substring(1), p.substring(1));
        }


    }

    /*
        My initial intuition but doesn't work for all
        tc.
     */
    private boolean pass1(String s, String p) {
        int m = s.length();
        int n = p.length();

        return dp(s, p, m, n, 0, 0, -1);
    }

    private boolean dp(String s, String p, int m, int n, int i, int j, int prev) {
        if (j >= n && i < m || i >= m && j < n) return false;
        if (j >=n ) return true;


        char reg = p.charAt(j);
        char ch = s.charAt(i);


        if (reg == '*' && p.charAt(j-1) == '.') return dp(s, p, m, n, m, j+1, prev);
        else if (reg == '*') {
            while (i < m && i < j && s.charAt(i) == p.charAt(j-1)) {
                i += 1;
            }
            return dp(s, p, m, n, i, j+1, i);
        } else if (j < n-1 && p.charAt(j+1) == '*') {
            return dp(s, p, m, n, i, j+1, i);
        } else if (reg != '.' && reg != ch) return false;

        return dp(s, p, m, n, i+1, j+1, i);
    }
}
