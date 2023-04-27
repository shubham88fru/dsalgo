package swd.binarysearch;

//@link - https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
public class FindFirstAndLastOccurrenceInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int firstIndex = binarySearch(nums, target, true);
        int endIndex = binarySearch(nums, target, false);

        return new int[] {firstIndex, endIndex};
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
