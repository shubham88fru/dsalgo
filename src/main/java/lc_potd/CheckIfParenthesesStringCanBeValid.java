package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
//@check - https://www.youtube.com/watch?v=Xw8SkHIu2bI&t=1544s&ab_channel=codestorywithMIK
public class CheckIfParenthesesStringCanBeValid {
    public boolean canBeValid(String s, String locked) {
        return pass3(s, locked);
    }

    /*
        Shamelessly based on Mik's approach.
        Tried a lot but couldn't do by myself.
    */
    private boolean pass3(String s, String locked) {
        int n = s.length();
        if (n%2 != 0) return false;

        Deque<Integer> open = new ArrayDeque<>();
        Deque<Integer> openClose = new ArrayDeque<>();

        for (int i=0; i<n; i++) {
            char chs = s.charAt(i);
            char chl = locked.charAt(i);

            if (chl == '1') {
                if (open.isEmpty()) {
                    if (chs == ')' && openClose.isEmpty()) return false;
                    else if (chs==')') openClose.removeFirst();
                    else open.addFirst(i);
                } else if (chs == ')') open.removeFirst();
                else open.addFirst(i);
            } else {
                openClose.addFirst(i);
            }
        }

        if (open.isEmpty()) return true;

        while (!open.isEmpty() && !openClose.isEmpty()) {
            int idx = open.removeFirst();
            if (idx > openClose.peekFirst()) return false;
            openClose.removeFirst();
        }

        return open.isEmpty();
    }
}
