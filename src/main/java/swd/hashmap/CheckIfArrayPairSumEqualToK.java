package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/two-sum/description/
public class CheckIfArrayPairSumEqualToK {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            //other half of the pair will be target minus the no. itself.
            int a = nums[i];
            int b = target-a; //b+a = target

            //if such a num seen before, we've found the pair.
            if (map.containsKey(b)) return new int[] {i, map.get(b)};

            //else put the num in map, for next iteration.
            map.put(a, i);
        }
        return new int[] {0, 0};
    }

    public static void main(String[] args) {
        CheckIfArrayPairSumEqualToK checkIfArrayPairSumEqualToK = new CheckIfArrayPairSumEqualToK();
        checkIfArrayPairSumEqualToK.twoSum(new int[]{3,2,4}, 6);
    }
}
