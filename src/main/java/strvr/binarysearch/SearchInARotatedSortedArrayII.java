package strvr.binarysearch;

//@link - https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
//@strvr - https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
public class SearchInARotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    private boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        //When a sorted array is rotated, regardless of
        //which index we choose as pivot, atleast one half (left or right)
        //around that pivot will always be sorted.
        while (start <= end) {
            int mid = (start + (end-start) / 2);

            //if found, return.
            if (nums[mid] == target) return true;

            /*
                This block is the only diff between search in rotated
                sorted array I and II.

                If num at mid is same as that at start, we'll just ignore
                and move the start pointer to next (continue to do so untill they're diff).
                If in this process, the start pointer crosses the mid, we've entered the other half,
                so need to update the mid again, and check if it is target.
            */
            /////////////////////////////////////
            while (start < mid && nums[mid] == nums[start]) {
                start += 1;
            }

            //optimization (will work even if don't t do this)
            //remove duplicate from end as well.
            while (end > mid && nums[mid] == nums[end]) {
                end -= 1;
            }
            /////////////////////////////////////

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

        return false;
    }
}
