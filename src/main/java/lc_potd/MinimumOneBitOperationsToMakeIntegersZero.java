package lc_potd;

//@link - https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/?
//@check - https://www.youtube.com/watch?v=yU6DZSLW4_c
public class MinimumOneBitOperationsToMakeIntegersZero {
    public int minimumOneBitOperations(int n) {
        return mikssol(n);
    }

    /**
     There is no way I could have
     come up with this approach entirely
     on my own. This is a tricky and observation
     based problem. Re-watch mik's explanation
     when solving this again.

     Editorial has multiple approaches for this.

     Following is coded by me entirely based on
     mik's explanation.
     */
    private int mikssol(int n) {
        long[] F = new long[32];
        F[0] = 1;

        for (int i=1; i<32; i++) F[i] = 2*F[i-1] + 1;

        int i = 31;
        int sign = 1;
        int ans = 0;
        while (i >= 0) {
            if ((n&(1<<i)) != 0) {
                long val = (i == 0) ? 1: F[i];
                ans += (sign*val);
                sign *= -1;
            }
            i -= 1;
        }

        return ans;
    }
}
