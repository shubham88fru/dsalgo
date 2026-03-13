package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/climbing-stairs/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5240641291550720
/*
Dynamic programming is nothing but storing the results of a subproblem so that next time
we encounter the same sub problem which we have solved already, we return the result of the
sub problem from our cache instead of solving it again.

DP is usually helpful in recursion questions.

Below we have 2 solns. 1st is simple recursive code which give TLE error on leetcode
and 2nd soln we've converted 1st to a DP soln.
*/
public class ClimbingStairs {
    public int climbStairs(int n) {

        //we're wrapping our recursive
        //function in the function that leetcode will
        //call.
        //return totalWaysRecursive(0, n);
        return totalWaysDP(0, n, new HashMap<Integer, Integer>());
    }

    //Sol 1: Simple Recursive T: O(2^N), S: O(N)
    private int totalWaysRecursive(int currStair, int targetStair) {

        //If we reach to a stair higher than the max stair (n), means it was a wrong route
        //hence we'd return 0
        if (currStair>targetStair) return 0;

        //If we reach the target stair, its a valid route, we'll return 1.
        if (currStair == targetStair) return 1;

        //For any stair, there are two options, we either go one stair up or we go
        //two stairs up. And the total no. of possible ways will simply be sum of all
        //such possible moves from subsequent stair.
        //total ways from next stair from current
        return totalWaysRecursive(currStair+1, targetStair) +

                //total ways from second next stair from current.
                totalWaysRecursive(currStair+2, targetStair);
    }


    //Sol 2: DP T: O(N), S: O(N)
    private int totalWaysDP(int currStair, int targetStair, Map<Integer, Integer> memo) {

        if (currStair>targetStair) return 0;

        if (currStair == targetStair) return 1;


        /* STEPS FOR MEMOIZATION (DP) */
        //STEP 1: Identity the changing param of recursive call
        //        and make a key out of it.
        int currentKey = currStair;

        //STEP 2: If cached, no need to solve, return from cache
        if (memo.containsKey(currentKey)) return memo.get(currentKey);

        //STEP 3: Else, solve and cache
        int oneStepWays = totalWaysDP(currStair+1, targetStair, memo);
        int twoStepWays = totalWaysDP(currStair+2, targetStair, memo);

        int res = oneStepWays + twoStepWays;

        //put to cache
        memo.put(currentKey, res);

        return res;
    }

    //Sol 3: check ectv for bottom-up approach.

    /*
        This is basically fibonnaci.
        The idea is to basically think
        of this problem from opposite side.
        i.e. to reach the nth stair, we can
        eventually only come from n-1 or n-2th
        stair, coz only 1 and 2 steps are allowed.
        Therefore, the number of ways to reach the
        nth stair is basically the sum of ways to
        reach n-1 and n-2th stairs and so on.
    */
    private int budp(int n) {
        if (n == 0) return 0;
        if (n <= 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
