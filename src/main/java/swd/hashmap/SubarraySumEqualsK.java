package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/subarray-sum-equals-k/description/
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
//        return oldOptimal(nums, k);
        // return brute(nums, k);
        return optimal(nums, k);
    }

    private int optimal(int[] nums, int k) {
        int n = nums.length;

        int count = 0, ps = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);

        for (int i=0; i<n; i++) {
            ps += nums[i];
            if (mp.containsKey(ps-k)) count += mp.get(ps-k);
            mp.put(ps, mp.getOrDefault(ps, 0)+1);
        }

        return count;
    }

    private int brute(int[] nums, int k) {
        int n = nums.length;

        int count = 0;
        for (int i=0; i<n; i++) {
            int rs = 0;
            for (int j=i; j<n; j++) {
                rs += nums[j];
                if (rs == k) count += 1;
            }
        }

        return count;
    }

    private int oldOptimal(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //Prefix sum v/s Frequency.
        int ps = 0; //Prefix sum - sum all elements till curr index.
        int ans = 0; //Final ans.
        map.put(0, 1); //Sum 0 at index -1, so one occurrence.

        for (int num : nums) {
            ps += num;

            //If curr prefix sum minus k is already seen,
            //means, sums of els between curr index and the
            //prev indexes where `ps-k` sum was seen is equal to k.
            int key = ps - k;
            if (map.containsKey(key)) {
                ans += map.get(key);
            }

            //Put current prefix sum and increase frequency.
            map.put(ps, map.getOrDefault(ps, 0) + 1);
        }
        return ans;
    }
}
