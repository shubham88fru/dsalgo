package lc_potd;

//@link - https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
//@check - https://www.youtube.com/watch?v=zmc_FoPw_WQ
public class ConcatenateNonZeroDigitsAndMultiplyBySumII {
    public int[] sumAndMultiply(String s, int[][] queries) {
        return mikssol(s, queries);
    }

    //Easy to understand, by completely based on
    //mik's approach.
    private int[] mikssol(String s, int[][] queries) {
        int mod = 1000000007;
        int n = s.length();

        int[] nonZeroCntUpto = new int[n];
        long[] nonZeroNumUpto = new long[n];
        long[] nonZeroSumUpto = new long[n];
        long[] pow10Upto = new long[n+1];

        nonZeroCntUpto[0] = s.charAt(0) == '0' ? 0: 1;
        for (int i=1; i<n; i++) {
            char dig = s.charAt(i);
            nonZeroCntUpto[i] = nonZeroCntUpto[i-1] + (dig == '0' ? 0: 1);
        }

        nonZeroNumUpto[0] = (long)(s.charAt(0) - '0');
        for (int i=1; i<n; i++) {
            int dig = s.charAt(i) - '0';
            if (dig == 0) {
                nonZeroNumUpto[i] = nonZeroNumUpto[i-1];
            } else {
                nonZeroNumUpto[i] = (nonZeroNumUpto[i-1]*10 + dig)%mod;
            }
        }

        nonZeroSumUpto[0] = (long)(s.charAt(0) - '0');
        for (int i=1; i<n; i++) {
            int dig = s.charAt(i) - '0';
            nonZeroSumUpto[i] = nonZeroSumUpto[i-1] + dig;
        }

        pow10Upto[0] = 1;
        for (int i=1; i<=n; i++) {
            pow10Upto[i] = (pow10Upto[i-1]*10)%mod;
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int i=0; i<m; i++) {
            int l = queries[i][0], r = queries[i][1];

            //sum
            long sum = nonZeroSumUpto[r] - (l == 0 ? 0: nonZeroSumUpto[l-1]);

            //x
            long numBefore = (l == 0 ? 0: nonZeroNumUpto[l-1]);
            int k = nonZeroCntUpto[r] - (l==0?0: nonZeroCntUpto[l-1]);
            long x = (nonZeroNumUpto[r] - (numBefore*pow10Upto[k])%mod + mod)%mod;

            ans[i] = (int)((x*sum)%mod);
        }

        return ans;
    }
}
