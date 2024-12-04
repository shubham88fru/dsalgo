package lc_potd;

//@link - https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/
public class MakeStringASubsequenceUsingCyclicIncrements {
    public boolean canMakeSubsequence(String str1, String str2) {
        return mysol(str1, str2);
    }

    private boolean mysol(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();

        int i = 0;
        int j = 0;
        while (j < l2 && i < l1) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(j);
            if (ch2 == ch1) {
                i += 1;
                j += 1;
            } else if (ch2 == getCyclicNext(ch1)) {
                i += 1;
                j += 1;
            } else {
                i += 1;
            }
        }

        return j == l2;
    }

    private char getCyclicNext(char ch) {
        return (char) ((((ch + 1) - 97) % 26) + 97);
    }
}
