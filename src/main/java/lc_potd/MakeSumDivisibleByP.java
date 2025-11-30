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

    /**
     After find the `targetSum` a brute
     force approach will be find the sum of each
     sub array using two loops. This can be avoided
     using the prefix sum technique, which helps
     us get the sum of any subarray in constant time.
     */

    private int revise(int[] nums, int p) {
        int n = nums.length;

        long ts = 0;
        for (int num: nums) ts += num;
        if (ts%p == 0) return 0;
        if (ts < p) return -1;

        long targetSum = ts%p;

        /**
         I had a strong urge to write a sliding window
         here to find the smallest sub array that sums
         to target. But that won't really solve the problem
         always. We need to follow the usual prefix sum sort
         of pattern that is also used for similar problems
         like counting sub arrays with sum k etc.

         e.g. of a test case that will fail with sliding
         window -
         nums = [26,8,26,3]
         p = 26
         */
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0l, -1); //to counter the case when entire subarray needs to be removed.
        long currSum = 0;
        int minLen = n;
        for (int i=0; i<n; i++) {
            currSum = (currSum + nums[i])%p;
            long prevSum = (currSum - targetSum + p)%p; //currSum-prevSum = targetSum
            if (mp.containsKey(prevSum)) minLen = Math.min(minLen, i-mp.get(prevSum));

            mp.put(currSum, i);

        }

        return minLen == n ? -1: minLen;

    }
}
