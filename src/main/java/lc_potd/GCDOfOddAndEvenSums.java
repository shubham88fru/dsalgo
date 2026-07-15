package lc_potd;

//@link - https://leetcode.com/problems/gcd-of-odd-and-even-sums/?
public class GCDOfOddAndEvenSums {
    public int gcdOfOddEvenSums(int n) {
        return pass1(n);
    }

    private int pass1(int n) {
        int os = n*n; //sum of first odd numbers.
        int es = n*(n+1); //sum of first even numbers.

        return gcd(os, es);
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
