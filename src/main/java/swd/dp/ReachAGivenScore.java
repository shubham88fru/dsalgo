package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/reach-a-given-score-1587115621/1
public class ReachAGivenScore {

    public long count(int n) {
        return waysToReachGivenScore(n, new int[]{3, 5, 10}, 0, new HashMap<Integer, Long>());
    }

    private long waysToReachGivenScore(int n, int[] possibleMoves, int currIndex, Map<Integer, Long> memo) {
        // return (three + five + ten);
        // if (n<0) return 0;
        // if (n==0) return 1;

        // Integer key = n;
        // if (memo.containsKey(key)) return memo.get(key);

        // long three = waysToReachGivenScore(n-3, memo);
        // long five = waysToReachGivenScore(n-5, memo);
        // long ten = waysToReachGivenScore(n-10, memo);

        // memo.put(key, (three+five+ten));
        // return memo.get(key);
        if (currIndex>=possibleMoves.length) return 0;
        if (n==0) return 1;

        long consider = 0;
        int move = possibleMoves[currIndex];
        if (move<=n) {
            consider = waysToReachGivenScore(n-move, possibleMoves, currIndex, memo);
        }

        long notConsider = waysToReachGivenScore(n, possibleMoves, currIndex+1, memo);

        return (consider + notConsider);
    }
}
