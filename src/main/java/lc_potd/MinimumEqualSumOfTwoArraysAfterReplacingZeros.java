package lc_potd;

//@link - https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/description/
public class MinimumEqualSumOfTwoArraysAfterReplacingZeros {
    public long minSum(int[] nums1, int[] nums2) {
        return pass1(nums1, nums2);
    }

    /* *
        My approach.
        Mik also showed a greedy sol on similar lines but
        shorter code.
     */
    private long pass1(int[] nums1, int[] nums2) {
        long sum1 = 0;
        long sum2 = 0;
        int zc1 = 0;
        int zc2 = 0;

        for (int num: nums1) {
            sum1 += num;
            if (num == 0) zc1 += 1;
        }

        for (int num: nums2) {
            sum2 += num;
            if (num == 0) zc2 += 1;
        }

        long ans = Math.max(sum1 + zc1, sum2 + zc2); //min possible equal sum

        if (zc1 == 0) {
            if (ans != sum1) return -1;
        }

        if (zc2 == 0) {
            if (ans != sum2) return -1;
        }


        long diff1 = ans - sum1;
        long diff2 = ans - sum2;
        if (diff1 >= zc1 && diff2 >= zc2) return ans; //replaceable

        return -1;
    }
}
