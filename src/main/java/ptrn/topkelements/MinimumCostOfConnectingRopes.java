package ptrn.topkelements;

import java.util.PriorityQueue;

//@link - https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4813582962524160
public class MinimumCostOfConnectingRopes {
    long minCost(long arr[], int n) {
        //We need to keep selecting two smallest
        //nums in the array to add.
        PriorityQueue<Long> minHeap =
                new PriorityQueue<>();

        //push all the elements of the
        //array to the heap.
        for (long num: arr) {
            minHeap.add(num);
        }

        //Then, keep picking two smallest
        //elements from the minHeap, adding them
        //to the final sum and pushing the sum of
        //curr two elements to the heap again.
        long sum = 0;
        while (minHeap.size() >= 2) {
            long min1 = minHeap.remove();
            long min2 = minHeap.remove();

            sum += min1 + min2;
            minHeap.add(min1 + min2);
        }

        return sum;
    }
}
