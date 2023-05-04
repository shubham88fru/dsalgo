package swd.binarysearch;

/*
    Not sure if I understand this question and soln very well.
*/
//@link - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        return binarySearch(nums);
    }

    private int binarySearch(int[] nums) {
        int start = 0;
        int end = nums.length -1;

        while (start <= end) {
            int mid = (start + (end-start)/2);

            //Smallest element in a rotated sorted array
            //will always lie in the unsorted half.
            if (nums[mid] > nums[end]) { //means right unsorted.
                start = mid + 1;  //move right
            } else if (nums[mid] < nums[end]) { //means left unsorted
                //move left.
                //Note: The trick to solving this question, is
                //when updating end, we'll update it to mid (not mid-1)
                end = mid;
            } else { //mid and end are equal
                end -= 1; //ignore the duplicate element.
            }
        }

        return nums[start];
    }
}
