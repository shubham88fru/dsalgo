package strvr.recursion;

import java.util.ArrayList;

//@link - https://practice.geeksforgeeks.org/problems/subset-sums2234/1
//@strvr - https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
public class SubsetSums {
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        ArrayList<Integer> ans = new ArrayList<>();
        generateAllSubsetsAndSumThem(arr, ans, 0, N, 0);
        return ans;
    }

    //T: O(2^N), S: O(1)
    //recursive soln to generate all subsets (pick/not-pick)
    //and in process keep summing the elements of the array.
    private void generateAllSubsetsAndSumThem(ArrayList<Integer> al, ArrayList<Integer> ans,
                                              int i, int N, int sum) {

        //if out of bounds, means no more
        //element can be added to the subset.
        //Sum will be whatever elements we have rt now.
        if (i>=N) {
            ans.add(sum);
            return;
        }

        //pick curr element (therefore, its sum will contribute to sum of subset)
        //NOTE: i --> i+1
        //Even if we pick the curr element, we'll move to next ofcourse.
        //the only diff between pick and not pick is that whether we add the
        //curr element to sum or not.
        generateAllSubsetsAndSumThem(al, ans, i+1, N, sum+al.get(i));

        //don't pick the curr element (therefore, its sum won't contribute to sum of subset)
        //NOTE: i --> i+1
        generateAllSubsetsAndSumThem(al, ans, i+1, N, sum);
    }
}
