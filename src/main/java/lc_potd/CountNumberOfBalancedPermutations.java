package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/count-number-of-balanced-permutations/description/
//@check - https://www.youtube.com/watch?v=_9YlLoFT8JQ&t=4472s&ab_channel=codestorywithMIK
public class CountNumberOfBalancedPermutations {
    int n;
    int totalDigitSum;
    final int M = 1000000007;
    long totalPossiblePerms = 0l;

    public int countBalancedPermutations(String num) {
        return (int)mikssol(num);
    }

    /*
        Completely based on miks soln.
    */
    private long mikssol(String num) {
        n = num.length();
        totalDigitSum = 0;

        long[] freq = new long[10];
        for (int i=0; i<n; i++) {
            int dig = (num.charAt(i)-'0');
            totalDigitSum += dig;
            freq[dig] += 1;
        }

        if (totalDigitSum%2 != 0) return 0;

        long[] factorials = new long[n+1];
        Arrays.fill(factorials, 1l);
        factorials[0] = 1;
        factorials[1] = 1;

        for (int i = 2; i <= n; i++) {
            factorials[i] = (factorials[i-1] * i) % M;
        }

        //factorial of 1/x
        long[] fermatFact = new long[n+1];
        Arrays.fill(fermatFact, 1L);
        for (int i=0; i<= n; i++) {
            fermatFact[i] = findPower(factorials[i], M-2) % M;
        }

        totalPossiblePerms = (long)(factorials[(n+1)/2] * factorials[n/2]) % M;

        int digit = 0;
        int evenIndexDigitsCount = 0;
        long currSum = 0;

        Long[][][] dp = new Long[10][(n+1)/2 + 1][totalDigitSum+1];
        return solve(0, evenIndexDigitsCount, currSum, freq, fermatFact, dp);
    }

    private long solve(int dig, long evenIndexDigitsCount, long currSum, long[] freq, long[] fermatFact, Long[][][] dp) {
        if (dig == 10) {
            if (
                    currSum == totalDigitSum/2 &&
                            evenIndexDigitsCount == (n+1)/2
            ) {
                return (int)totalPossiblePerms;
            }
            return 0;
        }

        if (dp[dig][(int)evenIndexDigitsCount][(int)currSum] != null) return dp[dig][(int)evenIndexDigitsCount][(int)currSum];

        long ways = 0;
        for (int count = 0; count <= Math.min(freq[dig], (n+1)/2 - evenIndexDigitsCount); count+=1) {
            int evenPosCount = count;
            int oddPosCount = (int)(freq[dig] - count);

            long div = (fermatFact[evenPosCount] * fermatFact[oddPosCount])%M;

            long val = solve(dig+1, evenIndexDigitsCount+count, currSum + dig*count, freq, fermatFact, dp);

            ways = ((ways + val * div)%M) % M;
        }

        dp[dig][(int)evenIndexDigitsCount][(int)currSum] = ways;
        return ways;
    }

    private long findPower(long a, long b) {
        if (b==0) return 1;

        long half = findPower(a, b/2);
        long result = (half * half) % M;

        if (b%2 == 1) result = (result*a) % M;

        return result;
    }
}
