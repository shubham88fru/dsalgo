package ptrn.dp;

// @link - https://leetcode.com/problems/coin-change-ii/
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        return pass1(amount, coins);
    }

    /*
        My top-down approach.
     */
    private int pass1(int amount, int[] coins) {
        int n = coins.length;
        Integer[][] dp = new Integer[amount+1][n+1];
        return dp(coins, amount, 0, dp);
    }


    private int dp(int[] coins, int amount, int i, Integer[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        if (i >= coins.length) return 0;

        if (dp[amount][i] != null) return dp[amount][i];

        int pick = dp(coins, amount-coins[i], i, dp);
        int skip = dp(coins, amount, i+1, dp);

        dp[amount][i] = (pick + skip);
        return dp[amount][i];
    }
}
