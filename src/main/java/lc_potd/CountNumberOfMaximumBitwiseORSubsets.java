package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
//@check - https://www.youtube.com/watch?v=DQOgVNI6BcE&t=1025s&ab_channel=codestorywithMIK
public class CountNumberOfMaximumBitwiseORSubsets {
    public int countMaxOrSubsets(int[] nums) {
        //Max `or` in an array of integers will
        //always be obtained by or'ing all the elements
        //of the array.
        int maxOr = 0;
        for (int i=0; i<nums.length; i++) {
            maxOr |= nums[i];
        }

        // backtracking.
        // int[] cnt = {0};
        // count(nums, 0, 0, cnt, new ArrayList<>(), new HashSet<>(), maxOr);
        // return cnt[0];

        //dp - memoization.
        return countMemo(nums, 0, 0, maxOr, new HashMap<>());
    }

    /*
    * My dp.
    * */
    private int revise(int[] nums) {
        int mor = 0;
        for (int num: nums) mor |= num;
        Map<String, Integer> memo = new HashMap<>();
        return dp(nums, 0, 0, mor, memo);
    }

    private int dp(int[] nums, int i, int curr, int mor, Map<String, Integer> memo) {
        if (i >= nums.length) {
            if (curr == mor) return 1;
            return 0;
        }

        if (curr > mor) return 0;

        String key = i + "_" + curr;
        if (memo.containsKey(key)) return memo.get(key);

        int pick = 0;
        int npick = 0;
        pick += dp(nums, i+1, curr|nums[i], mor, memo);
        npick += dp(nums, i+1, curr, mor, memo);

        memo.put(key, pick+npick);
        return memo.get(key);
    }

    //Dp - memoization soln. (Inspired by mik)
    private int countMemo(int[] nums, int curr, int or, int maxOr, Map<String, Integer> memo) {
        if (curr >= nums.length) {
            if (or == maxOr) return 1;
            return 0;
        }


        String key = curr + "_" + or;
        if (memo.containsKey(key)) return memo.get(key);

        int pick = countMemo(nums, curr+1, (or|nums[curr]), maxOr, memo);

        int notPick = countMemo(nums, curr+1, or, maxOr, memo);

        memo.put(key, pick + notPick);
        return memo.get(key);
    }

    //Backtracking soln - my soln.
    private void count(int[] nums, int curr, int or, int[] cnt, List<Integer> chosen, Set<String> cache, int maxOr) {
        if (or == maxOr) {
            List<Integer> lst = new ArrayList<>(chosen);
            Collections.sort(lst);
            String key = lst.toString();
            if (!cache.contains(key)) {
                cnt[0] += 1;
                cache.add(key);
            }
        }
        if (curr >= nums.length) return;


        chosen.add(curr);
        count(nums, curr+1, (or|nums[curr]), cnt, chosen, cache, maxOr);
        chosen.remove(chosen.size()-1);


        count(nums, curr+1, or, cnt, chosen, cache, maxOr);
    }
}
