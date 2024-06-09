package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/subarray-sums-divisible-by-k/description/
public class CountOfSubarraysWithSumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        //store the ps v/s no. of times the ps has been seen so far.
        Map<Integer, Integer> map = new HashMap<>();
        int ps = 0; //To hold the prefix sum at any given index.
        int ans = 0;
        //sum 0 appears once at index -1. Handles few edge cases.
        //Hence, 0 appears 1 times at the start.
        map.put(0, 1);
        for (int el: nums) {
            ps += el;
            //For +ve ps, this formula is same as ps%k.
            //However, simple ps%k wont give the correct ans
            //when ps is -ve. Therefore, as a rule of thumb,
            //we can always use the below formula to be safe when
            //using modulus operator.
            int modk = ((ps%k)+k)%k;

            //if modk already seen means the sum of elements
            //between the index where sum was last seen and curr index is
            //divisible by k for sure.
            if (map.containsKey(modk)) ans += map.get(modk);

            //irrespective, in each iteration, keep putting (or incrementing)
            //the count of modk.
            map.put(modk, map.getOrDefault(modk, 0)+1);
        }
        return ans;
    }
}
