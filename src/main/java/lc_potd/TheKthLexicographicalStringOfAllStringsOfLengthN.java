package lc_potd;

//@link - https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
public class TheKthLexicographicalStringOfAllStringsOfLengthN {

    /*
    * My Soln. Mik had the same solution.
    * */
    public String getHappyString(int n, int k) {
        boolean[] found = {false};
        StringBuffer ans = new StringBuffer();
        pass1("abc", n, new int[] {k}, found, ans);
        return ans.toString();
    }

    private void pass1(String abc, int n, int[] k, boolean[] found, StringBuffer ans) {

        if (ans.length() == n) {
            k[0] -= 1;

            if (k[0] == 0) {
                found[0] = true;
            }
            return;
        }

        for (int i=0; i<3; i++) {
            if (ans.length() != 0 && abc.charAt(i) == ans.charAt(ans.length()-1)) continue;
            ans.append(abc.charAt(i));
            pass1(abc, n, k, found, ans);
            if (found[0]) return;
            ans.deleteCharAt(ans.length()-1);
        }
    }
}
