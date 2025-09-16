package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/replace-non-coprime-numbers-in-array/description/?
public class ReplaceNonCoprimeNumbersInAnArray {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        return pass1(nums);
    }

    /**
     * My soln but revise GCD and LCM concept
     * and check mik if he has a diff soln.
     */
    private List<Integer> pass1(int[] nums) {
        Deque<Long> stack = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            if (stack.isEmpty()) stack.addFirst((long)nums[i]);
            else {
                long next = nums[i];
                while(!stack.isEmpty() && gcd(next, stack.peekFirst()) > 1) {
                    long el = stack.removeFirst();
                    next = lcm(next, el);
                }
                stack.addFirst(next);
            }
        }

        while (!stack.isEmpty()) ans.add(stack.removeFirst().intValue());
        Collections.reverse(ans);
        return ans;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
