package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/house-robber/description/
public class HouseRobber {
    public int rob(int[] nums) {
        return maxRob(0, nums, new HashMap<Integer, Integer>());
    }

    //T: O(N), S: O(N)
    private int maxRob(int currIndex, int[] nums, Map<Integer, Integer> memo) {

        if (currIndex>=nums.length) return 0;
        if (currIndex == nums.length) return nums[currIndex];

        int key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);

        //If Robber robs the current house, fir 1 ghar chhod ke hi chori kar skta hai.
        int robCurrent = nums[currIndex] + maxRob(currIndex+2, nums, memo);

        //If robber doesn't rob current house, fir agle ghar pe bhi chori kar skta hai.
        int dontRobCurrent = maxRob(currIndex+1, nums, memo);

        //Max of the two choices.
        memo.put(key, Math.max(robCurrent, dontRobCurrent));

        return memo.get(key);
    }
}
