package swd.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://practice.geeksforgeeks.org/problems/next-larger-element-1587115620/1
public class NextGreaterElementGFG {
    //Function to find the next greater element for each element of the array.
    public long[] nextLargerElement(long[] arr, int n) {
        return getNextLargerElement(arr);
    }

    private long[] getNextLargerElement(long[] arr) {
        Deque<Long> stack = new ArrayDeque<>();
        long[] ans = new long[arr.length];

        //since need to find next greater (i.e. to the right of elements)
        //start iterating from end of the list.
        for (int i=arr.length-1; i >= 0; i--) {
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else if (arr[i] >= stack.peekFirst()) {
                while (!stack.isEmpty() && arr[i] >= stack.peekFirst()) {
                    stack.removeFirst();
                }

                if (stack.isEmpty()) ans[i] = -1;
                else {
                    ans[i] = stack.peekFirst();
                }

            } else {
                ans[i] = stack.peekFirst();
            }

            //in any case, add curr element to stack.
            stack.addFirst(arr[i]);
        }

        return ans;
    }
}
