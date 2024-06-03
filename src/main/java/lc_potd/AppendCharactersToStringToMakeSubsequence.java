package lc_potd;

//@link - https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/
public class AppendCharactersToStringToMakeSubsequence {
    public int appendCharacters(String s, String t) {
        int p1 = 0;
        int p2 = 0;
        while (p1 < s.length() && p2 < t.length()) {
            if (t.charAt(p2) == s.charAt(p1)) {
                p2 += 1;
            }

            p1 += 1;
        }

        if (p2 == t.length()) return 0;
        return (t.length()-p2);
    }
}
