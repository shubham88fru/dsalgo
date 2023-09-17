package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/next-greater-element-i/description/
//@strvr - https://takeuforward.org/data-structure/next-greater-element-using-stack/
public class NextGreaterElementLC {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //get next greater for all elements in num2
        Map<Integer, Integer> mp = getNextLargerElements(nums2);
        int[] ans = new int[nums1.length];

        //then lookup elements in num1 and get their
        //next greater.
        for (int i=0; i<nums1.length; i++) {
            ans[i] = mp.get(nums1[i]);
        }

        return ans;
    }


    private Map<Integer, Integer> getNextLargerElements(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> ans = new HashMap<>();

        //since need to find next greater (i.e. to the right of elements)
        //start iterating from end of the list.
        for (int i=arr.length-1; i >= 0; i--) {
            if (stack.isEmpty()) {
                ans.put(arr[i], -1);
            } else if (arr[i] >= stack.peekFirst()) {
                while (!stack.isEmpty() && arr[i] >= stack.peekFirst()) {
                    stack.removeFirst();
                }

                if (stack.isEmpty()) ans.put(arr[i], -1);
                else {
                    ans.put(arr[i], stack.peekFirst());
                }

            } else {
                ans.put(arr[i], stack.peekFirst());
            }

            stack.addFirst(arr[i]);
        }

        return ans;
    }
}
