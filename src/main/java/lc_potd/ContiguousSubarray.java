package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/contiguous-array/description/

/**
 * @see lc_potd.ContinuousSubarraySum
 */
public class ContiguousSubarray {
    public int findMaxLength(int[] nums) {
        int maxLen = 0;
        int ps = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(ps, 0);
        for (int i=0; i<nums.length; i++) {
            //+1 if num is 1, -1 if num 0.
            if (nums[i] == 0) ps += -1;
            else ps += 1;

            //If ps is already seen before, means the sum of
            //nums in the range is 0, i.e. it must have equal num
            //of 0s and 1s, coz only then the sum will end up being 0
            //(equal number of +1 and -1)
            if (mp.containsKey(ps)) maxLen = Math.max(maxLen, i+1-mp.get(ps));
            else mp.put(ps, i+1);

        }

        return maxLen;
    }
}
