package ptrn.subsets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/combination-sum/description/
//@strvr - https://takeuforward.org/data-structure/combination-sum-1/
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return possibleCombinations(candidates, target, 0, new ArrayList<>());
    }

    private List<List<Integer>> possibleCombinations(int[] candidates, int target, int currIndex, List<Integer> comb) {

        //If a valid combination found, return it.
        if (target == 0) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>(comb));
            return ans;
        } else if (target < 0) return null;

        //If no valid combination found and we've exhausted
        //the array.
        if (currIndex >= candidates.length) return  null;

        //Consider the current element of array in
        //the subset. Since same element can be
        //included in subset multiple times, we
        //dont increment the index in this recursive call.
        List<List<Integer>> includeMe = null;
        int currCandidate = candidates[currIndex];
        if (currCandidate <= target) {
            comb.add(currCandidate);
            includeMe = possibleCombinations(candidates, target-currCandidate, currIndex, comb);

            //once done, back track.
            comb.remove(comb.size()-1);
        }

        //Second option, don't include the curr element in the subset.
        List<List<Integer>> includeMeNot = possibleCombinations(candidates, target, currIndex+1, comb);

        //Merge the result and return.
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
