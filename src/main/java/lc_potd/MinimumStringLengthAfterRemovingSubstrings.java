package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-string-length-after-removing-substrings/
//@check - https://www.youtube.com/watch?v=4XLzLdAE4Lc&ab_channel=codestorywithMIK
public class MinimumStringLengthAfterRemovingSubstrings {

    /*
        My sol. TC: O(N), SC: O(N)
        Mik showed an approach that is O(1) SC.
     */
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
