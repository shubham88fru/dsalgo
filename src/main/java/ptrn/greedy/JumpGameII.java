package ptrn.greedy;

import java.util.Map;

//@link - https://leetcode.com/problems/jump-game-ii/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5751571070844928
//       - https://www.youtube.com/watch?v=7SBVnw7GSTk&ab_channel=takeUforward
public class JumpGameII {
    /**
     Note that the difference between jump game I and II is that
     in jump game I, we just need to check if reaching at the end
     is possible or not. While in jump game two, its guaranteed
     that reaching at the end is possible and we need to return
     the min jumps needed to do that.
     */
    public int jump(int[] nums) {
        //return minJumpsDP(nums, 0, new HashMap<Integer, Integer>());
//        return minJumpsGreedy(nums);
        return strvr(nums);
    }

    private int strvr(int[] nums) {
        int n = nums.length;

        int l = 0;
        int r = 0;
        int jumps = 0;
        while (r < n-1) {
            int maxDist = 0;
            //max dist for each range.
            while (l <= r) {
                maxDist = Math.max(maxDist, l + nums[l]);
                l += 1;
            }
            r = maxDist;
            jumps += 1;
        }

        return jumps;
    }

    //@sol - https://leetcode.com/problems/jump-game-ii/solutions/3158169/clean-codes-full-explanation-implicit-bfs-c-java-python3/
    private int minJumpsGreedy(int[] nums) {
        int farthestJump = 0;
        int currentJump = 0;
        int jumps = 0;

        for (int i=0; i<nums.length-1; i++) {
            farthestJump = Math.max(farthestJump, i+nums[i]);
            if (farthestJump == nums.length-1) {
                jumps += 1;
                break;
            }

            if (i==currentJump) {
                jumps += 1;
                currentJump = farthestJump;
            }
        }

        return jumps;
    }

    private int minJumpsDP(int[] nums, int currIndex, Map<Integer, Integer> memo) {
        if (currIndex >= nums.length) return 10001;
        if (currIndex == nums.length-1) return 0;

        int key = currIndex;
        if (memo.containsKey(key)) return memo.get(key);
        int ans = 10001;
        for (int i=1; i<=nums[currIndex]; i++) {
            int min = 1 + minJumpsDP(nums, currIndex+i, memo);
            ans = Math.min(ans, min);
        }

        memo.put(key, ans);
        return memo.get(key);
    }
}
