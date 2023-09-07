package strvr.recursion;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/subsets-ii/description/
//@strvr - https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/
public class ArraySubsetsII {
    public List<List<Integer>> subsets(int[] nums) {
        /*solution on lines of SWD's approach*/
        //return subsets(nums, new ArrayList<Integer>(), 0);

        /*Strvr's approach*/
        Arrays.sort(nums); //sort so that duplicates group together.
        List<List<Integer>> ansList = new ArrayList<>();
        subsetsStrvr(0, nums, new ArrayList<>(), ansList);
        return ansList;
    }

    private void subsetsStrvr(int ind, int[] nums, List<Integer> ds, List<List<Integer>> ansList) {
        ansList.add(new ArrayList<>(ds));
        for (int i=ind; i<nums.length;i++) {
            //pick a duplicate only if its being seen for the first time at the level.
            //check strvr again. I didn't get a lot of it, honestly :D
            if (i!=ind && nums[i] == nums[i-1]) continue;
            ds.add(nums[i]);
            subsetsStrvr(i+1, nums, ds, ansList);
            ds.remove(ds.size()-1);
        }
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
