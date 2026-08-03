package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/stone-game-iii/
public class StoneGameIII {
    public String stoneGameIII(int[] stoneValue) {
        return revise(stoneValue);
    }

    /**
        This is my soln based on -
         @see {@link lc_potd.StoneGame}
         @see {@link lc_potd.PredictTheWinner}
         @see {@link lc_potd.GridGame}
    */
    private String revise(int[] piles) {
        int n = piles.length;

        Integer[] dp = new Integer[n+1];
        int ts = Arrays.stream(piles).sum();
        int aliceScore = gametheory(piles, n, dp, 0);
        int bobScore = ts - aliceScore;
        if (aliceScore > bobScore) return "Alice";
        else if (aliceScore == bobScore) return "Tie";

        return "Bob";
    }

    private int gametheory(int[] piles, int n, Integer[] dp, int i) {
        if (i >= n) return 0;

        if (dp[i] != null) return dp[i];

        int two = -999999, three = -999999;

        int one = piles[i] + Math.min(
                Math.min(
                        gametheory(piles, n, dp, i+2),
                        gametheory(piles, n, dp, i+3)),
                gametheory(piles, n, dp, i+4)
        );

        if (i < n-1) two = piles[i] + piles[i+1] + Math.min(
                Math.min(
                        gametheory(piles, n, dp, i+3),
                        gametheory(piles, n, dp, i+4)),
                gametheory(piles, n, dp, i+5)
        );

        if (i < n-2) three = piles[i] + piles[i+1] + piles[i+2] + Math.min(
                Math.min(
                        gametheory(piles, n, dp, i+4),
                        gametheory(piles, n, dp, i+5)),
                gametheory(piles, n, dp, i+6)
        );

        dp[i] = Math.max(Math.max(one, two), three);
        return dp[i];
    }
}
