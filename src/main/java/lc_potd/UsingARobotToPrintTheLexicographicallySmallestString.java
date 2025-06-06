package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
//@check - https://www.youtube.com/watch?v=NUMNPFel73Q&ab_channel=codestorywithMIK
public class UsingARobotToPrintTheLexicographicallySmallestString {
    public String robotWithString(String s) {
        return mikssol(s);
    }

    /*
        Got a vague idea after thinking
        but couldn't come up with a concrete
        soln on my own. Took hints from mik.

        Coded by using stack. mik didn't use stack.
    */
    private String mikssol(String s) {
        int n = s.length();

        int[] ng = new int[n]; //next greater.
        ng[n-1] = n-1;
        for (int i=n-2; i>=0; i--) {
            int ch = s.charAt(i);
            //`<=` coz need nearest smallest on right
            //other if we keep `<` only then "mqezoe" will fail.
            if (ch <= s.charAt(ng[i+1])) {
                ng[i] = i;
            } else ng[i] = ng[i+1];
        }

        //maybe we don't really need a stack.
        //i guess pushing and popping to sb is enough.
        StringBuffer sb = new StringBuffer();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            if (stack.isEmpty() || ng[stack.peekFirst()] == ng[i]) {
                stack.addFirst(i);
            } else {
                while (!stack.isEmpty() && s.charAt(ng[i]) >= s.charAt(stack.peekFirst())) {
                    sb.append(s.charAt(stack.removeFirst()));
                }
                stack.addFirst(i);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(s.charAt(stack.removeFirst()));
        }

        return sb.toString();
    }
}
