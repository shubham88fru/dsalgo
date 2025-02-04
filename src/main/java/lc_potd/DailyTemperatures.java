package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {
    /*
        Both are my solutions.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // return optimal(temperatures);
        return simplifiedOptimal(temperatures);
    }

    private int[] simplifiedOptimal(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        Deque<int[]> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            int curr = temperatures[i];
            while (!stack.isEmpty() && curr > stack.peekFirst()[0]) {
                int[] rem = stack.removeFirst();
                ans[rem[1]] = i - rem[1];
            }
            stack.addFirst(new int[] { curr, i});
        }

        return ans;
    }

    private int[] optimal(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];

        Deque<int[]> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            int curr = temperatures[i];
            if (stack.isEmpty()) {
                stack.addFirst(new int[] {curr, i});
            } else if (curr <= stack.peekFirst()[0]) {
                stack.addFirst(new int[] { curr, i });
            } else {
                while (!stack.isEmpty() && curr > stack.peekFirst()[0]) {
                    int[] rem = stack.removeFirst();
                    ans[rem[1]] = i - rem[1];
                }
                stack.addFirst(new int[] { curr, i});
            }
        }

        return ans;
    }
}
