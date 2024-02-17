package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link -  https://leetcode.com/problems/coin-change/description/
//@strvr - https://takeuforward.org/data-structure/coin-change-2-dp-22/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6698398708400128
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        long ans = coinchange(coins, amount, 0, new HashMap<>());
        return ans == Integer.MAX_VALUE ? -1: (int)ans;
        /** SWD Soln
        int ans = minCoinsForGivenAmount(coins, amount, 0, new HashMap<String, Integer>());
        if (ans == 10001) return -1;
        return ans;
         **/
    }

    //1) My soln
    private long coinchange(int[] coins, int amount, int curr, Map<String, Long> cache) {
        if (amount == 0) {
            return 0;
        }
        if (curr >= coins.length || amount < 0) {
            return Integer.MAX_VALUE;
        }

        String key = amount + "-" + curr;
        if (cache.containsKey(key)) return cache.get(key);

        long pick = 1 + coinchange(coins, amount-coins[curr], curr, cache);

        long notPick = coinchange(coins, amount, curr+1, cache);

        cache.put(key, Math.min(pick, notPick));
        return cache.get(key);
    }

    //2) SWD Soln
    private int minCoinsForGivenAmount(int[] coins, int targetAmount, int currIndex, Map<String, Integer> memo) {

        if (targetAmount==0)
            return 0;

        if (currIndex==coins.length)
            return 10001; //more than max possible amount.

        int includeCurr = 10001; //more than max possible amount.

        String key = targetAmount + "-" + currIndex;
        if (memo.containsKey(key)) return memo.get(key);

        if (targetAmount>=coins[currIndex]) {
            //add 1 because it is a possible move.
            includeCurr = 1 + minCoinsForGivenAmount(coins, targetAmount-coins[currIndex], currIndex, memo);

        }

        int dontIncludeCurr = minCoinsForGivenAmount(coins, targetAmount, currIndex+1, memo);

        memo.put(key, Math.min(includeCurr, dontIncludeCurr));
        return memo.get(key);

    }
}
