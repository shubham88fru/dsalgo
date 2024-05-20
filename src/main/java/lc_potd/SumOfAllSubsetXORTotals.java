package lc_potd;

//@link - https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/
public class SumOfAllSubsetXORTotals {
    public int subsetXORSum(int[] nums) {
        int[] sum = new int[1];
        // subsetsXORSum(nums, sum, 0, 0);
        subsetsXORSum1(nums, sum, 0, 0);
        return sum[0];
    }


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
