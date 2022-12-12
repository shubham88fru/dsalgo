package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
public class BuyAndSellWithTxnFee {
    //Can do any no. of transactions
    /** SWD SOLN **/
    public int maxProfit(int[] prices, int fee) {
        return bestTimeToBuyAndSellForMaxProfit(prices, fee, 0, 1, new HashMap<String, Integer>());
    }

    private int bestTimeToBuyAndSellForMaxProfit(int[] prices, int fee, int currentDay, int canBuy, Map<String, Integer> memo) {
        if (currentDay>=prices.length) return 0;

        String key = currentDay + "-" + canBuy;

        if (memo.containsKey(key)) return memo.get(key);

        int idle = bestTimeToBuyAndSellForMaxProfit(prices, fee, currentDay+1, canBuy, memo);
        if (canBuy == 1) {
            int buy = -prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, fee, currentDay+1, 0, memo);
            memo.put(key, Math.max(idle, buy));
        } else {
            int sell = prices[currentDay] - fee + //subtract the txn fee
                    + bestTimeToBuyAndSellForMaxProfit(prices, fee, currentDay+1, 1, memo);
            memo.put(key, Math.max(idle, sell));
        }
        return memo.get(key);
    }
}
