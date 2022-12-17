package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
public class BuyAndSellStockIII {
    /** SWD SOLN **/
    public int maxProfit(int[] prices) {
        Map<String, Integer> memo = new HashMap<String, Integer>();
        return bestTimeToBuyAndSellForMaxProfit(prices, 0, 1, 2, memo);
    }

    private int bestTimeToBuyAndSellForMaxProfit(int[] prices, int currentDay, int canBuy, int txnCount, Map<String, Integer> memo) {
        if (currentDay>=prices.length || txnCount == 0) return 0;

        String key = currentDay + "-" + canBuy + "-" + txnCount;

        if (memo.containsKey(key)) return memo.get(key);

        int idle = bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, canBuy, txnCount, memo);
        if (canBuy == 1) {
            int buy = -prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 0, txnCount, memo);
            memo.put(key, Math.max(idle, buy));
        } else {
            int sell = prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 1, txnCount-1, memo);
            memo.put(key, Math.max(idle, sell));
        }
        return memo.get(key);
    }
}
