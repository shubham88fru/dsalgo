package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
//@check - https://www.youtube.com/watch?v=Z4tH40wH6JA&ab_channel=codestorywithMIK
public class ShortestSubarrayWithSumAtleastK {
    public int shortestSubarray(int[] nums, int k) {
        return mikssol(nums, k);
    }

    /*
        I had no clue, how to solve this problem.
        Following is completely based on mik's explanation.
        Not 100% clear on it, tbh.
    */
    private int mikssol(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>(); //stores index in monotonic increasing order of cummulative sum.
        long[] cumulativeSum = new long[n];

        int result = Integer.MAX_VALUE;
        int j = 0;
        while (j < n) {
            if (j == 0) {
                cumulativeSum[j] = nums[j];
            } else {
                cumulativeSum[j] = cumulativeSum[j-1] + nums[j];
            }

            if (cumulativeSum[j] >= k) {
                result = Math.min(result, j+1);
            }

            //need to shrink the window or not.
            while (!dq.isEmpty() && (cumulativeSum[j] - cumulativeSum[dq.peekLast()] >= k)) {
                result = Math.min(result, j-dq.peekLast());
                dq.removeLast();
            }

            while (!dq.isEmpty() && (cumulativeSum[j] <= cumulativeSum[dq.peekFirst()])) {
                dq.removeFirst();
            }

            dq.addFirst(j);
            j += 1;
        }

        return result == Integer.MAX_VALUE ? -1: result;
    }
}
