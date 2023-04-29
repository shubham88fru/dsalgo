package swd.binarysearch;

//@link - https://leetcode.com/problems/search-insert-position/description/
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        int closest = -1;
        while (start <= end) {
            int mid = (start + (end-start)/2);

            //if no. found - return it.
            if (nums[mid] == target) return mid;
            //else, if curr num is less than target,
            //the target could potentially be inserted to a
            //position next to curr no. so record it and search in
            //right half.
            if (nums[mid] < target) {
                start = mid + 1;
                closest = mid;
            } else { //else, target must be in left half.
                end = mid - 1;
            }
        }

        //no. can be inserted at the next index of closest.
        return closest+1;
    }
}
