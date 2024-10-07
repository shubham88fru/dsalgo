package ptrn.stack;

import java.util.*;

//@link - https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6177380034150400
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        return mysol(tokens);
    }

    private int mysol(String[] tokens) {
        Set<String> ops = new HashSet<>(
                Arrays.asList("+", "-", "*", "/")
        );

        Deque<String> stack = new ArrayDeque<>();

        for (int i=0; i<tokens.length; i++) {
            String tok = tokens[i];
            if (stack.isEmpty() || !ops.contains(tok)) stack.addFirst(tok);
            else {
                /*
                    Coz the question says that the given
                    polish notation is always valid.
                    So if a token is encountered, the
                    stack must have two numbers at that
                    point in time.
                */
                String n2 = stack.removeFirst();
                String n1 = stack.removeFirst();
                stack.addFirst(calculate(n1, n2, tok));
            }
        }

        return Integer.parseInt(stack.removeFirst());
    }

    private String calculate(String num1, String num2, String op) {
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        switch(op) {
            case "+":
                return String.valueOf(n1+n2);

            case "-":
                return String.valueOf(n1-n2);

            case "*":
                return String.valueOf(n1*n2);

            case "/":
                return String.valueOf(n1/n2);
        }

        return "";
    }
}
