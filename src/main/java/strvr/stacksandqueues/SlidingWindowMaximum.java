package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/sliding-window-maximum/
//@strvr - https://takeuforward.org/data-structure/sliding-window-maximum/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6355320395005952
//       - https://www.youtube.com/watch?v=29OnjVQ-fk4&t=1733s&ab_channel=codestorywithMIK
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
            if (!deq.isEmpty() && deq.peekFirst() == i-k) deq.removeFirst(); //front

            //remove smaller number is k window as they are useless.
            while (!deq.isEmpty() && nums[deq.peekLast()] < nums[i]) deq.removeLast(); //back

            deq.addLast(i);
            if (i >= k-1) r[ri++] = nums[deq.peekFirst()];
        }
        return r;
    }

    //2) Brute force. iterate each window and find max in each window.
}
