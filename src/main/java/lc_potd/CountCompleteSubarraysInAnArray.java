package lc_potd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/count-complete-subarrays-in-an-array/
public class CountCompleteSubarraysInAnArray {
    public int countCompleteSubarrays(int[] nums) {
        return pass1(nums);
    }

    private int pass1(int[] nums) {
        int n = nums.length;

        Set<Integer> dis = new HashSet<>();
        for (int i=0; i<n; i++) dis.add(nums[i]);

        int disCount = dis.size();

        Map<Integer, Integer> freq = new HashMap<>();
        int l = 0;
        int r = 0;
        int count = 0;
        while (r < n) {
            freq.put(nums[r], freq.getOrDefault(nums[r], 0) + 1);

            while (freq.size() == disCount) {
                count += (n-r);
                int rem = nums[l];
                freq.put(rem, freq.get(rem)-1);
                if (freq.get(rem) == 0) freq.remove(rem);
                l += 1;
            }

            r += 1;
        }

        return count;
    }
}
