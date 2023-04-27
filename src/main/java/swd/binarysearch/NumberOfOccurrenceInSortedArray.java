package swd.binarysearch;

//@link - https://practice.geeksforgeeks.org/problems/number-of-occurrence2259/1
public class NumberOfOccurrenceInSortedArray {
    int count(int[] nums, int n, int target) {
        int firstIndex = binarySearch(nums, target, true);
        if (firstIndex == -1) return 0; //if theres' no first occurence, means element not present.

        int endIndex = binarySearch(nums, target, false);
        return endIndex-firstIndex+1;
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
                    end = mid-1; //if finding first occurence, move to left part if there's more occurence.
                else
                    start = mid + 1; //if finding the last occurrence, move right to find the rightmost ouccrence.
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
