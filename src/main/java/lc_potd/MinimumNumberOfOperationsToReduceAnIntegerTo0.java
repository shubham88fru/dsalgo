package lc_potd;

//@link - https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0/
//@check - https://www.youtube.com/watch?v=EQwDt88doaM
public class MinimumNumberOfOperationsToReduceAnIntegerTo0 {
    public int minOperations(int n) {
        return hintedSol(n);
    }

    /**
     I had a slight idea but couldn't
     come up with a soln by myself.
     Following is a soln based on
     some hints.
     */
    private int hintedSol(int n) {

        // return minMovesToMakeZero(n);
        return minMovesToMakeZeroMemoized(n, new Integer[n+1]);
    }

    private int minMovesToMakeZeroMemoized(int n, Integer[] memo) {
        /*
            If reached a number that is already a power of
            two, we just need one operation.
        */
        if (n == (int)Math.pow(2.0, log2(n))) return 1;

        if (memo[n] != null) return memo[n];

        int pow2Before = (int)Math.pow(2.0, log2(n)); //closest power of two lower.
        int pow2After = (int)Math.pow(2.0, log2(n)+1); //closest power of two higher.

        /*
            From a given `n`, to reach `pow2Before`, we need
            to subtract `n-pow2Before` from `n`, therefore we find
            how many min moves are needed to make `n-pow2Before` 0.
            And likewise for to reach `pow2After`, we need to add
            `pow2After-n` to `n` and therefore find how many min
            moves are needed to make `pow2After-n` 0. Finally, we
            choose whichever is minimum of the two because that's
            what the question asks for.
        */
        memo[n] = 1 + Math.min(minMovesToMakeZeroMemoized(n-pow2Before, memo),
                minMovesToMakeZeroMemoized(pow2After-n, memo));

        return memo[n];
    }

    /*
        Idea is that given a number, get the
        minimum number of moves needed to go
        to a closest power of two. This is so
        because, reducing a power to two to
        0 only takes one operation.
    */
    private int minMovesToMakeZero(int n) {
        /*
            If reached a number that is already a power of
            two, we just need one operation.
        */
        if (n == (int)Math.pow(2.0, log2(n))) return 1;

        int pow2Before = (int)Math.pow(2.0, log2(n)); //closest power of two lower.
        int pow2After = (int)Math.pow(2.0, log2(n)+1); //closest power of two higher.

        /*
            From a given `n`, to reach `pow2Before`, we need
            to subtract `n-pow2Before` from `n`, therefore we find
            how many min moves are needed to make `n-pow2Before` 0.
            And likewise for to reach `pow2After`, we need to add
            `pow2After-n` to `n` and therefore find how many min
            moves are needed to make `pow2After-n` 0. Finally, we
            choose whichever is minimum of the two because that's
            what the question asks for.
        */
        return 1 + Math.min(minMovesToMakeZero(n-pow2Before),
                minMovesToMakeZero(pow2After-n));
    }

    private int log2(int n) {
        return (int)(Math.log(n)/Math.log(2));
    }
}
