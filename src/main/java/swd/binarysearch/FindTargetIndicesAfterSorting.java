package swd.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/find-target-indices-after-sorting-array/description/
public class FindTargetIndicesAfterSorting {
    public List<Integer> targetIndices(int[] nums, int target) {
        //sort the array, ATQ.
        Arrays.sort(nums);

        //find first index.
        int firstIndex = binarySearch(nums, target, true);

        //if no first index, mean element doesn't  exist in the target array.
        if (firstIndex == -1) return new ArrayList<>();

        //find last occurrence.
        int endIndex = binarySearch(nums, target, false);

        //fill the answer from first to last index.
        List<Integer> ans = new ArrayList<>();
        for (int i=firstIndex; i<=endIndex; i++) {
            ans.add(i);
        }

        return ans;
    }

    private int binarySearch(int[] nums, int target, boolean findFirst) {
        int start = 0;
        int end = nums.length-1;
        int ans = -1;

        while (start <= end) {
            int mid = (start+end)/2; //or (start+(end-start))/2 --> will prevent overflow.
            //if found.
            if (nums[mid] == target) {
                ans = mid;
                if (findFirst)
                    end = mid-1; //if finding first occurrence, move to left part if there's more occurrence.
                else
                    start = mid + 1; //if finding the last occurrence, move right to find the rightmost occurrence.
            }
            //move to left half if target is smaller than mid
            //else move right (because the array is sorted in desc order)
            else if (target < nums[mid]) {
                end = mid - 1; //ignoring right half.
            } else {
                start = mid + 1; //ignorign left half.
            }
        }

        return ans;
    }
}
