package lc_potd;

//@link - https://leetcode.com/problems/vowels-game-in-a-string/description/
public class VowelGameInAString {
    public boolean doesAliceWin(String s) {
        return pass1(s);
    }

    /**
     My soln.
     The only way poor bob can win is in the
     very first attempt when alice can delete
     any substring, i.e. when there's no vowel
     int the og string at all.
    */
    private boolean pass1(String s) {
        int n = s.length();
        int vc = 0;

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') vc += 1;
        }

        return vc != 0;
    }
}
