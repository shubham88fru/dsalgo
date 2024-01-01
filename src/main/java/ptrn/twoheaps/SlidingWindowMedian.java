package ptrn.twoheaps;

import java.util.*;

//@link - https://leetcode.com/problems/sliding-window-median/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4785890087141376
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        //return suboptimal(nums, k);
        return optimal(nums, k);
    }

    //1) Optimal (To get an idea, refer the solution 2 first and maybe also read edctv)
    //keep two heaps - Min and max.
    //But for a new window, use the slidingwindow
    //concept to remove outgoing element and insert the
    //incoming elements into the heap, instead of iterating
    //over the entire window and inserting each element again.
    //The optimal approach is also as straight forward as the
    //suboptimal, however, the tricky parts and complexity comes
    //here from the fact that we have to keep the heaps in sync
    //with the window because unlike the suboptimal approach, we aren't
    //wiping out the entire heap at the start of each window.
    private double[] optimal(int[] nums, int k) {
        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Double, Double> releaseTracker = new HashMap<>();

        //First, add k elements to the maxHeap.
        for (int i=0; i<k; i++) {
            maxHeap.add((double)nums[i]);
        }

        /**
         //Then, transfer half the elements to the minHeap
         //from the maxHeap.
         int cnt = k/2;
         while (cnt > 0) {
         minHeap.add(maxHeap.remove());
         cnt -= 1;
         }
         */

        for (int i=0; i<k/2; i++) minHeap.add(maxHeap.remove());

        //balance is 0 when the two heaps have the num of elements that they should have.
        //i.e. Ceil[k/2] in maxHeap and Floor[k/2] in minHeap.
        int balance = 0;

        List<Double> ans = new ArrayList<>();

        //For sliding window.
        int start = 0;
        int end = k;
        while (end <= nums.length) {

            //And then, once we have the two middle elements, we
            //see if the window is even length or odd length. For an
            //even length window, both the middle elements need to be considered
            //while for the odd length window, only the middle element is needed.
            if (k%2 == 0) {
                ans.add((maxHeap.peek() + minHeap.peek()) / 2.0);
            } else ans.add(maxHeap.peek());

            /**
             When the window slides and the tail leaves, the window, it needs
             to be removed from the heaps as well. However, searching the element
             and removing it from the heap would be an O(m) (m is size of heap) affair.
             Hence, we don't remove the element from the heap directly. Rather, we
             maintain a hash map to keep track of the elements of the heap to be removed
             and we only remove the element from the heap if its the top of the heap (O(logN)).
             Because if the element to be removed is not the top of the heap, it doesn't disturb
             the calculation of the medians anyways.
             */
            if (end >= nums.length) break;
            double toRelease = (double) nums[start];
            double toAcquire = (double) nums[end];


            if (!maxHeap.isEmpty() && toRelease <= maxHeap.peek()) balance -= 1; //LABEL: A1. Paired with Label A3.
            else balance += 1; //LABEL: A2. Paired with Label A4.

            if (releaseTracker.containsKey(toRelease)) {
                releaseTracker.put(toRelease, releaseTracker.get(toRelease)+1);
            } else releaseTracker.put(toRelease, 1d);

            //Note that the balance variable helps us know that if the toRelease and toAcquire elements were
            //removed and added to same half repectively. Coz, if we add and remove to same half, we don't
            //need to do anything to balance the heaps. But if removal and addition happens to different
            //halfs, the middle elements would move and so we'll need to rebalance the heaps.
            if (!maxHeap.isEmpty() && toAcquire <= maxHeap.peek()) {
                balance += 1; //LABEL A3. Paired with Lable A1.
                maxHeap.add(toAcquire);
            } else {
                balance -= 1; // Label A4. Paired with Label A2.
                minHeap.add(toAcquire);
            }

            //Based on the aboves lines, where we updated balance, If balance is -ve
            //it means the minHeap has extra elements (than it should) compared to maxHeap.
            //And vice versa. And so, we removed an element from the respective heap and
            //add to the other. This is done becuase, if the left half of the window get an element
            //then the current middle element would roll over to the other half, while if an element
            //is added to the right half, then the current middle element will be a part of the left half.
            if (balance < 0) maxHeap.add(minHeap.remove());
            else if (balance > 0) minHeap.add(maxHeap.remove()); //coz, balance=0 mean heap is already balanced.

            //since the two heaps have now been balanced, we reset the balanced variable to 0.
            balance = 0;

            //remove the invalid nums present in the hashmap from top of max heap.
            //(Only if they are top elements, otherwise, they don't affect our median calculation)
            while (!maxHeap.isEmpty() && releaseTracker.containsKey(maxHeap.peek())) {
                double delete = maxHeap.remove();
                releaseTracker.put(delete, releaseTracker.get(delete)-1);
                if (releaseTracker.get(delete) == 0d) releaseTracker.remove(delete);
            }

            while (!minHeap.isEmpty() && releaseTracker.containsKey(minHeap.peek())) {
                double delete = minHeap.remove();
                releaseTracker.put(delete, releaseTracker.get(delete)-1);
                if (releaseTracker.get(delete) == 0d) releaseTracker.remove(delete);
            }

            start += 1;
            end += 1;
        }

        //System.out.println(ans);
        return ans.stream().mapToDouble(d -> d).toArray();
    }

    //2) Sub-optimal --> Gives TLE.
    //Keep two heaps - Min and max.
    //For every window push the k/2 elements to min heap
    //and the other k/2 to max heap. if k is odd, just take
    //an element from one of the heaps, while if k is even,
    //take two elements and avg them. (Detailed steps below)
    private double[] suboptimal(int[] nums, int k) {
        //first window.
        int start = 0;
        int end = k-1;

        List<Double> ans = new ArrayList<>();
        while (end < nums.length) {
            //Empty heaps for each window.
            //For each window, we'll iterate through each element
            //and insert the elements to these heaps. This idea is what makes
            //this approach suboptimal and causes a TLE.
            PriorityQueue<Double> minHeap = new PriorityQueue<>();
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            //First, put all elements of the window
            //to a max heap.
            for (int i = start; i <= end; i++) {
                maxHeap.add((double)nums[i]);
            }

            //Then take out k/2 elements of the max heap
            //and shove them to the min heap.
            //Doing so will give us two heaps such that
            //one (maxHeap) will have the max element of the
            //first half of the sorted window, while the second (minHeap)
            //will have the min of the second half of the sorted window.
            //Therefore, we'll basically have both the middle elements of
            //the window if it were sorted. Which is exactly what we need to
            //estimate the median of the window.
            int cnt = k/2;
            while (cnt > 0) {
                minHeap.add(maxHeap.remove());
                cnt -= 1;
            }

            //And then, once we have the two middle elements, we
            //see if the window is even length or odd length. For an
            //even length window, both the middle elements need to be considered
            //while for the odd length window, only the middle element is needed.
            if (k%2 == 0) {
                ans.add((maxHeap.remove() + minHeap.remove()) / 2.0);
            } else ans.add( maxHeap.remove());

            start += 1;
            end += 1;
        }

        return ans.stream().mapToDouble(d -> d).toArray();
    }

    //3) Brute --> Gives TLE.
    //Get the elements in each window, sort them
    //and then find the median of the window.
}
