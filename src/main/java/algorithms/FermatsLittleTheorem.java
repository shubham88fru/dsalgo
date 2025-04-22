package algorithms;

/**
 *  <strong>About</strong> - used to calculate modular nCr i.e.
 *  You have the value of nCr and need nCr%<some_prime_no>
 *
 *  Calculating mod of an integer with another prime number is
 *  typical. However, this theorem is useful when we have a fraction
 *  e.g (3/7, 1/2, etc.) and we need to mod it with some prime number.
 *  Since nCr can be a fraction, this theorem is pretty useful when
 *  nCr is large and it needs to be modded with some prime.
 *
 *  (3/2)%7 --> 3*(1/2)%7 --> (3 * 2^-1) % 7 --> 3 * (modular inverse of 2 % 7)
 *  where modular inverse of m % n is a value P such that (m * P) % n = 1
 *  Note that a brute force approach to calculate P is to keep substituting
 *  P from 1 onwards and checking if the (m*P)%n = 1 satisfies.
 *  In the above eg, (2*4)%7 = 1, therefore modular inverse of 2 % 7 is 4.
 *
 *  Fermat's little theorem basically provides a simpler way of estimating
 *  the modular inverse.
 *  <p>
 *      Per fermat's theorem - modular inverse of a % M
 *      is equal to (a^M-2)%M
 *  </p>
 *
 */
//@check - https://www.youtube.com/watch?v=FMBW7m1Wap0&t=1024s&ab_channel=codestorywithMIK
public class FermatsLittleTheorem {

    public static long modularNcR(int n, int r, long m) {
        if (r < 0 || r > n) return 0;

        long a = factorial(n);
        long b = (factorial(r) * factorial(n - r)) % m;

        return (a * BinaryExponentiation.beRecursive(b, m-2))%m;
    }

    private static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {

    }
}
