package lc_potd;

//@link - https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/description/
public class FindTheKthCharacterInStringGameI {
    public char kthCharacter(int k) {
        return brute(k);
    }

    /*
        Simulation based brute force soln.
        There's a cheap bit manip soln as well.
    */
    private char brute(int k) {
        StringBuilder s = new StringBuilder("a");

        while (s.length() < k) {
            int n = s.length();
            for (int i=0; i<n; i++) {
                char next = (char)((((s.charAt(i) - 'a') + 1)%26) + 'a');
                s.append(next);
            }
        }

        return s.charAt(k-1);
    }
}
