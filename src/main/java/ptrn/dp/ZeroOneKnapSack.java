package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
//@strvr - https://takeuforward.org/data-structure/0-1-knapsack-dp-19/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6454152865251328
public class ZeroOneKnapSack {

    static int knapSack(int W, int wt[], int val[], int n)
    {
        return maxProfitForWeightLimit(W, wt, val, 0, new HashMap<String, Integer>());
    }

    //1) My top-down solution.
    private static int knap(int W, int[] wt, int[] val, int curr, Map<String, Integer> cache) {
        //Note: the below order of checking -ve weight
        //before the next line matters. If we put this -ve weight check
        //after the next line, it won't work.
        //Another approach to fix this caveat it to
        //do a weight check while calling the pick recursive call.
        //Like swd did.
        if (W < 0) return Integer.MIN_VALUE;
        if (curr >= val.length || W==0) return 0;

        String key = W + "-" + curr;
        if (cache.containsKey(key)) return cache.get(key);

        int pick = val[curr] + knap(W-wt[curr], wt, val, curr + 1, cache);
        int dontPick = knap(W, wt, val, curr+1, cache);

        cache.put(key, Math.max(pick, dontPick));
        return cache.get(key);
    }

    //2) SWD - top-down solution.
    //T: O(2^N), S: O(N)
    static int maxProfitForWeightLimit(int weightLimit, int[] weightsArr, int[] profitsArr, int currIndex, Map<String, Integer> memo) {
        if (currIndex>=profitsArr.length) return 0;
        int currItemWeight = weightsArr[currIndex];
        int currItemProfit = profitsArr[currIndex];

        String currentKey = Integer.toString(currIndex) + "-" + Integer.toString(weightLimit); //Make a key for DP

        if (memo.containsKey(currentKey))
            return memo.get(currentKey);

        int putCurrentItemInKnapSack = 0, skipCurrentItem = 0;

        if (currItemWeight<=weightLimit) {
            putCurrentItemInKnapSack = currItemProfit
                    + maxProfitForWeightLimit(weightLimit-currItemWeight, weightsArr, profitsArr, currIndex+1, memo);
        }
        skipCurrentItem = maxProfitForWeightLimit(weightLimit, weightsArr, profitsArr, currIndex+1, memo);

        memo.put(currentKey, Math.max(putCurrentItemInKnapSack, skipCurrentItem));

        return memo.get(currentKey);
    }

    //3) check edctv for a bottom up approach.

}
