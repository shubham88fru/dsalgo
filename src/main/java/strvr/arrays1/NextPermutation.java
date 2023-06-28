package strvr.arrays1;

//@link - https://leetcode.com/problems/next-permutation/description/
//@strvr - https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        mutateToNext(nums);
    }

    //Mutate the input array to next permutation.
    private void mutateToNext(int[] nums) {
        int n = nums.length;

        //Iterate the array from back
        //and find the first break point.
        //A break point for this question
        //is the index of the first element
        //from back that is less than the element
        //to its right. After doing this, we'll know
        //one thing for sure that the element in range [breakpoint+1, n-1]
        //will certainly be sorted (otherwise we would have
        //gotten a breakpoint in this range).
        int breakPoint = -1;
        for (int i=n-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                breakPoint = i;
                break;
            }
        }

        //If no such breakpoint (i.e. -1) it means the
        //array is the sorted in descending order so ATQ, we'll
        //return the first permuation (ascending order)
        if (breakPoint == -1) reverse(0, nums.length-1, nums);

            //otherwise, we'll find the smallest element greater than the
            //element at breakpoint in the sorted part and swap it with the element
            //at breakpoint. Note: Even after doing this swap, the sorted half will still
            //remain sorted only.
            //Finally, we'll reverse the sorted part so that it becomes sorted in ascending order
            //(since we need the minimum) and return.
        else {
            swap(findMinIdxInSortedPart(breakPoint, nums), breakPoint, nums);
            reverse(breakPoint+1, nums.length-1, nums);
        }
    }

    private int findMinIdxInSortedPart(int start, int[] nums) {
        int n = nums.length;
        for (int i=n-1; i > start; i--) {
            if (nums[i] > nums[start]) return i;
        }

        return -1;
    }

    private void swap(int srcIndex, int destIndex, int[] nums) {
        int temp = nums[srcIndex];
        nums[srcIndex] = nums[destIndex];
        nums[destIndex] = temp;
    }

    private void reverse(int start, int end, int[] nums) {
        int left = start;
        int right = end;

        while (start <= end) {
            swap(start, end, nums);
            start += 1;
            end -= 1;
        }
    }
}
