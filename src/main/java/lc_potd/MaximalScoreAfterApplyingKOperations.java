package lc_potd;

import java.util.Collections;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/maximal-score-after-applying-k-operations/description/
public class MaximalScoreAfterApplyingKOperations {
    public long maxKelements(int[] nums, int k) {
        return mysol(nums, k);
    }

    /*
        -1) My first thought to solve the problem was to us DP/memoization.
        However, then I realized that we also had to change the
        og array (with Math.ceil(..)) which would mean also
        some sort of backtracking. From experience, memoization and backtracking
        don't go well together. Plus, simple backtracking would have never worked
        because of the constraints.
    */

    //0) Using heap.
    private long mysol(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num: nums) {
            maxHeap.add(num);
        }

        long maxSum = 0;
        //Keep taking the max available num
        //untill k operations have been done.
        while (k > 0) {
            long num = (long)maxHeap.remove();
            maxSum += num;
            maxHeap.add((int)Math.ceil(num/3.0));
            k -= 1;
        }

        return maxSum;
    }
}
