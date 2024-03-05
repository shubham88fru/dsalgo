package ptrn.fastnslowpointers;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/find-the-duplicate-number/description/
//@strvr - https://takeuforward.org/data-structure/find-the-duplicate-in-an-array-of-n1-integers/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6745247192973312
public class FindTheDuplicateNum {public int findDuplicate(int[] nums) {
    //Note that an approach similar to
    //that of finding the missing num can not be used here.
    //In this question, although the nums in array are in [1, n]
    //but this doesn't mean that every num from 1 to n appears atleast
    //once. The only thing guaranteed in this question is that each element
    //of the array will be in 1 to n and not that each of 1 to n appears in the array.


    //return findDuplicateBetter(nums);
    return findDuplicateOptimal(nums);
}

    //1) Optimal Soln: T: O(N), S: O(1)
    //Using linked list cycle method and Tortoise and Hare (slow and fast pointer)
    private int findDuplicateOptimal(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        //move till first collision.
        do {
            slow = nums[slow]; //move 1 at a time
            fast = nums[nums[fast]]; //move 2 at a time.
        } while (slow != fast);

        //after first collision, reset the fast pointer.
        fast = nums[0];

        //and then move fast and slow at same speed till next collision.
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        //the index where the the 2 pointers
        //collide second time is the duplicate
        //Note: not the element at the slow index.
        return slow;
    }


    //2) Better approach: T: O(N), S: O(N)
    //Use a hashmap/array of frequencies.
    private int findDuplicateBetter(int[] nums) {
        int[] freq = new int[nums.length];

        for (int num: nums) {
            if (freq[num] == 0) freq[num] += 1;
            else return num;
        }

        return -1;
    }

    //3) Brute force approaches -
    //Brute force 1: T: O(N^2), S: O(1)
    //For each element traverse the remaining array and
    //check if duplicate is present.
    //Brute force 2: T: O(NlogN), S: O(1)
    //Sort the array and then traverse the
    //array to search for val[i] that is same as val[i+1]
    //But this will distort the array.

    //4) Edctv soln 2. Using cyclic sort (slightly modified).
    //T: O(N), S: O(N)
    private int sol4(int[] nums) {
        Set<Integer> swapped = new HashSet<>();
        /*********Cyclic Sort******************/
        int i=0;
        while (i<nums.length) {
            //Ignore -ves, large nums, nums that arent' already correctly positioned and duplicates.
            if (!(nums[i] >= nums.length || nums[i] == i+1 || swapped.contains(nums[i]))) {
                swapped.add(nums[i]);
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            } else i += 1;
        }
        /*********Cyclic Sort******************/
        return nums[nums.length-1];
    }

}
