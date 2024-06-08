package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/continuous-subarray-sum/description/
//@check - https://www.youtube.com/watch?v=1W_HYBqvDLw
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        /**
         In short, the idea to solve this problem is that
         we'll keep a track of prefix sum mod k and if at
         any point we see a prefix sum mod k that was seen before,
         it means that that sum of nums in the range from current
         to previous is divisible by k (coz prefix sum mod k for that range is 0)
         */
        //we'll keep track of prefix sum mod k (instead of simple prefix sum)
        int psumModK = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(psumModK, 0);
        for (int i=0; i<nums.length; i++) {
            psumModK = (psumModK + nums[i])%k; //Note: can't write this eqn as --> psumModK += (nums[i]%k)

            //If at an index, we get a prefix sum (mod k) that was
            //already seen before, it means that the sum (mod k) of
            //numbers between the two indices was certainly zero i.e. divisible
            //by k. Therefore, all we'll have to confirm is that the length of
            //such range is more than two (ATQ).
            if (mp.containsKey(psumModK) && (i+1-mp.get(psumModK) >= 2)) return true;

            //If the we have already seen a prefix sum (mod k), we won't
            //update the map, coz we are anyways interested in finding the
            //largest subarray to know whether to return true or false.
            if (!mp.containsKey(psumModK)) mp.put(psumModK, i+1); //Note i+1, not i.
        }

        return false;
    }
}
