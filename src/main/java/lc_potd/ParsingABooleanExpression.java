package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/parsing-a-boolean-expression/
public class ParsingABooleanExpression {
    /**
     * My soln using two stacks.
     * Mik had a soln on the same lines but used one stack and a vector.
     */
    public boolean parseBoolExpr(String expression) {
        Deque<Character> st1 = new ArrayDeque<>();
        Deque<Character> st2 = new ArrayDeque<>();
        Set<Character> ops = new HashSet<>(Arrays.asList('&', '|', '!'));

        for (int i=0; i<expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ops.contains(ch)) st2.addFirst(ch);
            else {
                if (ch == ',') continue;
                if (ch == ')') {
                    int prev = -1;
                    boolean ans = true;
                    char exp = st2.removeFirst();
                    while (!st1.isEmpty() && (st1.peekFirst() != '(')) {
                        char boo = st1.removeFirst();
                        boolean unit = boo == 'f' ? false: true;
                        if (prev == -1) {
                            if (exp == '&' || exp == '|') ans = unit;
                            else ans = !unit;

                            prev = 0;
                        } else {
                            if (exp == '&') {
                                ans = ans && unit;
                            } else if (exp == '|') {
                                ans = ans || unit;
                            } else {
                                ans = !unit;
                            }
                        }
                    }
                    st1.removeFirst();
                    char boo = ans == false ? 'f': 't';
                    st1.addFirst(boo);
                } else {
                    st1.addFirst(ch);
                }
            }
        }

        char boo = st1.peekFirst();
        return boo == 'f' ? false: true;
    }
}
