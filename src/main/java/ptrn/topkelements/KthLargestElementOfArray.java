package ptrn.topkelements;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElementOfArray {
    public int findKthLargest(int[] nums, int k) {
        return better(nums, k);
    }

    private int better(int[] arr, int k) {
        /**
         * this pattern takes steps to solve the problem of finding the top k
         largest elements (using min-heap) or top k smallest elements (using max-heap):
         - Insert the first k elements from the given set of elements
         to the min-heap or max-heap.
         - Iterate through the rest of the elements.
         - For min-heap, if you find the larger element, remove the top
         (smallest number) of the min-heap and insert the new larger element.
         - For max-heap, if you find the smaller element, remove the top
         (largest number) of the max-heap and insert the new smaller element.
         *
         */
        //At the end this minHeap will have k element which
        //will be the k largest elements of the heap.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //push first k elemnts to minHeap.
        for (int i=0; i<k; i++) minHeap.add(arr[i]);

        int n = arr.length;
        //Then, iterate over the rest of n-k elements
        //and for each such element, check if it is greater
        //than root of the minHeap, if so, pop the root
        //and insert curr element to the heap. (essentially, if
        //curr element is larger than the root (i.e. the smallest seen so far)
        //, the root can certainly not be part of the k largest elements and so
        //we remove it.). Imagine this process to be similar to a sliding window
        //moving across the array. The window initially has the first k elements,
        //and then in each iteration we keep removing the smallest. In this end,
        //the window (i.e. the minHeap) will have the k largest elements.
        for (int i=k; i<n; i++) {
            if (minHeap.peek() <= arr[i]) {
                minHeap.remove();
                minHeap.add(arr[i]);
            }
        }

        //At this point, the minHeap will consist
        //of the largest K elements of the array. And
        //the smallest of these K (i.e. kth largest) will
        //be the root of the minHeap.
        return minHeap.remove();
    }
}
