package swd.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/jump-game-ii/description/
public class JumpGameII {
    public int jump(int[] nums) {
        return minJumps(nums, 0, new HashMap<Integer, Integer>());
    }

    private int minJumps(int[] nums, int currIndex, Map<Integer, Integer> memo) {
        if (currIndex >= nums.length) return 10001;
        if (currIndex == nums.length-1) return 0;

        int maxJumps = nums[currIndex];
        int key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);
        int ans = 10001;
        for (int i=1; i<=maxJumps; i++) {
            int min = 1 + minJumps(nums, currIndex+i, memo);
            ans = Math.min(ans, min);
            //return ans;
        }

        memo.put(key, ans);
        return memo.get(key);
    }
}
