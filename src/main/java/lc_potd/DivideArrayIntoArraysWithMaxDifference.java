package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/description/
public class DivideArrayIntoArraysWithMaxDifference {
    public int[][] divideArray(int[] nums, int k) {
        return pass1(nums, k);
    }

    /*
        My soln. Idea is to group closest elements.
        If closest elements don't work for any subgroup
        then there's no way that group will work with
        other elements because other elements will all
        be gte elements in current group, which means that
        the difference will be lte the diff in current
        group.
    */
    private int[][] pass1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int[][] ans = new int[n/3][3];
        int i = 0;
        while (i < n) {
            int[] sub = new int[3];
            int idx = i/3;
            for (int j=0; j<3; j++) {
                sub[j] = nums[i];
                i += 1;
            }
            if (sub[2] - sub[0] > k) return new int[0][0];
            ans[idx] = sub;
        }

        return ans;
    }
}
