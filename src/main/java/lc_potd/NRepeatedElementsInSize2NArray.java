package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/n-repeated-element-in-size-2n-array/description/?
public class NRepeatedElementsInSize2NArray {
    public int repeatedNTimes(int[] nums) {
        // return revise(nums);
        // return approach2(nums);
        return optimal(nums);
    }

    /**
     Optimal approach coded by me
     based on mik's explanation.
     */
    private int optimal(int[] nums) {
        int n = nums.length;
        for (int i=2; i<n; i++) {
            //With the given conditions,
            //it will always be such that
            //the repeating elements will get placed
            //such that either `i-1`th or `i-2`th element
            //is same.
            if (nums[i] == nums[i-1] || nums[i] == nums[i-2]) return nums[i];
        }

        return nums[0]; //edge case, first and last element will be equal e.g. [9, 5, 6, 9]
    }

    private int approach2(int[] nums) {
        int[] freq = new int[10001]; //nums[i] can be at max 10^4 per the constraints.
        for (int num: nums) {
            freq[num] += 1;
            if (freq[num] > 1) return num;
        }

        return -1;

    }

    private int revise(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num: nums) {
            if (st.contains(num)) return num;
            st.add(num);
        }

        return -1;
    }
}
