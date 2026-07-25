package lc_potd;

//@link - https://leetcode.com/problems/maximum-product-of-two-digits/
public class MaxProductOfTwoDigits {
    public int maxProduct(int n) {
        return brute(n);
    }

    private int brute(int n) {
        int fm = -1, sm = -1;
        while (n > 0) {
            int dig = n%10;
            if (dig >= fm) {
                sm = fm;
                fm = dig;
            } else if (dig > sm) {
                sm = dig;
            }

            n /= 10;
        }

        return fm*sm;
    }
}
