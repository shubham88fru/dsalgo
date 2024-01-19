package ptrn.topkelements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/kth-largest-element-in-a-stream/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6600090295992320
//1) Optimal approach
public class KthLargestElementInAStream {
    int k;
    /**
     We’ll implement a min-heap of size k.
     In a min-heap, the smallest number is always at the top.
     We’ll use this property to design a solution that
     ensures that in a min-heap with k elements, the kth largest
     element is always at the top of the heap.
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.add(val);
        } else if (minHeap.peek() <= val) {
            minHeap.remove();
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}

//2) Brute force approach.
class KthLargest {
    int k;
    List<Integer> lst = new ArrayList<>();
    int kthLargest = Integer.MIN_VALUE;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num: nums) lst.add(num);
    }

    public int add(int val) {
        lst.add(val);
        if (kthLargest != Integer.MIN_VALUE) {
            if (val < kthLargest) return kthLargest;
        }

        Collections.sort(lst, (a, b) -> b-a);

        for (int i=0; i<k; i++) {
            kthLargest = lst.get(i);
        }

        return kthLargest;
    }
}
