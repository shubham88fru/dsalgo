package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/description/
public class MaximumUniqueSubarraySumAfterDeletion {
    public int maxSum(int[] nums) {
        // return wrong(nums);
        // return suboptimal(nums);
        return optimal(nums);
    }

    private int optimal(int[] nums) {
        int n = nums.length;
        int largestNegative = Integer.MIN_VALUE;
        for (int num: nums) {
            if (num < 0) {
                largestNegative = Math.max(largestNegative, num);
            }
        }

        Set<Integer> st = new HashSet<>();
        int maxSum = 0;
        for (int num: nums) {
            if (num > 0 && !st.contains(num)) maxSum += num;
            st.add(num);
        }

        if (maxSum == 0 && !st.contains(0)) return largestNegative;

        return maxSum;

    }

    private int suboptimal(int[] nums) {
        int n = nums.length;
        int largestNegative = Integer.MIN_VALUE;
        List<Integer> positives = new ArrayList<>();
        for (int num: nums) {
            if (num < 0) {
                largestNegative = Math.max(largestNegative, num);
            } else positives.add(num);
        }

        if (positives.size() == 0) return largestNegative;

        Set<Integer> st = new HashSet<>();
        int maxSum = 0;
        for (int num: positives) {
            if (!st.contains(num)) maxSum += num;
            st.add(num);
        }


        return maxSum;
    }

    /*
        Wrong because we don't really need to
        move the window because we found a duplicate.
        We can include all the +ve by simply deleting the
        duplicates.
    */
    private int wrong(int[] nums) {
        int n = nums.length;
        int largestNegative = Integer.MIN_VALUE;
        List<Integer> positives = new ArrayList<>();
        for (int num: nums) {
            if (num < 0) {
                largestNegative = Math.max(largestNegative, num);
            } else positives.add(num);
        }

        if (positives.size() == 0) return largestNegative;

        System.out.println(positives);

        Map<Integer, Integer> freq = new HashMap<>();
        int maxSum = 0;
        int sum = 0;
        int l = 0;
        int r = 0;
        n = positives.size();
        while (r < n) {
            while (r < n && !freq.containsKey(positives.get(r))) {
                sum += positives.get(r);
                maxSum = Math.max(sum, maxSum);
                freq.put(positives.get(r), freq.getOrDefault(positives.get(r), 0)+1);
                r += 1;
            }

            int rem = positives.get(l);
            freq.put(rem, freq.get(rem)-1);
            if (freq.get(rem) <= 0) freq.remove(rem);
            sum -= rem;
            l += 1;
        }

        return maxSum;
    }
}
