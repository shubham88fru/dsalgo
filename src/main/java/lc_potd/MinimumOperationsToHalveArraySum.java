package lc_potd;

import java.util.Comparator;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-operations-to-halve-array-sum/
public class MinimumOperationsToHalveArraySum {
    public int halveArray(int[] nums) {
        return pass1(nums);
    }

    private int pass1(int[] nums) {
        int n = nums.length;
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        double sum = 0.0;
        for (int i=0; i<n; i++) {
            sum += (double)nums[i];
            maxHeap.add((double)nums[i]);
        }

        int ops = 0;
        double currSum = 0.0;
        double half = sum/2.0;
        while (!maxHeap.isEmpty()) {
            if (currSum >= half) break;

            ops += 1;
            double largest = maxHeap.remove();
            currSum += (largest/2.0);

            maxHeap.add((largest/2.0));
        }

        return ops;
    }
}
