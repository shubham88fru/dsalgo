package lc_potd;

import java.util.List;

//@link - https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/description/
public class AdjacentIncreasingSubarraysDetectionII {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        return optimal(nums);
    }

    /**
     Optimal soln.
     Becomes easy after solving part I of this problem
     <a href="https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/">...</a>

     @see AdjacentIncreasingSubarraysDetectionI

     One more approach to solve this problem is to simply
     use part I's code and do a binary search over 'k' and
     seeing the larget 'k' that returns true.
     */
    private int optimal(List<Integer> nums) {
        int n = nums.size();

        int prevArrayLen = 0; // prev subarray.
        int currArrayLen = 1; // current subarray.
        int kay = 1;

        for (int i=1; i<n; i++) {
            if (nums.get(i) > nums.get(i-1)) {
                currArrayLen += 1;
            } else {
                prevArrayLen = currArrayLen;
                currArrayLen = 1;
            }

            kay = Math.max(kay, Math.min(prevArrayLen, currArrayLen));
            kay = Math.max(kay, currArrayLen/2); //case when currArrayLen >= 2k
        }

        return kay;
    }
}
