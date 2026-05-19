package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/minimum-common-value/
public class MinimumCommonValue {
    public int getCommon(int[] nums1, int[] nums2) {
        // return pass1(nums1, nums2);
        return optimal(nums1, nums2);
    }

    //coz, the arrays are sorted in
    //non-decreasing order.
    private int optimal(int[] nums1, int[] nums2) {
        int p1=0, p2=0, n1=nums1.length, n2=nums2.length;
        while (p1 < n1 && p2 < n2) {
            if (nums1[p1] == nums2[p2]) return nums1[p1];
            if (nums1[p1] < nums2[p2]) {
                p1 += 1;
            } else {
                p2 += 1;
            }
        }

        return -1;
    }

    //bute'ish - may be a better way
    //just rushed through this.
    private int pass1(int[] nums1, int[] nums2) {
        Set<Integer> st = new HashSet<>();
        for (int n: nums1) st.add(n);

        int min = Integer.MAX_VALUE;
        for (int n: nums2) {
            if (st.contains(n) && n < min) min = n;
        }

        return min == Integer.MAX_VALUE ? -1: min;
    }
}
