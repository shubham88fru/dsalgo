package swd.binarysearch;

//@link - https://leetcode.com/problems/binary-search/description/
public class BinarySearch {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    //binary search algorithm.
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while (start <= end) {
            int mid = (start+end)/2;
            //if found, return.
            if (nums[mid] == target) return mid;
            //move to left half if target is smaller than mid
            //else move right.
            if (target < nums[mid]) {
                end = mid-1; //ignoring right half.
            } else {
                start = mid + 1; //ignorign left half.
            }
        }

        return -1;
    }
}
