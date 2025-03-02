package lc_potd;

//@link - https://leetcode.com/problems/merge-triplets-to-form-target-triplet/description/
//@check - https://www.youtube.com/watch?v=kShkQLQZ9K4&t=195s&ab_channel=NeetCode
public class MergeTripletsToFormTargetTriplet {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        return ncapproach(triplets, target);
    }
    /*
    * I was almost there, yet couldn't solve it completely by myself.
    * Had to take a small hint from nc.
    * Below soln is coded by me but approach by nc.
    * */
    private boolean ncapproach(int[][] triplets, int[] target) {
        int n = triplets.length;
        boolean fFound = false;
        boolean sFound = false;
        boolean tFound = false;

        for (int[] triplet: triplets) {
            int f = triplet[0];
            int s = triplet[1];
            int t = triplet[2];

            //if any triplets has any value greater than than the target
            //then we won't even bother considering merging it with any
            //other triplet. This is because, doing so will only make the
            //value in the resultant greater than what is needed.
            if (f > target[0] || s > target[1] || t > target[2]) continue;

            if (f == target[0]) fFound = true;
            if (s == target[1]) sFound = true;
            if (t == target[2]) tFound = true;

            if (fFound && sFound && tFound) return true;
        }

        //If we found each element at the corresponding
        //indexes in some triplet, this means we can form the
        //resultant.
        return (fFound && sFound && tFound);
    }
}
