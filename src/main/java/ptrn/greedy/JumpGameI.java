package ptrn.greedy;

import java.util.Map;

//@link - https://leetcode.com/problems/jump-game/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6521326786576384
public class JumpGameI {
    public boolean canJump(int[] nums) {
        //return canReachLastIndexDP(nums, 0, new HashMap<Integer, Boolean>());
        return canReachLastIndexGreedy(nums);
    }

    private boolean canReachLastIndexGreedy(int[] nums) {
        //set the last element in the array as our
        //initial target.
        int targetIndex = nums.length - 1;

        //Traverse the array from the end-1 to the first
        //element in the array.
        for (int j=nums.length-2; j >= 0; j--) {
            //If the target is reachable from current index,
            //we are sure that if we can reach current index, then
            //we can reach the target. So, update target to current
            //and move on to check if we can now reach this new target, ans so on.
            if (nums[j] >= (targetIndex-j)) {
                targetIndex = j;
            }
        }

        //If by the end of the process, our target
        //becomes index 0, i.e. where the person has to start
        //from, it means we can reach the og target i.e. the last index.
        if (targetIndex == 0) return true;

        //otherwise, we must have found some index
        //while traversing, we got stuck.
        return false;
    }

    private boolean canReachLastIndexDP(int[] nums, int currIndex, Map<Integer, Boolean> memo) {
        if (currIndex >= (nums.length-1)) return true;
        Integer key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);
        for (int i=nums[currIndex]; i>=1; i--) {
            if (canReachLastIndexDP(nums, currIndex+i, memo)) {
                //can reach last index from `key+i` index
                //and so obviously from `key` as well, coz
                //we can reach from `key` to `key+i`.
                memo.put(key+i, true);
                memo.put(key, true);
                return true;
            }
        }

        //if tried all jumps from
        //the location and still here,
        //means cant reach last index from curr index.
        memo.put(key, false);
        return false;
    }
}
