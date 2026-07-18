package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/?
public class SumOfGCDOfFormedPairs {
    public long gcdSum(int[] nums) {
        return sim(nums);
    }

    private long sim(int[] nums) {
        int n = nums.length;
        int mx = Integer.MIN_VALUE;

        int[] gcds = new int[n];
        for (int i=0; i<n; i++) {
            mx = Math.max(mx, nums[i]);
            gcds[i] = gcd(mx, nums[i]);
        }

        Arrays.sort(gcds);
        long sum = 0;
        int l=0, r=n-1;
        while (l < r) {
            sum += gcd(gcds[l], gcds[r]);
            l += 1;
            r -= 1;
        }

        return sum;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
