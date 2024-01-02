package ptrn.slidingwindow;

//@link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6681862044123136
public class BuyAndSellStock {
    public int maxProfit(int[] prices) {
        //return bestTimeToBuyAndSellForMaxProfitDP(prices, 0, 1, 1, new HashMap<String, Integer>());
        //return bestTimeToBuyAndSellForMaxProfitGreedy(prices);
        return bestTimeToBuyAndSellForMaxProfitSlidingWindow(prices);
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
}
