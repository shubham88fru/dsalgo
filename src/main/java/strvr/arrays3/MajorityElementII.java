package strvr.arrays3;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/majority-element-ii/description/
//@strvr - https://takeuforward.org/data-structure/majority-elementsn-3-times-find-the-elements-that-appears-more-than-n-3-times-in-the-array/
public class MajorityElementII {
    /*
        Note: There can always be at most only two
        elements (in an array of size n) in the array
        whose frequency is greater than n/3

        3*([n/3] + 1) --> ([n] + 3) is definitely greater than n.
        2*([n/3] + 1) --> ([2n/3] + 2) can be smaller than n.
    */
    public List<Integer> majorityElement(int[] nums) {
        //return moreThanThirdsElementBrute(nums);
        return moreThanThirdsElementOptimal(nums);
    }

    //1) Optimal approach: T: O(N), S: O(1)
    //Using moore's voting algorithm.
    private List<Integer> moreThanThirdsElementOptimal(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        //Approach is exactly same as for majority element 1 (>n/2)
        //with additions for element 2.
        int n = nums.length;
        int nBy3 = (int) Math.floor(n/3);

        int count1 = 0;
        int count2 = 0;

        int el1 = 0;
        int el2 = 0;

        for (int i=0; i < n; i++) {
            //el1 and el2 shouldn't be same
            if (count1 == 0 && nums[i] != el2) {
                count1 = 1;
                el1 = nums[i];
            } else if (count2 == 0 && nums[i] != el1) { //el1 and el2 shouldn't be same
                count2 = 1;
                el2 = nums[i];
            } else if (nums[i] == el1) {
                count1 += 1;
            } else if (nums[i] == el2) {
                count2 += 1;
            } else {
                count1 -= 1;
                count2 -= 1;
            }
        }

        //Question does not guarantee whether
        //the input array is guranteed to have elements
        //with frequency more than n/3 or not.
        //So we need to check explictly.
        int freq1 = 0;
        int freq2 = 0;
        for (int num: nums) {
            if (num == el1) freq1 += 1;
            else if (num == el2) freq2 += 1;
        }

        if (freq1 > nBy3) ans.add(el1);
        if (freq2 > nBy3) ans.add(el2);

        return ans;

    }

    //2) Better approach: T: O(N), S: O(N)
    //using hashmap to keep track of frequency

    //3) Brute force: T: O(N^2), S: O(1)
    //For each element, check its freq and see if its more than nBy3.
    //since there can only at most be 2 such elements (greater than nBy3)
    //we break the loop as soon as the list has 2 elements.
    private List<Integer> moreThanThirdsElementBrute(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        int nBy3 = (int) Math.floor(n/3);

        for (int i=0; i<n; i++) {
            int count = 0;

            //If ans empty or if we've not already stored
            //the num, count it and if more than nBy3, store it.
            if (ans.size() == 0 || nums[i] != ans.get(0)) {
                for (int j=i; j<n; j++) {
                    if (nums[j] == nums[i]) count += 1;
                }

                if (count > nBy3) ans.add(nums[i]);
            }

            //since there can be at max 2 such elements (greater than nBy3)
            //break as soon as we get 2 elements.
            if (ans.size() == 2) break;
        }

        return ans;
    }
}
