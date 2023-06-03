package swd.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1
public class InfixToPostfixConversion {
    private static final Map<String, Integer> precedence
            = new HashMap<>();

    static {
        precedence.put("(", 6);
        precedence.put("^", 5);
        precedence.put("/", 4);
        precedence.put("*", 4);
        precedence.put("+", 3);
        precedence.put("-", 3);
    }

    public String infixToPostfix(String exp) {
        return getPostfixFromInfix(exp);
    }


    /*
        STEPS:
         1. Iterate the infix string from start and for each char -
             a. If '(' --> push to stack
             b. Else if ')' --> keep popping and appending to result
                                untill you pop a corresponding '('.
             c. Else if operator --> while stack is not empty and top of stack is not a ')'
                                     and precendence of curr char is less than the top of stack,
                                     keep popping from stack and appending to the answer.
                                     In either case, finally add the curr element to top of stack.
             d. Else if operand --> append in the result variable.

         2. Finally, once iterated over entire infix, if anything remaing in
            the stack (be it operand or operator), simply append them all to
            the answer.
         3. Note: Infix to postfix conversion is relatively simpler than infix to
                  prefix, so, if we get a question to convert infix to prefix,
                  do infix to postfix first and then follow the algo for postfix
                  to prefix.
    */
    private String getPostfixFromInfix(String infix) {
        Deque<String> stack = new ArrayDeque<>();
        String result = "";
        for (char ch: infix.toCharArray()) {
            if ('(' == ch) stack.addFirst(ch +"");
            else if (')' == ch) {
                while (!stack.isEmpty() && !stack.peekFirst().equals("(")) {
                    result += stack.removeFirst();
                }
                stack.removeFirst(); //remove the "(", prefix and postfix dont' have `(` and `)`
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() &&
                        !stack.peekFirst().equals("(")
                        && precedence.get(ch+"") <= precedence.get(stack.peekFirst())
                ) {
                    result += stack.removeFirst();
                }
                stack.addFirst(ch + "");
            } else {
                result += ch + "";
            }
        }

        while (!stack.isEmpty()) {
            result += stack.removeFirst();
        }


        return result;
    }

    private boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^');
    }
}
