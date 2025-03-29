package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/apply-operations-to-maximize-score/description/
//@check - https://www.youtube.com/watch?v=DX4zng-0pLE&ab_channel=codestorywithMIK
public class ApplyOperationsToMaximizeScore {
    private final static int MOD = 1000000007;

    public int maximumScore(List<Integer> nums, int k) {
        return mikssol(nums, k);
    }

    /*
    * Extremely long problem. Not super difficult, but only
    * if you exactly know what to do. Completely based
    * on mik's explanation.
    * */
    private int mikssol(List<Integer> nums, int k) {
        int[] primeScores = findPrimeScores(nums);
        int[] nextGreater = findNextGreater(primeScores); //next greater is the breakpoint.
        int[] prevGreaterOrEqual = findPrevGreaterOrEqual(primeScores); //prev greater or equal is the breakpoint.

        int n = nums.size();
        long[] subarrays = new long[n];

        for (int i=0; i<n; i++) {
            subarrays[i] = (long)(nextGreater[i] - i) * (long)(i - prevGreaterOrEqual[i]);
        }

        int[][] sortedNums = new int[n][2];
        for (int i=0; i<n; i++) {
            sortedNums[i] = new int[] {nums.get(i), i};
        }
        Arrays.sort(sortedNums, (a1, a2) -> a2[0] - a1[0]);

        long score = 1;
        int idx = 0;
        while (k > 0) {
            int[] pair = sortedNums[idx];
            long ops = Math.min((long)k, subarrays[pair[1]]);
            score = (score * findPower(pair[0], ops))%MOD;
            k -= ops;
            idx += 1;
        }

        return (int)score;
    }

    private int[] findPrimeScores(List<Integer> nums) {
        int n = nums.size();
        int[] primeScores = new int[nums.size()];
        int max = Integer.MIN_VALUE;
        for (int num: nums) {
            max = Math.max(num, max);
        }

        List<Integer> primes = getPrimes(max);

        for (int i=0; i<n; i++) {
            int num = nums.get(i);

            for (int prime: primes) {
                if (prime*prime > num) break;
                if (num%prime !=0) continue;

                primeScores[i] += 1;
                while (num%prime == 0) {
                    num /= prime;
                }
            }

            if (num > 1) {
                primeScores[i] += 1;
            }
        }

        return primeScores;
    }

    private int[] findNextGreater(int[] primeScores) {
        int n = primeScores.length;
        int[] nextGreater = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n-1; i >= 0; i--) {
            if (stack.isEmpty()) {
                nextGreater[i] = n;
            } else {
                while (!stack.isEmpty() && primeScores[i] >= primeScores[stack.peekFirst()]) {
                    stack.removeFirst();
                }

                if (stack.isEmpty()) {
                    nextGreater[i] = n;
                } else {
                    nextGreater[i] = stack.peekFirst();
                }
            }

            stack.addFirst(i);
        }

        return nextGreater;
    }

    private int[] findPrevGreaterOrEqual(int[] primeScores) {
        int n = primeScores.length;
        int[] prevGreaterOrEqual = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                prevGreaterOrEqual[i] = -1;
            } else {
                while (!stack.isEmpty() && primeScores[i] > primeScores[stack.peekFirst()]) {
                    stack.removeFirst();
                }

                if (stack.isEmpty()) {
                    prevGreaterOrEqual[i] = -1;
                } else {
                    prevGreaterOrEqual[i] = stack.peekFirst();
                }
            }

            stack.addFirst(i);
        }

        return prevGreaterOrEqual;
    }

    //sieve algorithm to find primes.
    private List<Integer> getPrimes(int limit) {
        boolean[] isPrime = new boolean[limit+1];
        Arrays.fill(isPrime, true);

        List<Integer> primes = new ArrayList<>();

        for (int i=2; i*i <= limit; i++) {
            if (isPrime[i]) {
                for (int j=i*i; j<=limit; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i=2; i<=limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    //find power using binary exponentiation.
    private long findPower(long a, long b) {
        if (b == 0) return 1l;
        long half = findPower(a, b/2);
        long result = (half * half) % MOD;
        if (b%2 == 1) {
            result = (result * a) % MOD;
        }

        return result;
    }
}
