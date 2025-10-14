package lc_potd;

import java.util.List;

//@link - https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/description/?
public class AdjacentIncreasingSubarraysDetectionI {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // return brute(nums, k);
        return optimal(nums, k);
    }

    /**
     Optimal soln.
     Very intuitive but my dumb brain
     couldn't come up with this on its own.
     Coded by me but after hints.
     */
    private boolean optimal(List<Integer> nums, int k) {
        int n = nums.size();

        int prevArrayLen = 0; // prev subarray.
        int currArrayLen = 1; // current subarray.

        for (int i=1; i<n; i++) {
            if (nums.get(i) > nums.get(i-1)) {
                currArrayLen += 1;
            } else {
                prevArrayLen = currArrayLen;
                currArrayLen = 1;
            }

            //If curr increasing array itself is long enough
            //we can definitely split it two increasing subarrays
            //of k len.
            if (currArrayLen >= 2*k) return true;

            //If each of prev and curr subarrays are k len each,
            //then we have an answer too.
            if (Math.min(prevArrayLen, currArrayLen) >= k) return true;
        }

        return false;
    }

    /**
     My brute force soln.
     This was my first intuition.
     */
    private boolean brute(List<Integer> nums, int k) {
        int n = nums.size();

        for (int i=0; i<=(n-2*k); i++) {
            boolean first = checkIncreasing(i, i+k-1, nums);
            boolean second = checkIncreasing(i+k, i+k+k-1, nums);

            if (first && second) return true;
        }

        return false;
    }

    private boolean checkIncreasing(int start, int end, List<Integer> nums) {
        for (int i=start+1; i<=end; i++) {
            if (nums.get(i) <= nums.get(i-1)) return false;
        }

        return true;
    }
}
