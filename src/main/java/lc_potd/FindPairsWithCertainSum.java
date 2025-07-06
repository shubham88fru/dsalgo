package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/finding-pairs-with-a-certain-sum/
public class FindPairsWithCertainSum {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freq = new HashMap<>();

    /*
    * My soln. Mik had the exact same approach.
    * */
    public FindPairsWithCertainSum(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int num2: nums2) {
            freq.put(num2, freq.getOrDefault(num2, 0)+1);
        }
    }

    public void add(int index, int val) {
        freq.put(nums2[index], freq.get(nums2[index])-1);
        nums2[index] += val;
        freq.put(nums2[index], freq.getOrDefault(nums2[index], 0)+1);
    }

    public int count(int tot) {
        int count = 0;
        for (int num1: nums1) {
            if (freq.containsKey(tot-num1)) count += freq.get(tot-num1);
        }

        return count;
    }
}
