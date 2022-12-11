package swd.dp;

import java.util.HashMap;
import java.util.Map;

public class BuyAndSellStock {
    /** MY SOLN - TLE **/
    // public int maxProfit(int[] prices) {
    //     return bestTimeToBuyAndSellForMaxProfit(prices);
    // }

    // private int bestTimeToBuyAndSellForMaxProfit(int[] prices) {
    //     List<Integer> lst = new ArrayList<>();
    //     Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    //     for (int i=0; (i<prices.length); i++) {
    //         int key = prices[i];
    //         if (memo.containsKey(key)) {
    //             lst.add(memo.get(key));
    //             continue;
    //         }
    //         int buyCurr = bestTimeToSell(prices, key, i+1, new HashMap<Integer, Integer>());
    //         memo.put(key, buyCurr);
    //         lst.add(memo.get(key));
    //     }

    //     return Collections.max(lst);
    // }

    // private int bestTimeToSell(int[] prices, int buyPrice, int currIndex, Map<Integer, Integer> memo) {
    //     if (currIndex>=prices.length) return 0;


    //     int key = currIndex;
    //     if (memo.containsKey(currIndex)) return memo.get(currIndex);
    //     int sellCurr = 0;
    //     if (buyPrice <= prices[currIndex]) {
    //         sellCurr = prices[currIndex] - buyPrice;
    //     }

    //     int dontSellCurr = bestTimeToSell(prices, buyPrice, currIndex+1, memo);

    //     memo.put(currIndex, Math.max(sellCurr, dontSellCurr));
    //     return memo.get(currIndex);
    // }

    /** SWD SOLN **/
    public int maxProfit(int[] prices) {
        return bestTimeToBuyAndSellForMaxProfit(prices, 0, 1, 1, new HashMap<String, Integer>());
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
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 1, --txnCount, memo);
            memo.put(key, Math.max(idle, sell));
        }
        return memo.get(key);
    }
}
