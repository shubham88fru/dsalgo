package strvr.arrays4;

//@link - https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
//@strvr - https://takeuforward.org/data-structure/two-sum-check-if-a-pair-with-given-sum-exists-in-array/
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        //two pointer approach.
        int left = 0; //pointer 1
        int right = numbers.length-1; //pointer 2

        while (left <= right) {
            //Match found. Per question, there will always
            //be just one solution, so we have to look no further.
            if (numbers[left] + numbers[right] == target) {
                return new int[] {left+1, right+1};
            }

            //NOTE: that we can only move the pointers in the below
            //fashion because we know that the input array is sorted
            //so that when left moves to right, we get a higher sum
            //and when right moves to left, we get a lower sum.
            //otherwise, if sum of two nums is less than target,
            //means we need to increase, so move left towards the right
            if (numbers[left] + numbers[right] < target) left += 1;
            else right -= 1; //else, we need to decrease, so decrease right.
        }

        //per question, its impossible to be
        //in this situation.
        return new int[] {-1, -1};
    }
}
