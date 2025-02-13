package lc_potd;

import java.util.Arrays;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/minimum-operations-to-exceed-threshold-value-ii/
public class MinimumOperationsToExceedThresholdValueII {
    public int minOperations(int[] nums, int k) {
        return pass1(nums, k);
    }

    private int pass1(int[] nums, int k) {
        int n = nums.length;

        /**
         Below is inefficient way to put stuff
         in priority queue because TC is O(nlogn)
         */
        // PriorityQueue<Long> minHeap = new PriorityQueue<>();
        // for (int i=0; i<n; i++) {
        //     minHeap.add((long)nums[i]);
        // }

        /**
         The hepify operation to build heap is optimized
         and TC is O(N).
         So, as a rule of thumb, for any heap related problem
         build the heap using heapify and not by inserting the
         elements into heap one by one.
         */
        // Convert int[] to Long[] (O(n) operation)
        Long[] boxedArr = Arrays.stream(nums).mapToLong(el -> el).boxed().toArray(Long[]::new);

        // Build the heap from the collection (O(n) operation) - heapify
        PriorityQueue<Long> minHeap = new PriorityQueue<Long>(Arrays.asList(boxedArr));

        int ops = 0;
        while (minHeap.size() >= 2) {
            long m = minHeap.remove();

            if (m >= k) break;
            ops += 1;

            long sm = minHeap.remove();

            minHeap.add(2*m + sm);
        }

        return ops;
    }
}
