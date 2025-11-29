package lc_potd;

//@link - https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/description/
public class MinimumOperationsToMakeArraySumDivisibleByK {
    public int minOperations(int[] nums, int k) {
        // return pass1(nums, k);
        return pass2(nums, k);
    }

    private int pass1(int[] nums, int k) {
        int sum = 0;
        for (int num: nums) sum += num;

        return sum%k;
    }

    private int pass2(int[] nums, int k) {
        int sum = 0;
        for (int num: nums) sum = (sum + num)%k; //take continuous mod.

        return sum;
    }
}
