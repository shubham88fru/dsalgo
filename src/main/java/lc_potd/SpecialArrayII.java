package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/special-array-ii/
//@check - https://www.youtube.com/watch?v=powGdCI0afg&t=1426s&ab_channel=codestorywithMIK
public class SpecialArrayII {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        // return brute(nums, queries);
        return prefixApproachMik(nums, queries);
    }

    /*
        I couldn't come up with this optimal approach.
        Mik gave a hint.
     */
    private boolean[] prefixApproachMik(int[] nums, int[][] queries) {
        int n = queries.length;
        int m = nums.length;
        boolean[] ans = new boolean[n];
        int[] badCnt = new int[m];

        //Populate a prefix array storing such that
        //each index represents the count of invalid
        //pairs seen from start of array to that index
        //of the array.
        for (int i=1; i<m; i++) {
            if ((nums[i] + nums[i-1])%2 == 0) badCnt[i] = badCnt[i-1] + 1;
            else badCnt[i] = badCnt[i-1];
        }

        //Then iterate over each query.
        //If (badCnt[end]-badCnt[start]) == 0 means the
        //current range didn't add any extra mismatches.
        for (int i=0; i<n; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            if ((badCnt[end]-badCnt[start]) == 0) ans[i] = true;
        }

        return ans;
    }

    //brute force - TLE
    private boolean[] brute(int[] nums, int[][] queries) {
        int n = queries.length;
        boolean[] ans = new boolean[n];
        Arrays.fill(ans, true);
        for (int j=0; j<n; j++) {
            int start = queries[j][0];
            int end = queries[j][1];

            for (int i=start; i<end; i++) {
                //sum of of and even is odd.
                if ((nums[i] + nums[i+1])%2 == 0) {
                    ans[j] = false;
                    break;
                }
            }
        }

        return ans;
    }
}
