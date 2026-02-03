package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BuyAndSellStockII {
    public int maxProfit(int[] prices) {
        // return recursion(prices);
        // return optimal2(prices);
        return optimal(prices);
    }

    /**
     Even better version of optimal2.
     See LC soln if confused. Basically,
     instead of find each peak-valley,
     we can continuously keep summing
     subsequent low-highs and it will
     eventually be same as summing
     peak-valley. See the diagram in
     LC editorial to understand.
     */
    private int optimal(int[] prices) {
        int n = prices.length;

        int maxProfit = 0;
        for (int i=1; i<n; i++) {
            if (prices[i] > prices[i-1]) maxProfit += (prices[i] - prices[i-1]);
        }

        return maxProfit;
    }

    /**
     If you think of it, this part
     is very similar to optimal approach of
     part 1 variant. The difference being that
     here we'll add every peak-valley pair
     because we can buy sell multiple times.
     */
    private int optimal2(int[] prices) {
        int n = prices.length;

        int i = 0, maxProfit = 0;
        while (i < n-1) {
            while (i < n-1 && prices[i] >= prices[i+1]) i+= 1; //find a valley.

            if (i == n) return maxProfit;

            int valley = prices[i];

            while (i < n-1 && prices[i] <= prices[i+1]) i += 1; //find the peak.

            int peak = prices[i];
            maxProfit += (peak - valley);
        }

        return maxProfit;
    }

    private int recursion(int[] prices) {
        int n = prices.length;

        Integer[][] dp = new Integer[n+1][3];
        return dp2(prices, 0, 1, dp);
    }

    private int dp2(int[] prices, int i, int t, Integer[][] dp) {
        if (i >= prices.length) return 0;

        if (dp[i][t] != null) return dp[i][t];

        int buy = 0, sell = 0;
        if (t == 1) {
            buy = -prices[i] + dp2(prices, i+1, 0, dp);
        } else {
            sell = prices[i] + dp2(prices, i+1, 1, dp);
        }

        int hold = dp2(prices, i+1, t, dp);

        dp[i][t] = Math.max(buy, Math.max(sell, hold));
        return dp[i][t];
    }
}
