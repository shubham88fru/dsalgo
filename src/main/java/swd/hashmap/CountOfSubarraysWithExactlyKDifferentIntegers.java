package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/subarrays-with-k-different-integers/description/
public class CountOfSubarraysWithExactlyKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        //subarrays with exactly k distinct elements will be
        //nothing but num of subarrays with atmost `k` unique elements
        //minus num of subarrays with atmost `k-1` unique elements.
        return atmostKUnique(nums, k) - atmostKUnique(nums, k-1);
    }

    //Algorithm to find count of subarrays with atmost
    //k unique elements. (Using sliding window)
    private int atmostKUnique(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int release = 0;
        int acquire = 0;
        int ans = 0;

        while (release <= acquire && acquire < nums.length) {
            int c = nums[acquire];
            if (map.containsKey(c) || (map.size() < k)) {
                map.put(c, map.getOrDefault(c, 0)+1);
                ans += (acquire - release + 1);
                acquire++;
            } else {
                int deleteChar = nums[release];
                map.put(deleteChar, map.getOrDefault(deleteChar, 1)-1);
                if (Objects.equals(map.get(deleteChar), 0)) {
                    map.remove(deleteChar);
                }
                release++;
            }
        }

        return ans;
    }
}
