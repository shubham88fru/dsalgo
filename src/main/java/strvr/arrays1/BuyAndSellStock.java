package strvr.arrays1;

import java.util.Map;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
//@strvr - https://takeuforward.org/data-structure/stock-buy-and-sell/
public class BuyAndSellStock {
    // At most only one txn allowed
    public int maxProfit(int[] prices) {
        //return bestTimeToBuyAndSellForMaxProfitDP(prices, 0, 1, 1, new HashMap<String, Integer>());
        return bestTimeToBuyAndSellForMaxProfitGreedy(prices);
    }

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
