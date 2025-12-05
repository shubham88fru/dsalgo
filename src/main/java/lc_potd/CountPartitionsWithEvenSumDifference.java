package lc_potd;

//@link - https://leetcode.com/problems/count-partitions-with-even-sum-difference/description/?
public class CountPartitionsWithEvenSumDifference {
    public int countPartitions(int[] nums) {
        // return pass1(nums);
        // return pass2(nums);
        return optimal(nums);
    }

    private int optimal(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int num: nums) totalSum += num;

        /**
         `2*rs - ts` is the diff.
         since `2*rs` is always even,
         for the diff to be even as well,
         ts must be even too.
         */
        return totalSum%2==0 ? n-1: 0;
    }

    /*
        Using simple running sum.
    */
    private int pass2(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int i=0; i<n; i++) totalSum += nums[i];

        int count = 0;
        int rs = 0;
        for (int i=0; i<n-1; i++) {
            rs += nums[i];
            if (Math.abs(rs-(totalSum-rs))%2 == 0) count += 1; //`2*rs - ts`
        }

        return count;
    }

    /*
        Using prefix and suffix sum.
    */
    private int pass1(int[] nums) {
        int n = nums.length;
        int[] ps = new int[n];
        int[] ss = new int[n];

        for (int i=0; i<n; i++) {
            if (i==0) ps[0] = nums[0];
            else ps[i] = ps[i-1]+nums[i];

            if (i==0) ss[n-1] = nums[n-1];
            else ss[n-1-i] = ss[n-1-i+1] + nums[n-1-i];
        }

        int count = 0;
        for (int i=0; i<n-1; i++) {
            if (Math.abs(ps[i]-ss[i+1])%2 == 0) count += 1;
        }

        return count;
    }
}
