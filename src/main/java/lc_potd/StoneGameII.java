package lc_potd;

//@link - https://leetcode.com/problems/stone-game-ii/description/
//@check - https://www.youtube.com/watch?v=9f1vzDFVnGA
public class StoneGameII {
    public int stoneGameII(int[] piles) {
        /**
         ------------
         Game theory.
         ------------
         1. This pattern falls under DP.
         2. Idea of game theory problems is:
         - When its your (i.e. Alice) turn, do your best (i.e. max)
         - When its your opponent's (i.e. Bob) turn, expect the worst (i.e. min)
         */


        Integer[][][] memo = new Integer[2][101][101];
        return solveForAlice(piles, 1, 0, 1, memo); //1 - Alice, 0 - Bob.
    }

    private int solveForAlice(int[] piles, int person, int i, int M, Integer[][][] memo) {
        int n = piles.length;
        if (i >=n ) return 0;

        int result = (person == 1) ? -1: Integer.MAX_VALUE;
        int stones = 0;

        if(memo[person][i][M] != null) return memo[person][i][M];

        for (int x = 1; x <= Math.min(2*M, n-i); x += 1) {
            stones += piles[i+x-1];
            if (person == 1) {
                //When it's your turn, do the best (max).
                result = Math.max(result, stones + solveForAlice(piles, 0, i+x, Math.max(M, x), memo));
            } else {
                //When it's opponent's turn, expect the worst (min).
                result = Math.min(result, solveForAlice(piles, 1, i+x, Math.max(M, x), memo));
            }
        }

        memo[person][i][M] = result;
        return memo[person][i][M];
    }
}
