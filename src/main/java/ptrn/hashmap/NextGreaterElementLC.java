package ptrn.hashmap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/next-greater-element-i/description/
//@strvr - https://takeuforward.org/data-structure/next-greater-element-using-stack/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5054135541170176
public class NextGreaterElementLC {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        return optimal(nums1, nums2);
    }

    private int[] optimal(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;

        Map<Integer, Integer> mp = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=0; i<n2; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peekFirst()) {
                mp.put(stack.peekFirst(), nums2[i]);
                stack.removeFirst();
            }
            stack.addFirst(nums2[i]);
        }

        int[] ans = new int[n1];
        for (int i=0; i<n1; i++) {
            if (mp.containsKey(nums1[i])) ans[i] = mp.get(nums1[i]);
            else ans[i] = -1;
        }

        return ans;
    }
}
