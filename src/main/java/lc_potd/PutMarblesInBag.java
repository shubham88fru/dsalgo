package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/put-marbles-in-bags/description/
//@check - https://www.youtube.com/watch?v=L-KWU8W3OqE&ab_channel=codestorywithMIK
public class PutMarblesInBag {
    public long putMarbles(int[] weights, int k) {
        return mikssol(weights, k);
    }

    /*
        Coded by me but completely based on mik's explanation.

        Its a fairly easy problem, ONLY IF you've solved it before.
        There's no way I can come up with this greedy soln directly
        in an interview without having solved it first.

        revise and memorize is the only way out here, ig.
     */
    private long mikssol(int[] weights, int k) {
        int n = weights.length;

        long[] consecutivePairSum = new long[n-1];
        for (int i=0; i<n-1; i++) {
            consecutivePairSum[i] = (long)weights[i] + (long)weights[i+1];
        }

        Arrays.sort(consecutivePairSum);

        int pairs = k - 1;
        int i = 0;
        int j = n-2;
        long minSum = 0l;
        long maxSum = 0l;

        while (pairs > 0) {
            minSum += consecutivePairSum[i];
            maxSum += consecutivePairSum[j];

            i += 1;
            j -= 1;
            pairs -= 1;
        }

        return maxSum - minSum;
    }
}
