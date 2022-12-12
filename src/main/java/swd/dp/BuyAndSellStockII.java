package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BuyAndSellStockII {
    //Can do any no. of transactions
    /** SWD SOLN **/
    public int maxProfit(int[] prices) {
        return bestTimeToBuyAndSellForMaxProfit(prices, 0, 1, new HashMap<String, Integer>());
    }

    private int bestTimeToBuyAndSellForMaxProfit(int[] prices, int currentDay, int canBuy, Map<String, Integer> memo) {
        if (currentDay>=prices.length) return 0;

        String key = currentDay + "-" + canBuy;

        if (memo.containsKey(key)) return memo.get(key);

        int idle = bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, canBuy, memo);
        if (canBuy == 1) {
            int buy = -prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 0, memo);
            memo.put(key, Math.max(idle, buy));
        } else {
            int sell = prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 1, memo);
            memo.put(key, Math.max(idle, sell));
        }
        return memo.get(key);
    }
}
