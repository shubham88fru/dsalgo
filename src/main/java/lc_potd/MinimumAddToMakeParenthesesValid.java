package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        return mysol(s);
    }

    private int mysol(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        /*
            Iterate over the string and remove all pairs
            that are already balanced.
        */
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch == '(') stack.addFirst(ch);
            else if (ch == ')' && (stack.peekFirst() == '(')) {
                stack.removeFirst();
            } else stack.addFirst(ch);
        }

        /**
         At the end, only parens that are unpaired will
         be remaining and we'll need to add the counterpart
         for each to balance them.
         */
        return stack.size();
    }
}
