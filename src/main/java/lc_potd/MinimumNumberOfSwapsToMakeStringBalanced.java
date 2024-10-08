package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/
//@check - https://www.youtube.com/watch?v=W61jIP-O8lw&t=608s&ab_channel=codestorywithMIK
public class MinimumNumberOfSwapsToMakeStringBalanced {
    public int minSwaps(String s) {
        return mysol(s);
    }

    /**
     This is a completely observation based problem
     and I could not possibly blame myself for not being
     able to solve it.

     @check to revise.

     Idea is that first find all the balanced pairs and remove them.
     Once removed, we'll only be left with all the closing brackets at
     the start and opening brackets at the end.
     Then, just take the count of open brackets (which will be same as closing brackets),
     add one to it and divide by two.
     */
    private int mysol(String s) {
        int n = s.length();

        Deque<Character> stack = new ArrayDeque<>();

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch == '[') stack.addFirst(ch);
            else if (ch == ']' && stack.peekFirst() == '[') {
                stack.removeFirst();
            } else stack.addFirst(ch);
        }

        int openCount = stack.size()/2;
        return (openCount + 1)/2;
    }
}
