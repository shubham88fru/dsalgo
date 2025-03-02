package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/
public class MergeTwo2DArraysBySummingValues {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        return pass1(nums1, nums2);
    }

    private int[][] pass1(int[][] nums1, int[][] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        List<int[]> ans = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;

        while (p1 < n1 && p2 < n2) {
            if (nums1[p1][0] == nums2[p2][0]) {
                ans.add(new int[] {nums1[p1][0], nums1[p1][1] + nums2[p2][1]});
                p1 += 1;
                p2 += 1;
            } else if (nums1[p1][0] < nums2[p2][0]) {
                ans.add(nums1[p1]);
                p1 += 1;
            } else {
                ans.add(nums2[p2]);
                p2 += 1;
            }
        }

        while (p1 < n1) {
            ans.add(nums1[p1]);
            p1 += 1;
        }

        while (p2 < n2) {
            ans.add(nums2[p2]);
            p2 += 1;
        }

        return ans.toArray(int[][]::new);
    }
}
