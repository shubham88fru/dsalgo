package lc_cntst;

//@link - https://leetcode.com/problems/maximum-multiplication-score/description/
public class MaximumMultiplicationScore {
    public long maxScore(int[] a, int[] b) {
        Long[][] memo = new Long[5][b.length+1];
        return dp(a, b,
                0,
                0,
                memo
        );
    }

    private long dp(int[] a, int[] b, int picked, int curr, Long[][] memo) {
        if (curr >= b.length) {
            // returning  Integer.MIN_VALUE would edges cases like -
            //a = [100000,100000,100000,100000], b=[-100000,-100000,-100000,-100000]
            //where expected answer would be - -40000000000
            if (picked < 4) return (long)-99999999999L;
            return 0;
        }

        if (memo[picked][curr] != null) return memo[picked][curr];

        long pick = Long.MIN_VALUE;
        if (picked < 4) {
            pick = ((long)a[picked]*(long)b[curr]) + dp(
                    a, b,
                    picked+1,
                    curr+1,
                    memo
            );
        }

        long notPick = dp(a, b, picked, curr+1, memo);

        memo[picked][curr] = Math.max(pick, notPick);
        return memo[picked][curr];
    }
}
