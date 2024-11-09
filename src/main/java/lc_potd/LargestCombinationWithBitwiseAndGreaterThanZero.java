package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/
//@check - https://www.youtube.com/watch?v=9ZlMnn_N0QE&ab_channel=codestorywithMIK
public class LargestCombinationWithBitwiseAndGreaterThanZero {
    public int largestCombination(int[] candidates) {

        ///// FOR DP SOLN - Gives TLE /////////
        //Map<String, Integer> memo = new HashMap<>();
        //long allOnes = 0xFFFFFFFFL; //4294967295 - trick to get an initial 32 bit value with all bits set to one.
        //int len = dp(candidates, 0, allOnes,0, memo);
        //return len <= 0 ? 0: len;
        //////////////////////

        return bitwiseManipSol(candidates);
    }

    private int bitwiseManipSol(int[] candidates) {
        Map<Integer, Integer> mp = new HashMap<>();

        int max = 0;
        for (int candidate: candidates) {
            int idx = 0;
            while (candidate > 0) {
                int bit = candidate & 1;
                candidate = candidate >> 1;
                if (!mp.containsKey(idx)) mp.put(idx, 0);
                if (bit==1) {
                    mp.put(idx, mp.get(idx)+1);
                    max = Math.max(max, mp.get(idx));
                }
                idx += 1;
            }
        }

        return max;
    }

    /*
        My dp soln. Gives TLE.
    */
    private int dp(int[] candidates, int curr, long _and, int len, Map<String, Integer> memo) {
        if (_and <= 0) return 0;
        if (curr >= candidates.length) return len;

        String key = curr+"_"+_and+"_"+len;
        if (memo.containsKey(key)) return memo.get(key);

        int pick = dp(candidates, curr+1, _and&candidates[curr], len+1, memo);
        int notPick = dp(candidates, curr+1, _and, len, memo);

        memo.put(key, Math.max(pick, notPick));
        return Math.max(pick, notPick);
    }
}
