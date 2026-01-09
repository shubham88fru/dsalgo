package lc_potd;

//@link - https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/?
public class MaxDotProductOfTwoSubsequences {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        // return pass1(nums1, nums2);
        return pass2(nums1, nums2);
    }

    private int pass2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        Integer[][] memo = new Integer[n1+1][n2+1];
        return dp2(nums1, nums2, n1, n2, 0, 0, memo);
    }

    /**
         This was almost the same as my approach `dp()` but
         slightly clever to account for empty subsets case.
         Took hint from mik for this.
     */
    private int dp2(int[] nums1, int[] nums2, int n1, int n2, int i, int j, Integer[][] memo) {
        if (i >= n1 || j >= n2) return -99999999;

        if (memo[i][j] != null) return memo[i][j];

        int s4 = nums1[i]*nums2[j]; //just take current
        int s1 = (nums1[i]*nums2[j]) + dp2(nums1, nums2, n1, n2, i+1, j+1, memo); //take current and explore.
        int s2 = dp2(nums1, nums2, n1, n2, i+1, j, memo); //move first
        int s3 = dp2(nums1, nums2, n1, n2, i, j+1, memo); //move second.

        memo[i][j] = Math.max(s1, Math.max(s2, Math.max(s3, s4)));
        return memo[i][j];
    }

    private int pass1(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        Integer[][][] memo = new Integer[n1+1][n2+1][Math.min(n1, n2)+1];
        return dp(nums1, nums2, n1, n2, 0, 0, 0, memo);
    }

    /**
     My initial approach, works but
     give TLE because its 3d dp.
     */
    private int dp(int[] nums1, int[] nums2, int n1, int n2, int i, int j, int p, Integer[][][] memo) {
        if (i >= n1 || j >= n2) {
            if (p == 0) return -99999999; //haven't picked anything.
            return 0;
        }

        if (memo[i][j][p] != null) return memo[i][j][p];

        int s1 = (nums1[i]*nums2[j]) + dp(nums1, nums2, n1, n2, i+1, j+1, p+1, memo);
        int s2 = dp(nums1, nums2, n1, n2, i+1, j, p, memo);
        int s3 = dp(nums1, nums2, n1, n2, i, j+1, p, memo);
        int s4 = dp(nums1, nums2, n1, n2, i+1, j+1, p, memo);

        memo[i][j][p] = Math.max(s1, Math.max(s2, Math.max(s3, s4)));
        return memo[i][j][p];
    }
}
