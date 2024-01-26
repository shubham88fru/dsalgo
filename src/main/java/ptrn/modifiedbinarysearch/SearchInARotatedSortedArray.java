package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/search-in-rotated-sorted-array/description/
//@strvr - https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6397071906308096
public class SearchInARotatedSortedArray {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    /**
     * If the array is rotated by less than half the length of the array,
     * at least the second half of the array will still be sorted. Contrarily,
     * if the array is rotated by more than half the length of the array,
     * then at least the first half of the array will be sorted.
     *
     * We can use this property to our advantage and modify the binarySearch function as follows:
     *  - If the target value lies within the sorted half of the array, our problem is a basic binary search.
     *  - Otherwise, discard the sorted half and keep examining the unsorted half.
     *
     * NOTE: This type I of the problem guarantees distinct elements in the array.
     */
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
