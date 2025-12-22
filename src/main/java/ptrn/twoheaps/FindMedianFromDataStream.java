package ptrn.twoheaps;

import java.util.Collections;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-median-from-data-stream/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5808780689539072
public class FindMedianFromDataStream {
    //Using the two heaps approach.
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    //to the store the total
    //count of element - odd/even.
    int cnt;

    public FindMedianFromDataStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        cnt = 0;
    }

    public void addNum(int num) {
        cnt += 1;
        //first num goes to the maxhep.
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(num);
        } else {
            //if new num is less than top of maxHeap,
            //add to maxHeap or vice-versa.
            if (num <= maxHeap.peek()) maxHeap.add(num);
            else minHeap.add(num);

            //balance the heaps.
            //In a balanced scenario, when even elements, both heaps
            //will have same no. of element. But when
            //odd, the maxHeap will have an extra element coz it will
            //have the middle element.
            if (maxHeap.size() > minHeap.size() + 1) {
                //if maxHeap has an extra element, the middle
                //curr middle will roll over to the other half
                //and so, shove it to the minHeap.
                minHeap.add(maxHeap.remove());
            } else if (minHeap.size() > maxHeap.size()) {
                //vice-versa.
                maxHeap.add(minHeap.remove());
            }
        }
    }

    public double findMedian() {
        //if even num of elements, median will be
        //average of middle elements.
        if (cnt%2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            //else median will simply be the middle element.
            return maxHeap.peek();
        }
    }
}

// Mik's approach coded by me.
class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (!maxHeap.isEmpty() && num > maxHeap.peek()) minHeap.add(num);
        else maxHeap.add(num);

        if (minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.remove());
        if (maxHeap.size() - minHeap.size() > 1) minHeap.add(maxHeap.remove());

    }

    public double findMedian() {
        if ((minHeap.size() + maxHeap.size())%2 == 0) {
            return (minHeap.peek() + maxHeap.peek())/2.0;
        }

        return maxHeap.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */