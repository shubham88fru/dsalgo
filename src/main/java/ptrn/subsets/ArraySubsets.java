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

    private List<List<Integer>> subsets(int[] nums, List<Integer> subset, int currIdx) {

        //When at end of array, means we've made all choices,
        //and we have a subset. So, store in array and move on.
        if (currIdx >= nums.length) {
            List<List<Integer>> subsets = new ArrayList<>();
            //Here we store a new copy (separate from original subset)
            //in the final answer, so that our backtracking (deleting)
            //doesn't effect the final answer.
            subsets.add(new ArrayList<>(subset));
            return subsets;
        }

        //Two choices -
        //1) Don't include curr el in the ans.
        List<List<Integer>> includeMeNot = subsets(nums, subset, currIdx+1);

        //2) Include the curr el in the ans.
        subset.add(nums[currIdx]);
        List<List<Integer>> includeMe = subsets(nums, subset, currIdx+1);

        //when returning from current recursive call - backtrack.
        //so that we undo the change we did to the list.
        //if we don't backtrack, the addition that we did for the `consider`
        //case will add the element to the list and since list is passed by reference
        //all subsequent calls will have that element added.
        //By backtracking, we are basically returning the list to the state it
        //was in the previous recursive call.
        subset.remove(subset.size()-1);

        //Merge answers in `includeMe` and `includeMeNot` array into one list ans return.
        List<List<Integer>> all = Stream.concat(includeMe.stream(), includeMeNot.stream())
                .collect(Collectors.toList());
        return all;
    }
}
