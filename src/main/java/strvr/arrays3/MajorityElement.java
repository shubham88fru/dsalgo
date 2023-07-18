package strvr.arrays3;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/majority-element/description/
//@strvr - https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
public class MajorityElement {
    /*
        Note: There can always be at most only
        one element (in array of size n) which
        has a frequency more than n/2
     */
    public int majorityElement(int[] nums) {
        //return majorityElementBrute(nums);
        //return majorityElementBetter(nums);
        return majorityElementOptimal(nums);
    }

    //1) Optimal approach: T: O(N), S: O(1)
    //Using the `Moore's Voting algorithm`
    private int majorityElementOptimal(int[] nums) {
        int count = 0;
        int majorityEl = 0;

        for (int num: nums) {
            //when count becomes
            //zero, reset and start fresh.
            if (count == 0) {
                majorityEl = num;
                count += 1;
            }

            //when curr element same as
            //curr majority, increase count (vote for)
            else if (num == majorityEl) count += 1;

                //otherwise, decrement count (cancel out, vote against)
            else count -= 1;
        }

        //ultimately, `majorityEl` will be the majority element of
        //the array (provided the array has majority element.)
        //all others will cancel out.

        //Note: Moore's voting algo doesn't guarantee that
        //`majorityEl` will be the majority el for all arrays.
        //However, it does guarantee that provided the array has
        //majority element, `majorityEl` will always be that element.
        //In this question, since it is given, that the array will have
        //a majority element for sure, we don't need to check that from
        //our side and can safely assume that `majorityEl` will be
        //the majority element only.
        return majorityEl;
    }

    //2) Better approach: T: O(N), S: O(N)
    //Use a hashmap to keep frequency to find the ans.
    private int majorityElementBetter(int[] nums) {
        int n = nums.length;
        int nBy2 = (int)Math.floor(n/2);
        Map<Integer, Integer> map = new HashMap<>();

        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nBy2) return num;
        }

        return 1;
    }

    //3) Brute force: T: O(N^2), S: O(1)
    //for each element count its occurrence
    //in rest of the array, to find the ans.
    private int majorityElementBrute(int[] nums) {
        int n = nums.length;
        int nBy2 = (int)Math.floor(n/2);
        int count = 1;

        for (int num: nums) {
            for (int j=1; j<n; j++) {
                if (num == nums[j]) count += 1;
                if (count > nBy2) return num;
            }
            count = 1;
        }

        return 1;
    }
}
