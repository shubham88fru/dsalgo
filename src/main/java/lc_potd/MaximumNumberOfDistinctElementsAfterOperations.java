package lc_potd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/description/
//@check - https://www.youtube.com/watch?v=Vg3NjGAgOzU
public class MaximumNumberOfDistinctElementsAfterOperations {
    public int maxDistinctElements(int[] nums, int k) {
        // return brute(nums, k);
        return optimal(nums, k);
    }

    /**
     * Coded by me completely based on mik's explanation.
     * I don't think I'm ever going to be good enough
     * in dsa.
     *
     * @param nums
     * @param k
     * @return
     */
    private int optimal(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int cnt = 0;
        int prev = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            if (nums[i]-k <=  prev) {
                if (prev+1 <= nums[i]+k) {
                    prev += 1;
                    cnt += 1;
                }

            } else {
                prev = nums[i]-k;
                cnt += 1;
            }
        }

        return cnt;
    }

    private int brute(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> st = new HashSet<>();

        for (int num: nums) {
            for (int i=-1*k; i<=k; i++) {
                if (!st.contains(num+i)) {
                    st.add(num+i);
                    break;
                }
            }
        }
        return st.size();
    }
}
