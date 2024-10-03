package lc_potd;

import java.util.HashMap;
import java.util.Map;


/*
    This question was simple, and very similar to finding subarrays
    with a target sum. And so, it's unfortunate that I couldn't
    solve it on my own.

    Anyways, following is mik's soln. Try again on next encounter.
 */
//@link - https://leetcode.com/problems/make-sum-divisible-by-p/description/
//@check - https://www.youtube.com/watch?v=5jpCEfRI1sM&ab_channel=codestorywithMIK
public class MakeSumDivisibleByP {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long totalSum = 0;

        for (int i=0; i<n; i++) {
            totalSum = (totalSum + nums[i])%p;
        }
        long target = totalSum%p;

        if (target == 0) return 0;


        /*
            At this point, the question boils
            down to a problem similar to `finding subarrays whose sum is k.`
        */
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0l, -1);
        long curr = 0;
        int res = n;
        for (int i=0; i<n; i++) {
            curr = (curr + nums[i])%p;
            long pair = (curr - target + p)%p;
            if (mp.containsKey(pair)) {
                res = Math.min(res, (i - (mp.get(pair))));
            }

            mp.put(curr, i);
        }

        return res == n ? -1: res;
    }
}
