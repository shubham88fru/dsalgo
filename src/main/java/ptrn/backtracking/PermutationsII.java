package ptrn.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/permutations-ii/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6404650443210752
public class PermutationsII {
    /**
     This is a nasty solution, on similar lines to my version of
     solution for permutation problem.

     If this problem is a recurring problems for some company,
     do check online for an optimal soln.
     */
    Set<String> glob = new HashSet<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> combs = new ArrayList<>();
        getPerms(nums, combs, new ArrayList<>(), new HashSet<>(), 0);
        return combs;
    }

    //Recursive soln. Uses extra space.
    private void getPerms(int[] nums, List<List<Integer>> ans, List<Integer> comb, Set<String> st, int curr) {
        if (curr >= nums.length) {
            if (!glob.contains(comb.toString()))
                ans.add(new ArrayList<>(comb));
            glob.add(comb.toString());

            return;
        }

        for (int i=0; i<nums.length; i++) {
            if (!st.contains(nums[i] + "-" + i)) {
                //using set for constant lookup
                //and comb for actual permutation.
                //Can't push the set directly, coz, set
                //won't store the order. It's hashed.
                st.add(nums[i]+"-"+i);
                comb.add(nums[i]);
                getPerms(nums, ans, comb, st, curr+1);
                st.remove(nums[i]+"-"+i);
                comb.remove(comb.size()-1);
            }
        }
    }
}
