package swd.dp;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
public class BuyAndSellStockIII {
    /** SWD SOLN **/
    public int maxProfit(int[] prices) {
        int[][][] memo = new int[100000][2][3];
        //Arrays.fill(memo, -1);
        //Map<String, Integer> memo = new HashMap<String, Integer>();
        return bestTimeToBuyAndSellForMaxProfit(prices, 0, 1, 2, memo);
    }

    private int bestTimeToBuyAndSellForMaxProfit(int[] prices, int currentDay, int canBuy, int txnCount, int[][][] memo) {
        if (currentDay>=prices.length || txnCount == 0) return 0;

        //String key = currentDay + "-" + canBuy + "-" + txnCount;

        //if (memo.containsKey(key)) return memo.get(key);
        int val = memo[currentDay][canBuy][txnCount];
        if (val != 0) return val;

        int idle = bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, canBuy, txnCount, memo);
        if (canBuy == 1) {
            int buy = -prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 0, txnCount, memo);
            //memo.put(key, Math.max(idle, buy));
            memo[currentDay][canBuy][txnCount] = Math.max(idle, buy);
        } else {
            int sell = prices[currentDay]
                    + bestTimeToBuyAndSellForMaxProfit(prices, currentDay+1, 1, txnCount-1, memo);
            //memo.put(key, Math.max(idle, sell));
            memo[currentDay][canBuy][txnCount] = Math.max(idle, sell);
        }
        //return memo.get(key);
        return memo[currentDay][canBuy][txnCount];
    }
}
