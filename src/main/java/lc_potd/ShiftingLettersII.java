package lc_potd;

//@link - https://leetcode.com/problems/shifting-letters-ii/
//@check - https://www.youtube.com/watch?v=_lC3Wb5di2s&t=354s&ab_channel=codestorywithMIK
public class ShiftingLettersII {
    public String shiftingLetters(String s, int[][] shifts) {
        // return brute(s, shifts);
        // return pass2(s, shifts);
        return pass3(s, shifts);
    }

    //TLE
    private String brute(String s, int[][] shifts) {
        StringBuffer sb = new StringBuffer(s);

        for (int[] shift: shifts) {
            int start = shift[0];
            int end = shift[1];
            int dir = shift[2];

            for (int i=start; i<=end; i++) {
                char ch = sb.charAt(i);
                char repl;
                if (dir == 1) {
                    repl = (char)(((ch-96)%26) + 97); //(ch+1-97) -> (ch-96)
                } else {
                    repl = (char)((((ch-98)%26)+26)%26 + 97); //(ch-1-97) -> (ch-98)
                }
                sb.setCharAt(i, repl);
            }
        }

        return sb.toString();
    }

    //Slightly better than my brute force soln and passes more
    //test cases but still gives TLE.
    private String pass2(String s, int[][] shifts) {
        int n = s.length();
        int[] shfs = new int[n];
        StringBuffer sb = new StringBuffer(s);

        for (int[] shift: shifts) {
            int start = shift[0];
            int end = shift[1];
            int dir = shift[2];

            for (int i=start; i<=end; i++) {
                if (dir == 1) {
                    shfs[i] += 1;
                } else {
                    shfs[i] -= 1;
                }
            }
        }

        for (int i=0; i<n; i++) {
            char ch = sb.charAt(i);
            int shf = shfs[i];
            // if (shf < 0) {
            //     repl = (char)((((ch+shf-97)%26)+26)%26 + 97);
            // } else if (shf>0) {
            //     repl = (char)(((ch+shf-97)%26) + 97);
            // } else repl = ch;
            char repl = (char)((((ch+shf-97)%26)+26)%26 + 97);
            sb.setCharAt(i, repl);
        }

        return sb.toString();
    }

    //Based on Array difference technique taught by Mik.
    private String pass3(String s, int[][] shifts) {
        int n = s.length();
        int[] shfs = new int[n];
        StringBuffer sb = new StringBuffer(s);

        //the array difference technique.
        for (int[] shift: shifts) {
            int start = shift[0];
            int end = shift[1];
            int dir = shift[2];

            if (dir == 1) {
                shfs[start] += 1;
                if (end+1<n) shfs[end+1] -= 1;
            } else {
                shfs[start] -= 1;
                if (end+1<n) shfs[end+1] += 1;
            }
        }

        for (int i=1; i<n; i++) {
            shfs[i] += shfs[i-1];
        }

        for (int i=0; i<n; i++) {
            char ch = sb.charAt(i);
            int shf = shfs[i];
            char repl = (char)((((ch+shf-97)%26)+26)%26 + 97);
            sb.setCharAt(i, repl);
        }

        return sb.toString();
    }
}
