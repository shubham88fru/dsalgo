package ptrn.subsets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/subsets/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6316228944855040
public class ArraySubsets {
    public List<List<Integer>> subsets(int[] nums) {
        /*solution on lines of SWD's approach*/
        //return subsets(nums, new ArrayList<Integer>(), 0);

        /* Standard iterative approach to generating subsets. Edctv soln */
        return subsetsIterative(nums);
    }

    /**
     * Note that this solution, like other solutions below
     * won't directly work when subset could have duplicates.
     */
    private List<List<Integer>> subsetsIterative(int[] nums) {
        /**
         An array of size n, consisting of distinct elements will have
         2^n subsets. Each binary representation of 0 to 2^n can help
         us decide which element to pick (if bit position is 1, we pick that index)
         or which to not pick. This way, we'll be able to generate all subsets of
         the array.
         */
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        //total possible subsets.
        int subsets = (int)Math.pow(2, n);
        for (int i=0; i<subsets; i++) {
            //current subset.
            List<Integer> subset = new ArrayList<>();
            for (int j=0; j < n; j++) {
                //if jth bit in binary rep of i
                //is set, then we add the jth element of
                //the array in the current subset.
                if (isBitSet(i, j)) {
                    subset.add(nums[j]);
                }
            }

            //add current subset to the ans list.
            ans.add(subset);
        }
        return ans;
    }

    private boolean isBitSet(int num, int idx) {
        return ((num & (1 << idx)) != 0);
    }

    /**
     *NOTE: There are 3 terms that appear frequently in array questions
     * and are often confusing - subarray, subset and subsequence.
     * Based on my understanding -
     * For "subarrays", you can only
     * have contiguous chunks of the og array i.e. can't use
     * the pick/notpick pattern.
     *
     * For "subsets", you don't need to pick contiguous chunks.
     * And order of elements doesn't matter i.e. two subsets
     * with same elements in different order are basically the
     * same. For subsets we can use the pick/notpick pattern.
     *
     * For "subsequence" also we don't need to pick contiguous
     * chunks but relative ordering of elements matters. i.e.
     * an element `b` appearing after the element `a` in some
     * array, must also be after it in the subsequence.
     * For subsequences also, we can use the pick/notpick pattern.
     */
    private void bt2(int[] nums, int i, List<Integer> sub, List<List<Integer>> subs) {
        subs.add(new ArrayList<>(sub));

        for (int j=i; j<nums.length; j++) {
            sub.add(nums[j]);
            bt2(nums, j+1, sub, subs);
            sub.remove(sub.size()-1);
        }
    }

    private void bt1(int[] nums, int i, List<Integer> sub, List<List<Integer>> subs) {
        if (i >= nums.length) {
            subs.add(new ArrayList<>(sub));
            return;
        }

        sub.add(nums[i]);
        bt1(nums, i+1, sub, subs);
        sub.remove(sub.size()-1);

        bt1(nums, i+1, sub, subs);
    }
}
