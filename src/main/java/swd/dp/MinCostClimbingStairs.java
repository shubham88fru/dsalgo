package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/min-cost-climbing-stairs/description/
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {

        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
        //Min cost for starting from stair 0
        int minCostStair0 = minCostClimbing(0, cost, memo);

        //Min cost for starting from stair 1
        //int minCostStair1 = minCostClimbing(1, cost, new HashMap<Integer, Integer>());
        int minCostStair1 = memo.get(1); //memo will already have solved value from call on line 6

        //Min of starting from stair 0 or stair 1
        return Math.min(minCostStair0, minCostStair1);
    }

    private int minCostClimbing(int startStair, int[] cost, Map<Integer, Integer> memo) {
        if (startStair > cost.length) return 1001; //max cost so that it is neglected
        if (startStair == cost.length) return 0; //no cost for reaching to same stair from same stair

        int key = startStair;

        if (memo.containsKey(key)) return memo.get(key);

        int oneStep = minCostClimbing(startStair + 1, cost, memo);
        int twoStep = minCostClimbing(startStair + 2, cost, memo);

        memo.put(key, cost[startStair] + Math.min(oneStep, twoStep));

        return memo.get(key);
    }
}
