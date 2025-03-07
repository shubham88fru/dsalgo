package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/closest-prime-numbers-in-range/
//@check - https://www.youtube.com/watch?v=9tUv0SfUyGg&t=1015s&ab_channel=codestorywithMIK
public class ClosestPrimeNumbersInRange {
    public int[] closestPrimes(int left, int right) {
        return mikssol(left, right);
    }

    private int[] mikssol(int left, int right) {
        return pass1(left, right);
    }

    private int[] pass1(int left, int right) {
        boolean[] isPrime = sieveHelper(right);
        List<Integer> primes = new ArrayList<>();

        for (int num=left; num<=right; num++) {
            if (isPrime[num] == true) {
                primes.add(num);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        int[] ans = {-1, -1};
        for (int i=1; i<primes.size(); i++) {
            int diff = primes.get(i) - primes.get(i-1);
            if (diff < minDiff) {
                minDiff = diff;
                ans[0] = primes.get(i-1);
                ans[1] = primes.get(i);
            }
        }

        return ans;
    }

    /*
        Sieve algorithm to check if a number is prime.
        In this case, the algorithm returns an array
        indicating whether each number from left to right
        are prime or not.
    */
    private boolean[] sieveHelper(int right) {
        boolean[] isPrime = new boolean[right+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i=2; i*i<=right; i++) {
            if (isPrime[i] == true) {
                for (int j=2; i*j<=right; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        return isPrime;
    }
}
