package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/single-element-in-a-sorted-array/
//@strvr - https://takeuforward.org/data-structure/search-single-element-in-a-sorted-array/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6116778045603840
public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        /*NOTE: There is a standard XOR method, to find the odd one out.
        However, since the input array in this question is sorted, we can
        do better than a linear complexity, by applying binary search here. */
        return binarySearchStrvr(nums);
        //return binarySearchEdctv(nums);
    }

    private int binarySearchStrvr(int[] nums) {
        int left = 0;
        int right = nums.length-2; //right starts with one before end (for edge case). Refer strvr.

        //We'll image the array to be in two part. Broken at the index
        //where the odd one out elements lives. The left half (0 to index before
        //odd one out) consists of all elements which are double.
        //While the right half (from the odd one out to end or array) will have double elements
        //expect the odd one out off-course. Because of this, in the left half, each new element will
        //start from and even index and its copy will be next to it i.e. at an odd index.
        //While in the right half, due to the odd one out element, a num will start at odd index
        //and its copy will be at index next to it, i.e. at even index (opposite of left half.)
        //Our odd one out element, will lie in the right half, so that's where we'll try to
        //move in our binary search.
        while (left <= right) {
            int mid = left + (right-left)/2;
            //mid^1 --> if mid is odd, gives prev even num. Else if mid is even
            //it gives, next odd.
            //if mid is even and next element is same as curr, we're in left half.
            //or if mid is odd, and prev element is same is curr, we're in left half as well.
            //in both cases, since our target will be in right half, we move to right half.
            if (nums[mid] == nums[mid^1]) {
                left = mid+1; //Order disruption hasn't started. Move to right portion.
            } else {
                right = mid-1; //order is disrupted. Culprit must be somewhere in the left portion.
            }
        }

        return nums[left]; //right and left will cross right at the breakpoint.
    }

    private int binarySearchEdctv(int[] nums) {
        int l  = 0;
        int r = nums.length-1;
        while (l < r) {
            int mid = l + ((r-l) / 2);
            if (mid%2 != 0) mid -= 1; //if odd, make it even.
            if (nums[mid] == nums[mid+1]) {
                l = mid+2;
            } else r = mid;
        }

        return nums[l];
    }
}
