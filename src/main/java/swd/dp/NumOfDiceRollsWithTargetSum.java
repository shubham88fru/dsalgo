package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/description/
public class NumOfDiceRollsWithTargetSum {
    /**MY SOLUTION**/
    public int numRollsToTarget(int n, int k, int target) {
        return numWaysToTarget(n, k, target, 0, new HashMap<String, Integer>());
    }

    private int numWaysToTarget(int n, int k, int target, int currIndex, Map<String, Integer> memo) {
        if (target == 0 && currIndex == n) return 1;

        if (currIndex >= n || target <= 0) return 0;

        int ways = 0;
        String key = currIndex + "-" + target;
        int MOD = (int)Math.pow(10, 9) + 7; //10^9 + 7
        if (memo.containsKey(key)) return memo.get(key);
        for (int j=1; j<=k; j++) {
            ways = (ways%MOD + numWaysToTarget(n, k, target-j, currIndex+1, memo)%MOD)%MOD; ////ways += ways + <recursive_call>
        }
        memo.put(key, (ways));
        return memo.get(key);
    }


    /**SWD SOLUTION**/
    // public int numRollsToTarget(int n, int k, int target) {
    //     return numWaysToTarget(n, k, target, new HashMap<String, Integer>());
    // }

    // private int numWaysToTarget(int n, int k, int target, Map<String, Integer> memo) {
    //     if (target == 0 && n == 0) return 1;

    //     if (n <= 0 || target <= 0) return 0;

    //     int ways = 0;
    //     int MOD = (int)Math.pow(10, 9) + 7; //10^9 + 7
    //     String key = n + "-" + target;
    //     if (memo.containsKey(key)) return memo.get(key);
    //     // (a+b)%c = (a%c + b%c)%c
    //     for (int j=1; j<=k; j++) {
    //        ways = (ways%MOD + numWaysToTarget(n-1, k, target-j, memo)%MOD)%MOD;
    //     }
    //     memo.put(key, (ways));
    //     return memo.get(key);
    //     //return ways;
    // }
}
