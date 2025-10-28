package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/make-array-elements-equal-to-zero/description/?
public class MakeArrayElementsEqualToZero {
    public int countValidSelections(int[] nums) {
        // return brute(nums);
        return optimal(nums);
    }

    /**
     My prefix sum soln, this wasn't
     my first intuition. But since topics
     mentioned it, I came up with this
     approach myself.
     There's an even better version of
     this code, where you don't need
     the suffix sum array.
     */
    private int optimal(int[] nums) {
        int n = nums.length;

        int[] ps = new int[n+1];
        int[] ss = new int[n+1];

        for (int i=1; i<=n; i++) {
            ps[i] = nums[i-1] + ps[i-1];
        }

        for (int i=n-1; i>=0; i--) {
            ss[i] = nums[i] + ss[i+1];
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            if (nums[i] == 0) {
                if (ps[i] == ss[i+1]) ans += 2;
                else if (Math.abs(ps[i]-ss[i+1]) == 1) ans += 1;
            }
        }

        return ans;
    }

    /**
     My brute force simulation soln.
     */
    private int brute(int[] nums) {
        int n = nums.length;

        int count = 0;
        for (int i=0; i<n; i++) {
            if (nums[i] == 0) {
                if (operate(Arrays.copyOf(nums, n + 1), i, 1)) count += 1;
                if (operate(Arrays.copyOf(nums, n + 1), i, -1)) count += 1;
            }
        }

        return count;
    }

    private boolean operate(int[] nums, int i, int dir) {
        int n = nums.length;

        int curr = i;
        while (curr >= 0 && curr < n) {
            if (nums[curr] > 0) {
                nums[curr] -= 1;
                dir *= -1;
            }
            curr += dir;
        }

        for (int num: nums) {
            if (num != 0) return false;
        }

        return true;
    }
}
