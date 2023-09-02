package strvr.linkedlistandarray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link - https://leetcode.com/problems/max-consecutive-ones/description/
//@strvr - https://takeuforward.org/data-structure/count-maximum-consecutive-ones-in-the-array/
public class MaximumConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        return findMaxConsecutiveOnesOptimal(nums);
    }

    private int findMaxConsecutiveOnesOptimal(int[] nums) {
        int max = 0;
        int curr = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 1) { //if encouter one, reset the count and chose the max till now.
                max = Math.max(curr, max);
                curr = 0;
            } else curr += 1; //if 1, keep increasing the coutner.
        }

        //it might be possible that we don't have
        //any 0 at all, in that case, we'll never calculate max.
        //To handle that case, calculate the max one last time and
        //return the ans.
        return Math.max(curr, max);
    }

    private int findMaxConsecutiveOnesSusSolution(int[] nums) {
        int count = 0;
        List<Integer> cons_ones = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
            cons_ones.add(count);
        }
        return Collections.max(cons_ones);
    }
}
