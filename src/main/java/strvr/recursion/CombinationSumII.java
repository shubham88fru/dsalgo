package strvr.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@link - https://leetcode.com/problems/combination-sum-ii/description/
//@strvr - https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /* kinda 0-1 knapsack. */
        // return possibleCombinationsSWD(candidates, target, 0,new ArrayList<>(), new HashMap<String, Integer>());

        /* Striver's soln. */
        //sort the array so that similar element's are together.
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        possibleCombinationsStrvr(candidates, ans, new ArrayList<>(), 0, target);
        return ans;
    }


    //Strvr's soln. WIth
    private void possibleCombinationsStrvr(int[] candidates, List<List<Integer>> ans, List<Integer> comb, int currIndx, int target) {
        if (target == 0) {
            ans.add(new ArrayList<>(comb));
            return;
        }


        for (int i=currIndx; i<candidates.length; i++) {
            //Array is sorted. At the same array position, if we are encoutnering
            //same element again (second time onwards), we won't pick it up since
            //we have already considered it once.
            if (i>currIndx && candidates[i] == candidates[i-1]) continue;

            //optimization - since array is sorted, if curr is greater than target,
            //no point checking further. All others will also be greater.
            if (candidates[i] > target) break;

            //select and move to next position of array.
            comb.add(candidates[i]);
            possibleCombinationsStrvr(candidates, ans, comb, i+1, target-candidates[i]);

            //backtrack.
            comb.remove(comb.size()-1);
        }
    }

    //Gives TLE.
    private List<List<Integer>> possibleCombinationsSWD(int[] candidates, int target, int currIndex, List<Integer> comb, Map<String, Integer> seenmap) {

        if (target == 0) {
            List<Integer> combCopy = new ArrayList<>(comb);
            Collections.sort(combCopy);
            String combStr = combCopy.toString();
            if (!seenmap.containsKey(combStr)) {
                seenmap.put(combStr, 1);
                List<List<Integer>> ans = new ArrayList<>();
                ans.add(new ArrayList<>(combCopy));
                return ans;
            }
            return null;
        } else if (target < 0) return null;

        if (currIndex >= candidates.length) return  null;

        int currCandidate = candidates[currIndex];
        comb.add(currCandidate);
        List<List<Integer>> includeMe = possibleCombinationsSWD(candidates, target-currCandidate, currIndex+1, comb, seenmap);
        comb.remove(comb.size()-1);

        List<List<Integer>> includeMeNot = possibleCombinationsSWD(candidates, target, currIndex+1, comb, seenmap);

        List<List<Integer>> all = null;

        if (includeMe != null && includeMeNot != null) {
            return Stream.concat(includeMe.stream(), includeMeNot.stream())
                    .collect(Collectors.toList());
        } else if (includeMe != null) {
            return includeMe;
        } else if (includeMeNot != null) {
            return includeMeNot;
        } else {
            return new ArrayList<>();
        }

    }
}
