package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-string-length-after-removing-substrings/
public class MinimumStringLengthAfterRemovingSubstrings {
    public int minLength(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty()) stack.addFirst(ch);
            else if (
                    (ch == 'B' && stack.peekFirst() == 'A') ||
                            (ch == 'D' && stack.peekFirst() == 'C')
            ) {
                stack.removeFirst();
            } else stack.addFirst(ch);
        }

        return stack.size();
    }
}
