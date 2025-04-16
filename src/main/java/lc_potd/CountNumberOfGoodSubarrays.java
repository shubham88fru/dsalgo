package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/count-the-number-of-good-subarrays/description
public class CountNumberOfGoodSubarrays {
    public long countGood(int[] nums, int k) {
        return pass1(nums, k);
    }

    /*
    * My soln, mik had the same soln.
    * */
    private long pass1(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freqs = new HashMap<>();

        int l = 0;
        int r = 0;
        long pairs = 0;
        long ans = 0;
        while (r < n) {
            pairs += (long)freqs.getOrDefault(nums[r], 0);
            freqs.put(nums[r], freqs.getOrDefault(nums[r], 0)+1);

            while (pairs >= k) {
                int freq = freqs.get(nums[l]);
                if (freq > 1) pairs -= (long)(freq-1);
                freqs.put(nums[l], freqs.get(nums[l])-1);
                if (freqs.get(nums[l]) == 0) freqs.remove(nums[l]);
                ans += (long)(n-r);
                l += 1;
            }



            r += 1;
        }

        return ans;
    }
}
