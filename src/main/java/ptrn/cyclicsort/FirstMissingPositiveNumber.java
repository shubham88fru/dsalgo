package ptrn.cyclicsort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/first-missing-positive/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5617578409197568
public class FirstMissingPositiveNumber {
    public int firstMissingPositive(int[] nums) {
        // return nonoptimal(nums);
        return optimal(nums);
    }

    //1) Edctv soln. Using cyclic sort (slightly modified).
    //T: O(N), S: O(N)
    private int optimal(int[] nums) {
        Set<Integer> swapped = new HashSet<>();
        /*********Cyclic Sort******************/
        int i=0;
        while (i<nums.length) {
            //Ignore -ves, large nums, nums that arent' already correctly positioned and duplicates.
            if (!(nums[i] <= 0 || nums[i] >= nums.length || nums[i] == i+1 || swapped.contains(nums[i]))) {
                swapped.add(nums[i]);
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            } else i += 1;
        }
        /*********Cyclic Sort******************/

        /**** Post Cyclic sort Detection******/
        int num=1;
        for (int j=0; j<nums.length; j++) {
            if (nums[j] != j+1) break;
            num += 1;
        }
        /**** Post Cyclic sort Detection******/
        return num;
    }

    //2) My soln. Works, but not optimal in terms of TC.
    //T: O(nlogn+), S: O(1)
    private int nonoptimal(int[] nums) {
        Arrays.sort(nums);
        int missing = 1;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= 0) {
                i += 1;
                continue;
            }
            if (missing != nums[i]) return missing;
            while (i < nums.length && missing == nums[i]) {
                i += 1;
            }
            missing += 1;
        }
        return missing;
    }
}
