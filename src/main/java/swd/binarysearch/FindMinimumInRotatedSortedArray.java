package swd.binarysearch;

//@link - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        return binarySearch(nums);
    }

    private int binarySearch(int[] nums) {
        int start = 0;
        int end = nums.length-1;

        //if given array already sorted, return first element.
        //Note: Checking last greater than first is sufficient to indicate
        //that the array is sorted in this case, because the question says that
        //the input array is already sorted and maybe rotated.
        if (nums[end] >= nums[start]) return nums[0];

        while (start <= end) {
            int mid = (start + (end-start)/2);

            //only the smallest element (i.e. the first elemtn of a ascending sorted array)
            //will follow the property that once rotated, it will lower than the element before
            //it as well as element after it. All other elements in the array will be greater
            //than element before them and less than element after them.
            if (nums[mid] > nums[mid+1]) return nums[mid+1]; //element at mid+1 smaller than element before it.

            if (nums[mid] < nums[mid-1]) return nums[mid]; //element at mid smaller than element before it.

            //else check which part of array is sorted
            //and move to the unsorted part because the smallest element
            //in the rotated array will always lie in the unsorted part only
            //as it is that element itself which is causing the unsorted behavior
            //in that part at the first place.
            if (nums[mid] >= nums[start]) {
                start = mid + 1; //left is sorted, so move to right part.
            } else {
                end = mid - 1; //right sorted, so move to left part.
            }
        }


        return -1;
    }
}
