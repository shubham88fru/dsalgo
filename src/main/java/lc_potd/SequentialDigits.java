package lc_potd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/sequential-digits/description/?
//@check - https://www.youtube.com/watch?v=E5XFO3-6xe4
public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        // return approach1(low, high);
        return approach2(low, high);
    }

    /*
        Coded by me but completely based on
        mik's approach.
    */
    private List<Integer> approach1(int low, int high) {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i=1; i<9; i++) q.addLast(i);

        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int num = q.removeFirst();

            if (num >= low && num <= high) ans.add(num);

            int lastDigPlus1 = (num%10 + 1);
            int next = (num*10 + lastDigPlus1);

            if (lastDigPlus1 > 9) continue;
            if (next > high) continue;

            q.addLast(next);
        }

        return ans;
    }

    /**
     My brute force approach
     */
    private List<Integer> approach2(int low, int high) {
        int[] possibilities = new int[]{
                12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234,
                2345, 3456, 4567, 5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456,
                234567, 345678, 456789, 1234567, 2345678, 3456789, 12345678, 23456789, 123456789
        };

        List<Integer> ans = new ArrayList<>();
        for (int p: possibilities) {
            if (p > high) break;

            if (p >= low) ans.add(p);
        }

        return ans;
    }
}
