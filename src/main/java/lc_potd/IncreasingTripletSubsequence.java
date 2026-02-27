package lc_potd;

//@link - https://leetcode.com/problems/increasing-triplet-subsequence
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        // return brute(nums);
        return optimal(nums);
    }

    /*
        1. Brute
    */
    //3 loops.

    /*
        2. Coded by me based on LC's
        editorial.
    */
    private boolean optimal(int[] nums) {
        int f=Integer.MAX_VALUE, s=Integer.MAX_VALUE;
        for (int n: nums) {
            if (n <= f) f = n;
            else if (n <= s) s = n;
            else return true;
        }
        return false;
    }
}
