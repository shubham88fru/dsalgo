package lc_cntst;

//@link - https://leetcode.com/problems/find-the-count-of-numbers-which-are-not-special/description/
public class FindCountOfNumsWhichAreNotSpecial {
    public int nonSpecialCount(int l, int r) {
        int count = 0;
        int upper = (int)Math.sqrt(r);
        for (int i=1; i<=upper; i++) {
            int sq = i*i;
            if (isPrime(i) && sq>=l && sq <=r ) {
                count += 1;
            }

            if (sq > r) break;
        }


        return (r-l+1-count);
    }

    //efficient algorithm to check for primality.
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    private boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return (num == (sqrt * sqrt));
    }
}
