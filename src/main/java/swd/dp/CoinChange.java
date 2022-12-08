package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link -  https://leetcode.com/problems/coin-change/description/
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int ans = minCoinsForGivenAmount(coins, amount, 0, new HashMap<String, Integer>());
        if (ans == 10001) return -1;
        return ans;
    }

    private int minCoinsForGivenAmount(int[] coins, int targetAmount, int currIndex, Map<String, Integer> memo) {

        if (targetAmount==0)
            return 0;

        if (currIndex==coins.length)
            return 10001; //more than max possible amount.

        int includeCurr = 10001; //more than max possible amount.

        String key = targetAmount + "-" + currIndex;
        if (memo.containsKey(key)) return memo.get(key);

        if (targetAmount>=coins[currIndex]) {
            includeCurr = 1 + minCoinsForGivenAmount(coins, targetAmount-coins[currIndex], currIndex, memo);

        }

        int dontIncludeCurr = minCoinsForGivenAmount(coins, targetAmount, currIndex+1, memo);

        memo.put(key, Math.min(includeCurr, dontIncludeCurr));
        return memo.get(key);

    }
}
