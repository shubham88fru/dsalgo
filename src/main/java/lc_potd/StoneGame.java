package lc_potd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/stone-game/
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        return game(piles, 0, piles.length-1, 0, 0, 0, new HashMap<>());
    }

    //My soln.
    private boolean game(int[] piles, int p1, int p2, int s1, int s2, int player, Map<String, Boolean> memo) {
        if (p1 > p2) {
            return s1 > s2;
        }

        /**
         Since s1 and s2 are both changing, my obvious first intuition
         was that key will consist of s1 and s2 also. However, with that,
         using Map for memo was giving a TLE. Then I thought of using array
         for memo, but with that, the size of array was getting large because s1 and s2
         were dimensions too.
         Surprisingly, one solution just ignored s1 and s2 in the key and the soln still worked.
         */
        String key = p1 + "_" + p2 + "_" + player;
        if (memo.containsKey(key)) return memo.get(key);

        boolean pickP1 = game(piles, p1+1, p2, (player == 0? s1 + piles[p1]: s1), (player == 1? s2 + piles[p1]: s2), 1-player, memo);
        boolean pickP2 = game(piles, p1, p2-1, (player == 0? s1 + piles[p2]: s1), (player == 1? s2 + piles[p2]: s2), 1-player, memo);

        memo.put(key, (pickP1 || pickP2));
        return memo.get(key);
    }

    /**
        This sol is based on mik's gametheory explanation.
        @see {@link lc_potd.PredictTheWinner}
        @see {@link lc_potd.GridGame}
    */
    private boolean revise(int[] piles) {
        int n = piles.length;
        int ts = Arrays.stream(piles).sum();
        Integer[][] dp = new Integer[n+1][n+1];
        int aliceScore = gametheory(piles, dp, 0, n-1);

        return aliceScore > ts - aliceScore;
    }

    private int gametheory(int[] piles, Integer[][] dp, int i, int j) {
        if (i > j) return 0;

        if (dp[i][j] != null) return dp[i][j];

        int l = piles[i] + Math.min(
                gametheory(piles, dp, i+1, j-1),
                gametheory(piles, dp, i+2, j)
        );

        int r = piles[j] + Math.min(
                gametheory(piles, dp, i+1, j-1),
                gametheory(piles, dp, i, j-2)
        );

        dp[i][j] =  Math.max(l, r);
        return dp[i][j];
    }
}
