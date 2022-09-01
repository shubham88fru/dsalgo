package basics.priority_queue;

import java.util.PriorityQueue;

//Given an unsorted array and num k,
//need to print k largest element of the
//array.
public class KLargestElements {

    //naive approach will be to sort the array
    //and then get max k els. This will be a O(nlog(n))
    //heap approach will be better here, because question
    //does not need to prevent the order. We just need to
    //return max.

    //But with max heap approach, T: O(n+klog(n))

    //Infact with min heap approach, T can be even
    //better. With min heap approach it will be
    //O(k+(n-k)logk) --> when k small, becomes O(n)
    //when k of order of n, becomes O(n+log(n))
    void printKlargest(int[] arr, int k) {
        //start with a min heap.
        PriorityQueue<Integer> priorityQueue
                = new PriorityQueue<>();

        //add only first k els to priority
        //queue. T: O(k*log(k))
        for (int i=0; i<k; i++) {
            priorityQueue.add(arr[i]);
        }

        //for the next k els in array,
        //we'll iterate and for every el
        //compare with queue's root.
        //after each iteration, below logic
        //will give us the max k els that
        //we have seen so far.
        //T:O((n-k)*log(k))
        for (int i=k; i<arr.length; i++) {
            //if curr el greater than root, remove
            //root and add curr element to priority
            //queue.
            if (arr[i]>priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(arr[i]);
            }
        }

        //finally print
        //T: O(k*log(k))
        for (int i=0; i<k; i++) {
            System.out.println(priorityQueue.poll());
        }

        //TOTAL: O(k*log(k))+O((n-k)*log(k))+O(k*log(k))
    }

    public static void main(String[] args) {
        KLargestElements kLargestElements
                = new KLargestElements();
        int[] arr = {1, 23, 12, 9, 30, 2, 50};
        kLargestElements.printKlargest(arr, 3);
    }
}
