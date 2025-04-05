package lc_potd;

//@link - https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/
//@check - https://www.youtube.com/watch?v=3tzP38WPAoA&ab_channel=codestorywithMIK
public class SumOfAllSubsetXORTotals {
    //Note, that the question is asking for subsets and not
    //subarrays. Two for loops will generate subarrays - not subsets.

    //The pick-notpick/recursion/backtracking should be the way to go
    //when question is about subsets or else.
    public int subsetXORSum(int[] nums) {
        int[] sum = new int[1];
        // subsetsXORSum(nums, sum, 0, 0);
        subsetsXORSum1(nums, sum, 0, 0);
        return sum[0];
    }

    //0) Using bit manipulation observation.
    //There's a very simple observation based solution
    //given in lc editorial. Mik also showed that soln but
    //didn't give an explanation on why that works.
    //If this problem is a HF problem for some company,
    //take a look at it.

    //1) Simple recursion.
    private void subsetsXORSum1(int[] nums, int[] sum, int curr, int xor) {
        if (curr >= nums.length) {
            //once subset is done.
            //add current xor to the sum.
            sum[0] += xor;
            return;
        }

        //not pick
        subsetsXORSum1(nums, sum, curr+1, xor);

        //pick - xor with current subset's xor.
        subsetsXORSum1(nums, sum, curr+1, xor^nums[curr]);
    }

    /**
     * EDIT: The soln below is non-sense. Looking
     * back at it today, I can only laugh on what i wrote :P
     *
     *
     * The actual backtracking soln for this problem is to
     * use backtracking to generate all subsets and then
     * iterate over each subset to calc xor and sum them.
     */

    //2) Recursion (also using backtracking)
    //Generate subsets - Note that as per the questions, duplicate elements
    //can form duplicate subsets and we don't have to handle any edge case
    //when there are duplicate elements in the subset.
    //While generating the subset, keep track of xor for the current subset.
    private void subsetsXORSum2(int[] nums, int[] sum, int curr, int xor) {
        if (curr >= nums.length) {
            //once subset is done.
            //add current xor to the sum.
            sum[0] += xor;
            return;
        }

        subsetsXORSum2(nums, sum, curr+1, xor);

        //xor with current subset's xor.
        xor ^= nums[curr];
        subsetsXORSum2(nums, sum, curr+1, xor);
        //bactracking, current element needs to be removed
        //from xor. A^A = 0, so to cancel out the contribution
        //of current element, xor with the current element once again.
        xor ^= nums[curr];
    }
}
