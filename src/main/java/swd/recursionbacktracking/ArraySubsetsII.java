package swd.recursionbacktracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/subsets-ii/description/
public class ArraySubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return subsets(nums, new ArrayList<Integer>(), 0, new HashMap<String, Integer>());
    }

    private List<List<Integer>> subsets(int[] nums, List<Integer> subset, int currIdx, Map<String, Integer> seenmap) {

        //When at end of array, means we've made all choices,
        //and we have a subset. So, store in array and move on.
        if (currIdx >= nums.length) {
            List<List<Integer>> subsets = new ArrayList<>();

            //sort the current subset, stringify it and store in
            //map, so that if the same subset has already been added
            //previously in the answer, we don't add it again.
            //Note that, sorting the copy (and not the original) of list
            //itself is neccessary, because sorting modifies the orginal
            //list thereby changing the position of elements in the original
            //list, which will cause problems when we are backtracking and
            //removing the last added element.
            List<Integer> subsetCopy = new ArrayList<>(subset);
            Collections.sort(subsetCopy);
            String subsetCopyStr = subsetCopy.toString();
            if (!seenmap.containsKey(subsetCopyStr)) {
                //Here we store a new copy (seperate from original subset)
                //in the final answer, so that our backtracking (deleting)
                //doesn't effect the final answer.
                subsets.add(subsetCopy);
                seenmap.put(subsetCopyStr, 1);
                return subsets;
            }

            return null;
        }

        //Two choices -
        //1) Don't include curr el in the ans.
        List<List<Integer>> includeMeNot = subsets(nums, subset, currIdx+1, seenmap);

        //2) Include the curr el in the ans.
        subset.add(nums[currIdx]);
        List<List<Integer>> includeMe = subsets(nums, subset, currIdx+1, seenmap);

        //when returning from current recursive call - backtrack.
        //so that we undo the the change we did to the list.
        //if we don't backtrack, the addition that we did for the consider
        //case will add the elemnt to the list and since list is passed by reference
        //all subsequent calls will had that element added.
        //By backtracking, we are basically returning the list to the state it
        //was in the previous recursive call.
        subset.remove(subset.size()-1);

        //Merge answers in `includeMe` and `includeMeNot` array into one list ans return.
        List<List<Integer>> all = null;
        if (includeMe != null && includeMeNot != null) {
            all = Stream.concat(includeMe.stream(), includeMeNot.stream())
                    .collect(Collectors.toList());
        } else if (includeMe != null) {
            all = includeMe;
        } else if (includeMeNot != null) {
            all = includeMeNot;
        } else {
            all = new ArrayList<>();
        }
        return all;
    }
}
