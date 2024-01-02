package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BuyAndSellStock {
    // At most only one txn allowed
    public int maxProfit(int[] prices) {
        return bestTimeToBuyAndSellForMaxProfitDP(prices, 0, 1, 1, new HashMap<String, Integer>());
        //return bestTimeToBuyAndSellForMaxProfitGreedy(prices);
        //return bestTimeToBuyAndSellForMaxProfitSlidingWindow(prices);
    }

    //0) Sliding Window solution
    private int bestTimeToBuyAndSellForMaxProfitSlidingWindow(int[] prices) {
        int start = 0;
        int end = 0;
        int maxP = 0;

        while (end < prices.length) {
            int acq = prices[end];
            //keep acquiring and calculating the best
            //profit till the end is larger than start.
            //If end is smaller, we slide the window from the end.
            if (acq >= prices[start]) {
                maxP = Math.max(maxP, acq-prices[start]);
                end += 1;
            } else start += 1;


        }

        return maxP;
    }

    //1) Top-Down DP Soln
    /**
     Between the buy and a sell, we can remain idle (in pursuit of the best time to sell).
     And so, at any day, we can do three things - Buy, sell or remain idle.
     However, For this question:
     - We can only sell if we have bought first (so we need the canBuy variable.)
     - We can do atmost one txn. Therefore, we track the txnCount varible.
     Start with 1 and decrement it when selling. As soon as txnCount becomes 0
     man we completed on txn.
     */
    private int bestTimeToBuyAndSellForMaxProfitDP(int[] prices, int currentDay, int canBuy, int txnCount, Map<String, Integer> memo) {
        if (currentDay>=prices.length || txnCount == 0) return 0;

        String key = currentDay + "-" + canBuy + "-" + txnCount;

        if (memo.containsKey(key)) return memo.get(key);

        //if we go idle - we move to next day, no change to canBuy (same as whatever it was), and txnCount.
        int idle = bestTimeToBuyAndSellForMaxProfitDP(prices, currentDay+1, canBuy, txnCount, memo);
        if (canBuy == 1) {
            //since, we are buying, we're lossing money.
            //We move to next day, canBuy becomes 0 - from now, we can only go idle or sell.
            int buy = -prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfitDP(prices, currentDay+1, 0, txnCount, memo);
            memo.put(key, Math.max(idle, buy));
        } else { //can only sell if can't buy, i.e. we are already holding a stock.
            //while selling, we earn that day's money.
            //We move to next day, canBuy becomes 1, and txnCount goes down.
            int sell = prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfitDP(prices, currentDay+1, 1, --txnCount, memo);
            memo.put(key, Math.max(idle, sell));
        }
        return memo.get(key);
    }

    //2) Greedy approach
    private int bestTimeToBuyAndSellForMaxProfitGreedy(int[] prices) {
        int minPriceSeenSoFar = Integer.MAX_VALUE; //We need to buy at lowest price.
        int maxProfit = 0; //default

        for (int price: prices) {
            //keep track of min price (potential buying price)
            //seen so far.
            if (price < minPriceSeenSoFar) {
                minPriceSeenSoFar = price;
            }

            //if selling at current price yeilds more profit
            //than so far, update max profit.
            /*
                int profitPotenz = (price-minPriceSeenSoFar);
                if (profitPotenz > maxProfit) maxProfit = profitPotenz;
             */
            maxProfit = Math.max(maxProfit, price-minPriceSeenSoFar);
        }

        return maxProfit;
    }
}
