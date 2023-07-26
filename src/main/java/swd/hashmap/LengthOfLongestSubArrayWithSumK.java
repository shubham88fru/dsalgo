package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
public class LengthOfLongestSubArrayWithSumK {
    public static int lenOfLongSubarr (int[] nums, int N, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //Prefix sum v/s index.
        int ps = 0; //Prefix sum - sum all elements till curr index.
        int ans = 0; //Final ans.
        map.put(0, -1); //Sum 0 at index -1.

        for (int i=0; i<nums.length; i++) {
            ps += nums[i];

            //If curr prefix sum minus k is already seen,
            //means, sums of els between curr index and the
            //prev indexes where `ps-k` sum was seen is equal to k.
            int key = ps - k;
            if (map.containsKey(key)) {
                ans = Math.max(ans, i-map.get(key)); //need to find the largest sub-array.
            }

            //since we need to find the largest sub-array,
            //we're interested in putting a prefix sum in map
            //only the first time it is seen.
            if (!map.containsKey(ps)) {
                map.put(ps, i);
            }

        }
        return ans;
    }
}
