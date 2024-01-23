package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/binary-search/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6748849764564992
public class BinarySearch {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    //binary search algorithm.
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while (start <= end) {
            int mid = (start+end)/2; //or (start+(end-start))/2 --> will prevent overflow.
            //if found, return.
            if (nums[mid] == target) return mid;
            //move to left half if target is smaller than mid
            //else move right (because the array is sorted in desc order)
            if (target < nums[mid]) {
                end = mid-1; //ignoring right half.
            } else {
                start = mid + 1; //ignorign left half.
            }
        }

        return -1;
    }
}
