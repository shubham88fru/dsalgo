package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/sliding-window-maximum/
//@strvr - https://takeuforward.org/data-structure/sliding-window-maximum/
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return getMaxInEachWindow(nums, k);
    }

    //1) Optimal solution. Using a doubly ended queue.
    private int[] getMaxInEachWindow(int[] nums, int k) {
        int n = nums.length;
        int[] r = new int[n-k+1];
        int ri = 0;

        Deque<Integer> deq = new ArrayDeque<>(); //doubly ended queue.
        for (int i=0; i<n; i++) {
            //remove nums out of range window k.
            if (!deq.isEmpty() && deq.peek() == i-k) deq.poll();

            //remove smaller number is k window as they are useless.
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i]) deq.pollLast();

            deq.offer(i);
            if (i >= k-1) r[ri++] = nums[deq.peek()];
        }
        return r;
    }

    //2) Brute force. iterate each window and find max in each window.
}
