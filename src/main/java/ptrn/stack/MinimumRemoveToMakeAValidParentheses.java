package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4755429507989504
public class MinimumRemoveToMakeAValidParentheses {
    public String minRemoveToMakeValid(String s) {
        /**
         This problem basically wants us to find the
         positions where the parentheses are unbalanced.
         Once we have those positions, we'll remove those
         unbalanced parens from the og string.
         */
        Deque<Pair> stack = new ArrayDeque<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') stack.addFirst(new Pair(ch, i));
            else if (ch == ')') {
                //if a match found, remove it.
                if (!stack.isEmpty() && stack.peekFirst().paren == '(') stack.removeFirst();
                else stack.addFirst(new Pair(ch, i));
            }
        }

        //At this point, the stack will only have those parens (and their index)
        //which are unbalanced. And so, we go over the og string again and only
        //remove those parens that are unbalanced.
        StringBuffer sb = new StringBuffer(s);
        while (!stack.isEmpty()) {
            Pair p = stack.removeFirst();
            int idx = p.idx;
            sb.deleteCharAt(idx);
        }

        return sb.toString();
    }
}

class Pair {
    char paren;
    int idx;
    public Pair(char paren, int idx) {
        this.paren = paren;
        this.idx = idx;
    }
}
