package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/rod-cutting0840/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class RodCutting {

    public int cutRod(int[] price, int n) {
        return cutRodForMaxPrice(price, n, 0, new HashMap<String, Integer>());
    }

    private int cutRodForMaxPrice(int[] price, int remainingLength, int currIndex, Map<String, Integer> memo) {

        if (currIndex >= price.length) return 0;
        if (remainingLength == 0) return 0;

        int includeCurr = 0;

        String key = currIndex + "-" + remainingLength;
        if (memo.containsKey(key)) return memo.get(key);
        int currentPieceLength = currIndex+1;
        if (currentPieceLength <= remainingLength) {
            includeCurr = price[currIndex] +
                    cutRodForMaxPrice(price, remainingLength-currentPieceLength, currIndex, memo);
        }

        int dontIncludeCurr = cutRodForMaxPrice(price, remainingLength, currIndex+1, memo);

        memo.put(key, Math.max(includeCurr, dontIncludeCurr));
        return memo.get(key);
    }
}
