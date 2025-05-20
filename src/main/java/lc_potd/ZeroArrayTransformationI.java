package lc_potd;

//@link - https://leetcode.com/problems/zero-array-transformation-i/
public class ZeroArrayTransformationI {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        return pass1(nums, queries);
    }

    /*
        My soln. Mik had the exact same soln.
    */
    private boolean pass1(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] ps = new int[n];

        for (int[] query: queries) {
            int l = query[0];
            int r = query[1];

            ps[l] += 1; //add to start
            if (r + 1 < n) ps[r+1] -= 1; //subtract from r + 1 to adjust.
        }

        int maxDecrement = 0;
        for (int i=0; i<n; i++) {
            maxDecrement += ps[i];
            if (nums[i] > maxDecrement) return false;
        }

        return true;
    }
}
