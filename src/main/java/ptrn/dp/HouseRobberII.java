package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/house-robber-ii/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5613951213371392
public class HouseRobberII {
    public int rob(int[] nums) {
        return topdowndp(nums);
    }

    //1) Top down dp solution.
    private int topdowndp(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(
                /**
                 Divide the houses in two sets. The first set, doesn't include
                 the first house and second set doesn't include the last house.
                 */
                //when starting at zero, can only rob till last but one house,
                //coz, the last house's next is the first house.
                houserobber2(nums, 0, nums.length-1, new HashMap<Integer, Integer>()),
                houserobber2(nums, 1, nums.length, new HashMap<Integer, Integer>())
        );
    }

    private int houserobber2(int[] nums, int currIndex, int last, Map<Integer, Integer> memo) {

        if (currIndex >= last) return 0;

        int key = currIndex ;
        if (memo.containsKey(key)) return memo.get(key);

        int robCurr = robCurr = nums[currIndex] + houserobber2(nums, (currIndex+2),last, memo);

        int dontRobCurr = houserobber2(nums, (currIndex+1), last, memo);

        memo.put(key, Math.max(robCurr, dontRobCurr));
        return memo.get(key);
    }

    //2) Check edctv for bottom up solution.
}
