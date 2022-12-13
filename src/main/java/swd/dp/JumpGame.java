package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/jump-game/
public class JumpGame {
    public boolean canJump(int[] nums) {
        return canReachLastIndex(nums, 0, new HashMap<Integer, Boolean>());
    }

    private boolean canReachLastIndex(int[] nums, int currIndex, Map<Integer, Boolean> memo) {
        if (currIndex >= nums.length) return false;
        if (currIndex == (nums.length-1)) return true;
        boolean can = false;
        Integer key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);
        for (int i=1; i <= nums[currIndex]; i++) {
            can = canReachLastIndex(nums, currIndex+i, memo);
            memo.put(currIndex+i, can);
            if (can) return true;
        }
        return can;
    }
}

class Test {
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(new int[]{5,3,1,1,4}));
    }
}
