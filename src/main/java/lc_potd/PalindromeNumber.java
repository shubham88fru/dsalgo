package lc_potd;

//@link - https://leetcode.com/problems/palindrome-number/description/
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // return brute(x);
        return optimal(x);
    }

    private boolean optimal(int x) {
        if (x < 0) return false;
        if (x == 0) return true;

        int cpy = x, rev = 0;
        while (cpy > 0) {
            int d = cpy%10;
            rev = rev*10 + d;
            cpy /= 10;
        }

        return x == rev;
    }

    private boolean brute(int x) {
        if (x<0) return false;
        if (x==0) return true;
        String str = Integer.valueOf(x).toString();
        StringBuilder sbld = new StringBuilder(str);
        return str.equals(sbld.reverse().toString());
    }
}
