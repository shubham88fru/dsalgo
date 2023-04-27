package swd.binarysearch;

//@link - https://practice.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
public class FloorInASortedArray {
    int findFloor(long[] arr, int n, long x) {
        return binarySearch(arr, x);
    }

    private int binarySearch(long[] nums, long target) {
        int start = 0;
        int end = nums.length-1;
        int ans = -1;

        while (start <= end) {
            int mid = (start+end)/2; //or (start+(end-start))/2 --> will prevent overflow.
            //if found.
            if (nums[mid] == target) {
                return mid;
            }
            //move to left half if target is smaller than mid
            //else move right (because the array is sorted in desc order)
            else if (target < nums[mid]) {
                end = mid - 1; //ignoring right half.
            } else {
                //if mid less than target - save it. This could be a potential ans.
                ans = mid;
                start = mid + 1; //ignorign left half.
            }
        }

        return ans;
    }
}
