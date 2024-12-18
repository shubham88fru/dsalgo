package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/
public class FinalPricesWithASpecialDiscountInAShop {
    public int[] finalPrices(int[] prices) {
        return revise(prices);
    }

    private int[] revise(int[] prices) {
        Deque<int[]> stack = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];

        int i = 0;
        while (i < n) {
            if (stack.isEmpty() || (prices[i] > stack.peekFirst()[0])) {
                stack.addFirst(new int[] {prices[i], i});
                ans[i] = prices[i];
                i += 1;
            } else {
                int[] rem = stack.removeFirst();
                ans[rem[1]] = rem[0] - prices[i];
            }
        }

        return ans;
    }

    private int[] sol2(int[] prices) {
        int n = prices.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(prices[n-1]);

        for (int i=n-2; i>=0; i--) {
            while (!stack.isEmpty() && (prices[i] < stack.peekFirst())) {
                stack.removeFirst();
            }
            int discount = 0;
            if (stack.size() > 0) discount = stack.peekFirst();
            stack.addFirst(prices[i]);
            prices[i] -= discount;
        }
        return prices;
    }
}
