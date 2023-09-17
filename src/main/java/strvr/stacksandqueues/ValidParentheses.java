package strvr.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/valid-parentheses/description/
//@strvr - https://takeuforward.org/data-structure/check-for-balanced-parentheses/
public class ValidParentheses {
    private static final Map<Character, Character> pairs;

    //Initialize a map of pairs (open and closed parens)
    static {
        pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');
    }


    public boolean isValid(String s) {
        return areParensBalanced(s);
    }

    private boolean areParensBalanced(String s) {
        //Initialize a stack. (using java's arraydeque as a stack.)
        Deque<Character> stack = new ArrayDeque<>();

        //Iterate over each char in the string.
        for (char ch: s.toCharArray()) {
            //If its open paren, add it to stack.
            if (pairs.containsKey(ch)) stack.addFirst(ch);

                //Else if not an open paren and the stack is still empty
                //means the string starts with a closed paren, which is definitely
                //not a balanced expression, so immediately end.
            else if (stack.isEmpty()) return false;

                //Here, means curr char is certainly a closed paren, so
                //we check if the latest open paren (top of stack) is pairing
                //with this closed paren, if yes, move on to next char. If no,
                //means string isn't a balanced expression.
            else if (ch != pairs.get(stack.removeFirst())) return false;
        }

        //Finally after processing the entire string, and still not exiting,
        //means string may be balanced. But, even at this point we can only be sure
        //that the string is balanced if the stack is empty i.e. for every opening
        //there was a compliant closing. eg: "(((((" this string will also come here
        //without exiting early, but this is not balanced as this is not closed.
        return stack.isEmpty();
    }
}
