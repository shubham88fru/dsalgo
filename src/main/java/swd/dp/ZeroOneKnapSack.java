package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article

public class ZeroOneKnapSack {

    static int knapSack(int W, int wt[], int val[], int n)
    {
        return maxProfitForWeightLimit(W, wt, val, 0, new HashMap<String, Integer>());
    }

    //T: O(2^N), S: O(N)
    static int maxProfitForWeightLimit(int weightLimit, int[] weightsArr, int[] profitsArr, int currIndex, Map<String, Integer> memo) {

        /***My soln - not working***/
        // if (currIndex>=profitsArr.length) return 0;
        // //if (weightLimit==0) return 0;
        // if (weightLimit<0) return -5000;


        // int putCurrentItemInKnapSack = profitsArr[currIndex]
        //         + maxProfitForWeightLimit(weightLimit-weightsArr[currIndex], weightsArr, profitsArr, currIndex+1);

        // int skipCurrentItem = maxProfitForWeightLimit(weightLimit, weightsArr, profitsArr, currIndex+1);

        // return Math.max(putCurrentItemInKnapSack, skipCurrentItem);

        /***SWD soln***/
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
}
