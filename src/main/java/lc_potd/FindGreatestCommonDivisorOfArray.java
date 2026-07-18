package lc_potd;

//@link - https://leetcode.com/problems/find-greatest-common-divisor-of-array/?
public class FindGreatestCommonDivisorOfArray {
    public int findGCD(int[] nums) {
        return revise(nums);
    }

    private int revise(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num: nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
