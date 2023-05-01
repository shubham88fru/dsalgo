package swd.binarysearch;

//@link - https://leetcode.com/problems/search-in-rotated-sorted-array/description/
public class SearchInARotatedSortedArray {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        //When a sorted array is rotated, regardless of
        //which index we choose as pivot, atleast one half (left or right)
        //around that pivot will always be sorted.
        while (start <= end) {
            int mid = (start + (end-start) / 2);

            //if found, return.
            if (nums[mid] == target) return mid;

            if (nums[mid] >= nums[start]) {//means left half sorted.
                if (nums[start] <= target && nums[mid] > target) {//means the target lies in the left half.
                    //left half sorted and target lies in that half.
                    //so, prepare for binary search in this part.
                    end = mid - 1;
                } else {
                    //left is sorted but target doesn't lie in that half.
                    //so, prepare of binary search in right half.
                    start = mid + 1;
                }
            } else {//otherwise, right half is sorted.
                if (nums[mid] < target && nums[end] >= target) {//means the target lies in the right half.
                    //right half sorted and target lies in that half.
                    //so, prepare for binary search in this part.
                    start = mid + 1;
                } else {
                    //right half sorted but target doesn't lie in that half.
                    //so, prepare for binary search in left half.
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}
