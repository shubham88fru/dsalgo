package lc_potd;

//@link - https://leetcode.com/problems/rotate-string/
public class RotateString {
    public boolean rotateString(String s, String goal) {
        // return brute(s, goal);
        return optimal(s, goal);
    }

    /*
        Trick: A string concatenated with itself, will
        contain all possible rotations of the string.
    */
    private boolean optimal(String s, String g) {
        int n1 = s.length(), n2 = g.length();

        if (n1 != n2) return false;

        return (s + s).contains(g);
    }

    private boolean brute(String s, String g) {
        int n1 = s.length(), n2 = g.length();

        if (n1 != n2) return false;

        char c1 = s.charAt(0);
        for (int i=0; i<n2; i++) {
            char c2 = g.charAt(i);
            if (c2 == c1 && check(s, g, i)) return true;
        }

        return false;
    }

    private boolean check(String s, String g, int i) {
        int n = s.length();

        for (int j=0; j<n; j++) {
            if (s.charAt(j) != g.charAt(i)) return false;
            i = (i+1)%n;
        }

        return true;
    }
}
