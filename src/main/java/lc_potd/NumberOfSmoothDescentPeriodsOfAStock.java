package lc_potd;

//@link - https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/description/?
public class NumberOfSmoothDescentPeriodsOfAStock {
    public long getDescentPeriods(int[] prices) {
        return pass1(prices);
    }

    /**
     * My sol.
     * Sliding window/two pointer.
     */
    private long pass1(int[] prices) {
        int n = prices.length;

        int i = 0;
        int j = 1;
        long ans = 0;
        while (j <= n) {
            while (j < n && (prices[j]-prices[j-1] == -1)) {
                j += 1;
            }
            long m = j-i;
            ans += ((m*(m+1))/2);
            i = j;
            j += 1;
        }

        return ans;
    }
}
