package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/divide-array-into-equal-pairs/description/
public class DivideArrayIntoEqualPairs {
    public boolean divideArray(int[] nums) {
        return brute(nums);
    }

    private boolean brute(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int xor = 0;
        for (int num: nums) {
            xor ^= num;
            mp.put(num, mp.getOrDefault(num, 0)+1);
        }

        int groups = 0;
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            int freq = entry.getValue();
            if (freq%2 != 0) return false;
            groups += freq/2;
        }

        return groups == nums.length/2;
    }

    /*
        Failed in 1st attempt.
        This was a bummer but good learning.
        Given an array of nums, we can simply apply
        a xor and claim that the nums are in pairs
        if xor is zero.
        e.g. - [0, 1, 2, 3] has a xor of zero:
        1^2 = 3 -> 3^3 = 0 -> 0^0 = 0
    */
    private boolean usingXor(int[] nums) {
        int xor = 0;
        for (int num: nums) xor ^= num;

        return xor == 0;
    }
}
