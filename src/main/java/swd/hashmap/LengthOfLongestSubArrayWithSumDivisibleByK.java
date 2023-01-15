package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
public class LengthOfLongestSubArrayWithSumDivisibleByK {
    int longSubarrWthSumDivByK(int[] nums, int n, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ps = 0; //To hold the prefix sum at any given index.
        int ans = 0;
        map.put(0, -1); //sum 0 appears once at index -1. Handles few edge cases.
        for (int i=0; i<nums.length; i++) {
            ps += nums[i];
            //For +ve ps, this formula is same as ps%k.
            //However, simple ps%k wont give the correct ans
            //when ps is -ve. Therefore, as a rule of thumb,
            //we can always use the below formula to be safe when
            //using modulus operator.
            int modk = ((ps%k)+k)%k;

            //if modk already seen means the sum of elements
            //between the index where sum was last seen and curr index is
            //divisible by k for sure.
            if (map.containsKey(modk)) {
                ans = Math.max(ans, i-map.get(modk));
            }

            if (!map.containsKey(modk)) {
                map.put(modk, i);
            }
        }
        return ans;
    }
}
