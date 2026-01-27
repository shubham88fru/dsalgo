package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/minimum-absolute-difference
public class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        //Sorting will ensure that all closer (magnitude wise) elements
        //stay close to each other and so, we are guaranteed that
        //pairs in a sorted array will have the min diff, whatever it may be.
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        for (int i=0; i<arr.length-1; i++) {
            int diff = Math.abs(arr[i]-arr[i+1]);
            if (diff < minDiff) {
                ans = new ArrayList<>();
                minDiff = diff;
            }

            if (diff == minDiff) {
                ans.add(Arrays.asList(arr[i], arr[i+1]));
            }

        }

        return ans;
    }
}
