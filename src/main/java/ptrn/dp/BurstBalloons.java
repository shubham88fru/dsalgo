package ptrn.dp;

//@link - https://leetcode.com/problems/burst-balloons/
//@check - https://www.youtube.com/watch?v=Yz4LlDSlkns&ab_channel=takeUforward
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        // return pass1(nums);
        return strvrsoln(nums);
    }

    /*
    * Coded by me based on strvr's explanation.
    * Understood partially.
    * Strvr also showed bottom up approach.
    *  */
    private int strvrsoln(int[] nums) {
        int n = nums.length;
        int[] padded = new int[n+2];
        padded[0] = 1;
        padded[n+1] = 1;
        for (int i=0; i<n; i++) {
            padded[i+1] = nums[i];
        }
        Integer[][] dp = new Integer[n+2][n+2];

        return topdownstrvr(padded, 1, n, dp);
    }

    private int topdownstrvr(int[] padded, int i, int j, Integer[][] dp) {
        if (i > j) return 0;

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int max = Integer.MIN_VALUE;
        for (int idx=i; idx<=j; idx++) {
            int curr = padded[i-1]*padded[idx]*padded[j+1] +
                    topdownstrvr(padded, i, idx-1, dp)
                    + topdownstrvr(padded, idx+1, j, dp);

            max = Math.max(max, curr);
        }

        dp[i][j] = max;
        return max;
    }

    private int pass1(int[] nums) {
        int n = nums.length;

        return dp(nums, n, 0, -1);
    }

    //my ususal approach - WRONG - won't work.
    private int dp(int[] nums, int n, int i, int pi) {
        if (i >= n) return 0;

        int burst = 0;

        if (i == 0) {
            burst = nums[i]*nums[i+1] + dp(nums, n, i+1, pi);
        } else if (i==n-1) {
            burst = nums[pi]*nums[i] + dp(nums, n, i+1, pi);
        } else {
            burst = (nums[pi]*nums[i]*nums[i+1]) + dp(nums, n, i+1, pi);
        }

        int dontBurst = dp(nums, n, i+1, i);

        return Math.max(burst, dontBurst);
    }
}
