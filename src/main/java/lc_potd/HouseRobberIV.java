package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/house-robber-iv/description/
//@check - https://www.youtube.com/watch?v=YrRvP9720vY&ab_channel=codestorywithMIK
public class HouseRobberIV {
    public int minCapability(int[] nums, int k) {
        // return pass1(nums, k);
        return pass2(nums, k);
    }

    //TC: O(n*log(max_capability))
    //SC: O(1)
    private int pass2(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i=0; i<n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int l = min;
        int r = max;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r-l)/2;

            if (isPossible(nums, k, mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    /*
        This is tricky why it works.
    */
    private boolean isPossible(int[] nums, int k, int mid) {
        int robbed = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] <= mid) {
                robbed += 1;
                i += 1; //skipping adjacent house.
            }
        }

        return robbed >= k; //managed to rob atleast k houses.
    }

    //2) First intuition, took hint from mik
    //on how to solve without first storing all
    //the maxes and then finding the minimum
    //from them.
    //But gives TLE.
    private int pass1(int[] nums, int k) {
        Map<String, Integer> memo = new HashMap<>();
        return dp(nums, k, 0, memo);
    }

    private int dp(int[] nums, int k, int i, Map<String, Integer> memo) {
        /*
            Coming up with this base case was also
            a bit confusing.
        */
        if (k == 0) return 0;
        if (i >= nums.length) return Integer.MAX_VALUE;

        String key = k + "_" + i;
        if (memo.containsKey(key)) return memo.get(key);

        int rob = Math.max(nums[i], dp(nums, k-1, i+2, memo)); //note.
        int dontRob = dp(nums, k, i+1, memo);

        memo.put(key, Math.min(rob, dontRob));
        return memo.get(key);
    }
}
