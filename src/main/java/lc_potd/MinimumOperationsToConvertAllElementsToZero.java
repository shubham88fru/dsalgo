package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/description/?
//@check - https://www.youtube.com/watch?v=Q1OSaRRkEcs&t=112s
public class MinimumOperationsToConvertAllElementsToZero {
    public int minOperations(int[] nums) {
        // return brute(nums);
        return optimal(nums);
    }

    /**
     * Monotonic stack.
     *
     * @param nums
     * @return
     */
    private int optimal(int[] nums) {
        int n = nums.length;

        int ops = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int num: nums) {
            while (!stack.isEmpty() && num < stack.peekFirst()) stack.removeFirst();

            if (num == 0) continue;

            if (stack.isEmpty() || stack.peekFirst() != num) {
                ops += 1;
                stack.addFirst(num);
            }
        }

        return ops;
    }

    /**
     My brute force approach was a bit diff
     and complicated. This is mik's approach
     and relatively more straightforward.
     */
    private int brute(int[] nums) {

        Set<Integer> unique = new HashSet<>();
        for (int num: nums) unique.add(num);

        int ops = 0;
        for (int u: unique) {
            boolean flow = false;

            if (u==0) continue;

            for (int n: nums) {
                if (n == u) {
                    if (!flow) {
                        ops += 1;
                        flow = true;
                    }
                } else if (n < u) {
                    flow = false;
                }
            }
        }

        return ops;
    }
}
