package lc_potd;

//@link - https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i/description/
public class ConcatenateNonZeroDigitsAndMultiplyBySumI {
    public long sumAndMultiply(int n) {
        return pass1(n);
    }

    private long pass1(long n) {
        long x = 0;
        long sum = 0;
        while (n > 0) {
            int dig = (int)n%10;
            if (dig > 0) {
                x = 10*x + dig;
                sum += dig;
            }
            n /= 10;
        }

        n = x;
        x = 0;
        while (n > 0) {
            int dig = (int)n%10;
            x = 10*x + dig;
            n /= 10;
        }

        return x*sum;
    }
}
