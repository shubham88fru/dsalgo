package strvr.recursion;

import java.util.ArrayList;
import java.util.List;
 /*
  DOESN'T WORK - NEED TO FIX.
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> combs = new ArrayList<>();
        numCombs(0, nums, combs, new ArrayList<>(), target);
        return combs.size();
    }

    private void numCombs(int curr, int[] nums, List<List<Integer>> combs, List<Integer> comb, int target) {
        if (target == 0) {
            combs.add(new ArrayList<>(comb));
            return;
        }

        if (curr >= nums.length) return;

        if (nums[curr] <= target) {
            comb.add(nums[curr]);
            numCombs(curr, nums, combs, comb, target-nums[curr]);
            comb.remove(comb.size()-1);
        }

        numCombs(curr+1, nums, combs, comb, target);
    }
}
