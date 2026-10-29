package hellointerview.twopointers;

import java.util.HashSet;
import java.util.Set;

//@link - https://www.hellointerview.com/learn/code/two-pointers/two-sum
public class TwoSumSortedArray {
    public boolean twoSum(int[] nums, int target) {
        // Your code goes here
        // return brute(nums, target);
        // return suboptimal(nums, target);
        return optimal(nums, target);
    }

    private boolean optimal(int[] nums, int target) {
        int n = nums.length;
        int l=0, r=n-1;
        while (l < r) {
            if ((nums[l] + nums[r]) == target) return true;
            if (nums[l] + nums[r] < target) {
                l += 1;
            } else r -= 1;
        }

        return false;
    }

    private boolean suboptimal(int[] nums, int target) {
        int n = nums.length;
        Set<Integer> st = new HashSet<>();
        for (int num: nums) {
            if (st.contains(num)) return true;
            st.add(target-num);
        }

        return false;
    }

    private boolean brute(int[] nums, int target) {
        int n = nums.length;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (nums[i]+nums[j] == target) return true;
            }
        }

        return false;
    }
}
