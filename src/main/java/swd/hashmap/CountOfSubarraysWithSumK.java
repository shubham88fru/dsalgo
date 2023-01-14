package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/subarray-sum-equals-k/description/
public class CountOfSubarraysWithSumK {
    public int subarraySum(int[] nums, int k) {
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

            //Put current prefix sum and increase frequence.
            map.put(ps, map.getOrDefault(ps, 0) + 1);
        }
        return ans;
    }
}
