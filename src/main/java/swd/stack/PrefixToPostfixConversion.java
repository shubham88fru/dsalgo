package swd.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1
public class PrefixToPostfixConversion {
    private static final Map<Character, Boolean> operators = new HashMap<>();

    static {
        operators.put('*', true);
        operators.put('/', true);
        operators.put('+', true);
        operators.put('-', true);
    }

    String preToPost(String pre_exp) {
        return getPostfixFromPrefix(pre_exp);
    }

    private String getPostfixFromPrefix(String prefix) {
        char[] chars = prefix.toCharArray();
        Deque<String> stack = new ArrayDeque<>();

        //A postfix expression has operators at the start
        //and operands to the end. Since, infix expressions
        //start with operands, we'll start iterating the
        //prefix expression from the end.
        //If we encounter an operator, we'll pop
        //top 2 elements from stack and apend them
        //in below order along with pair of parenthesis and
        //put the formed expression to stack again.
        //Otherwise, if the current char is not an operator,
        //we simply put it to stack and move on.
        for (int i=chars.length-1; i>=0; i--) {
            if (operators.containsKey(chars[i])) {
                String operan1 = stack.removeFirst();
                String operan2 = stack.removeFirst();
                stack.addFirst(operan1 + operan2 + chars[i]);
            } else {
                stack.addFirst(chars[i]+"");
            }
        }

        //Finally, at the end of iteration, whatever
        //is at top of stack is our ans.
        return stack.removeFirst();
    }
}
