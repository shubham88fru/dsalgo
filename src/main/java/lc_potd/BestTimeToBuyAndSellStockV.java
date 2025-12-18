package lc_potd;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/description/?
public class BestTimeToBuyAndSellStockV {
    public long maximumProfit(int[] prices, int k) {
        // return topdown(prices, k);
        return bottomUp(prices, k);
    }

    /**
         Bottom up, converted from topdown. Shown
         by mik.
     */
    private long bottomUp(int[] prices, int K) {
        int n = prices.length;

        Long[][][] dp = new Long[n+1][K+1][3];
        for (int k=0; k<=K; k+=1) {
            dp[n][k][0] = 0l;
            dp[n][k][1] = Long.MIN_VALUE/2;
            dp[n][k][2] = Long.MIN_VALUE/2;
        }

        for (int i=n-1; i>=0; i--) {
            for (int k=0; k<=K; k+=1) {
                // CASE 0: no open transaction
                dp[i][k][0] = dp[i + 1][k][0]; // do nothing
                if (k > 0) {
                    dp[i][k][0] = Math.max(
                            dp[i][k][0],
                            Math.max(
                                    -prices[i] + dp[i + 1][k][1], // buy
                                    prices[i] + dp[i + 1][k][2]  // short sell
                            )
                    );
                }

                // CASE 1: holding long
                dp[i][k][1] = dp[i + 1][k][1]; // hold
                if (k > 0) {
                    dp[i][k][1] = Math.max(
                            dp[i][k][1],
                            prices[i] + dp[i + 1][k - 1][0] // sell
                    );
                }

                // CASE 2: holding short
                dp[i][k][2] = dp[i + 1][k][2]; // hold
                if (k > 0) {
                    dp[i][k][2] = Math.max(
                            dp[i][k][2],
                            -prices[i] + dp[i + 1][k - 1][0] // buy back
                    );
                }
            }
        }



        return dp[0][K][0];
    }

    /**
     * My topdown soln.
     */
    private long topdown(int[] prices, int k) {
        int n = prices.length;
        Long[][][] cache = new Long[n+1][(n/2)+1][4];
        return dp(prices, k, 0, 0, cache);
    }

    private long dp(int[] prices, int k, int i, int type, Long[][][] cache) {
        if (k <= 0) return 0;

        if (i >= prices.length) {
            if (type == 0) return 0;

            return Long.MIN_VALUE/2;
        }

        if (cache[i][k][type] != null) return cache[i][k][type];

        long max = Long.MIN_VALUE;
        if (type == 0) {
            long buy = -(1L*prices[i]) + dp(prices, k, i+1, 1, cache); //next sell
            long sell = (1L*prices[i]) + dp(prices, k, i+1, 2, cache); //next buy
            max = Math.max(max, Math.max(buy, sell));
        } else if (type == 1) { // can only sell
            long buySell = (1L*prices[i]) + dp(prices, k-1, i+1, 0, cache);
            max = Math.max(max, buySell);
        } else { // can only buy
            long sellBuy = -(1L*prices[i]) + dp(prices, k-1, i+1, 0, cache);
            max = Math.max(max, sellBuy);
        }

        long idle = dp(prices, k, i+1, type, cache);

        cache[i][k][type] = Math.max(idle, max);
        return cache[i][k][type];
    }
}
