package lc_potd;

//@link - https://leetcode.com/problems/valid-word/
public class ValidWord {
    public boolean isValid(String word) {
        return brute(word);
    }

    private boolean brute(String word) {
        int n = word.length();
        if (n < 3) return false;

        int vcount = 0;
        int ccount = 0;
        for (int i=0; i<n; i++) {
            char ch = word.toLowerCase().charAt(i);
            if (!Character.isLetterOrDigit(ch)) return false;
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') vcount += 1;
            else if (!Character.isDigit(ch)) ccount += 1;
        }
        return (vcount >= 1 && ccount >= 1);
    }
}
