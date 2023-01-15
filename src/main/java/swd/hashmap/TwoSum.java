package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/two-sum/description/
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            //other half of the pair will be target minus the no. itself.
            int search = target-nums[i];

            //if such a num seen before, we've found the pair.
            if (map.containsKey(search)) return new int[] {i, map.get(search)};

            //else put the num in map, for next iteration.
            map.put(nums[i], i);
        }
        return new int[] {0, 0};
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.twoSum(new int[]{3,2,4}, 6);
    }
}
