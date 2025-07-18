package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/description/
//@check - https://www.youtube.com/watch?v=g38YHwee1Yo&ab_channel=codestorywithMIK
public class FindMaximumLengthOfValidSubsequenceII {
    public int maximumLength(int[] nums, int k) {
        // return pass1(nums, k);
        // return pass2(nums, k);
        // return pass4(nums, k);
        return pass5(nums, k);
    }

    private int pass5(int[] nums, int k) {
        int n = nums.length;
        return dp4(nums, k);
    }

    private int dp4(int[] nums, int k) {
        int n = nums.length;

        int[][] dps = new int[k][n];
        for (int i=0; i<k; i++) {
            Arrays.fill(dps[i], 1);
        }

        int ans = 1;
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                int mod = (nums[i]+nums[j])%k;
                dps[mod][i] = Math.max(dps[mod][i], dps[mod][j]+1);
                ans = Math.max(ans, dps[mod][i]);
            }
        }

        return ans;
    }

    private int pass1(int[] nums, int k) {
        int n = nums.length;
        Integer[][][] memo = new Integer[n+1][n+1][n+1];
        return dp(nums, -1, -1, 0, k, memo);
    }

    private int dp(int[] nums, int pi, int ppi, int i, int k, Integer[][][] memo) {

        if (i >= nums.length) return 0;

        if (pi != -1 && ppi != -1 && memo[i][pi][ppi] != null) return memo[i][pi][ppi];

        int pick = 0;
        if (pi != -1 && ppi != -1) {
            if ((nums[pi]+nums[ppi])%k == (nums[pi]+nums[i])%k) {
                pick += (1 + dp(nums, i, pi, i+1, k, memo));
            }
        } else if (pi != -1) {
            pick += (2 + dp(nums, i, pi, i+1, k, memo));
        } else {
            pick += dp(nums, i, pi, i+1, k, memo);
        }


        int npick = dp(nums, pi, ppi, i+1, k, memo);

        if (pi != -1 && ppi != -1) {
            memo[i][pi][ppi] = Math.max(pick, npick);
        }
        return Math.max(pick, npick);
    }

    /*
        Interesting 2d dp approach, very similar
        to my 3d dp approach with a clever trick.
        Fix the result of mod (int this 0 or 1) and
        runt the dp twice. This removes the need to track
        past 2 variables.
        Took this hint from mik.
    */
    private int pass2(int[] nums, int k) {
        int n = nums.length;

        int max = 0;
        for (int i=0; i<k; i++) {
            max = Math.max(max, dp2(nums, -1, 0, i, k, new Integer[n+1][n+1]));
        }
        return max;
    }

    private int dp2(int[] nums, int pi, int i, int target, int k, Integer[][] memo) {

        if (i >= nums.length) return 0;

        if (pi != -1 && memo[i][pi] != null) return memo[i][pi];

        int pick = 0;
        if (pi != -1) {
            if ((nums[pi]+nums[i])%k == target) {
                pick += (1 + dp2(nums, i, i+1, target, k, memo));
            }
        } else {
            pick += (1 + dp2(nums, i, i+1, target, k, memo));
        }


        int npick = dp2(nums, pi, i+1, target, k, memo);

        if (pi != -1) {
            memo[i][pi] = Math.max(pick, npick);
        }
        return Math.max(pick, npick);
    }

    /*
        Using bottom-up appraoch.
        LIS variant. Still TLE.
    */
    private int pass4(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for (int i=0; i<k; i++) {
            max = Math.max(max, dp3(nums, i, k));
        }
        return max;
    }

    private int dp3(int[] nums, int target, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int ans = 1;
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if ((nums[i]+nums[j])%k == target) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        return ans;
    }
}
