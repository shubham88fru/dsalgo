package swd.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/subsets/description/
public class ArraySubsets {
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, new ArrayList<Integer>(), 0);
    }

    private List<List<Integer>> subsets(int[] nums, List<Integer> subset, int currIdx) {

        //When at end of array, means we've made all choices,
        //and we have a subset. So, store in array and move on.
        if (currIdx >= nums.length) {
            List<List<Integer>> subsets = new ArrayList<>();
            //Here we store a new copy (seperate from original subset)
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
        //so that we undo the the change we did to the list.
        //if we don't backtrack, the addition that we did for the consider
        //case will add the elemnt to the list and since list is passed by reference
        //all subsequent calls will had that element added.
        //By backtracking, we are basically returning the list to the state it
        //was in the previous recursive call.
        subset.remove(subset.size()-1);

        //Merge answers in `includeMe` and `includeMeNot` array into one list ans return.
        List<List<Integer>> all = Stream.concat(includeMe.stream(), includeMeNot.stream())
                .collect(Collectors.toList());
        return all;
    }
}
