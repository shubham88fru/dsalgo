package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/sort-an-array/description/
public class SortAnArray {
    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num: nums) minHeap.add(num);

        int i = 0;
        while (!minHeap.isEmpty()) {
            nums[i] = minHeap.remove();
            i += 1;
        }

        return nums;
    }
}
