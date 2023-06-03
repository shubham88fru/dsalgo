package swd.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/postfix-to-infix-conversion/1
public class PostfixToInfixConversion {
    private static final Map<Character, Boolean> operators = new HashMap<>();

    static {
        operators.put('*', true);
        operators.put('/', true);
        operators.put('+', true);
        operators.put('-', true);
    }

    String postToInfix(String exp) {
        return getInfixFromPostfix(exp);
    }

    private String getInfixFromPostfix(String postfix) {
        char[] chars = postfix.toCharArray();
        Deque<String> stack = new ArrayDeque<>();

        //A postfix expression has operands at the start
        //and operator to the end. Since, prefix expressions
        //start with operators, we'll start iterating the
        //postfix expression from the start.
        //If we encounter an operator, we'll pop
        //top 2 elements from stack and apend them
        //in below order (operan2 before operan1 because when iterating from
        //begining and pushing to stack, the operand which is first is popped second
        //due to FIFO nature of stack) along with pair of parenthesis and
        //put the formed expression to stack again.
        //Otherwise, if the current char is not an operator,
        //we simply put it to stack and move on.
        for (int i=0; i < chars.length; i++) {
            if (operators.containsKey(chars[i])) {
                String operan1 = stack.removeFirst();
                String operan2 = stack.removeFirst();
                stack.addFirst("(" + operan2 + chars[i] + operan1 + ")");
            } else {
                stack.addFirst(chars[i]+"");
            }
        }

        //Finally, at the end of iteration, whatever
        //is at top of stack is our ans.
        return stack.removeFirst();
    }
}
