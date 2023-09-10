package strvr.recursion;

import java.util.ArrayList;
import java.util.*;

//@link - https://leetcode.com/problems/permutations/description/
//@strvr - https://takeuforward.org/data-structure/print-all-permutations-of-a-string-array/
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        /* My recursive soln. Uses extra space. */
        List<List<Integer>> combs = new ArrayList<>();
        //getPerms(nums, combs, new ArrayList<>(), new HashSet<>(), 0);
        //return combs;

        /* Striver's soln */
        getPermsOptimal(0, nums, combs);
        return combs;
    }

    //1) Optimal solution. Recursion but no extra space.
    //Striver's soln
    private void getPermsOptimal(int index, int[] nums, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> ds = new ArrayList<>();
            for (int i=0; i<nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i=index; i<nums.length; i++) {
            swap(i, index, nums);
            getPermsOptimal(index+1, nums, ans);
            swap(i, index, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    //Recursive soln. Uses extra space.
    private void getPerms(int[] nums, List<List<Integer>> ans, List<Integer> comb, Set<Integer> st, int curr) {
        if (curr >= nums.length) {
            ans.add(new ArrayList<>(comb));
            return;
        }

        for (int i=0; i<nums.length; i++) {
            if (!st.contains(nums[i])) {
                //using set for constant lookup
                //and comb for actual permutation.
                //Can't push the set directly, coz, set
                //won't store the order. It's hashed.
                st.add(nums[i]);
                comb.add(nums[i]);
                getPerms(nums, ans, comb, st, curr+1);
                st.remove(nums[i]);
                comb.remove(comb.size()-1);
            }
        }
    }
}
