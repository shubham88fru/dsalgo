package lc_potd;

//@link - https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/description/
//@check - https://www.youtube.com/watch?v=aSZi159QnvU&t=334s&ab_channel=codestorywithMIK
public class DivisibleAndNonDivisibleSumsDifference {
    public int differenceOfSums(int n, int m) {
        // return pass1(n, m);
        return pass2(n, m);
    }

    //O(1) - optimal soln based on mik's explanation.
    private int pass2(int n, int m) {
        int totalSum = n * (n+1)/2;
        int totalDivisibleByM = n/m;
        int sum2 = m * (totalDivisibleByM*(totalDivisibleByM+1))/2;
        int sum1 = totalSum - sum2;
        return sum1 - sum2;
    }

    //O(n)
    private int pass1(int n, int m) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i=1; i<=n; i++) {
            if (i%m == 0) sum2 += i;
            else sum1 += i;
        }

        return sum1-sum2;
    }
}
