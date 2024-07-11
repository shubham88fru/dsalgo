package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
public class ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                stack.addFirst("(");
                i += 1;
            } else if (s.charAt(i) == ')'){
                StringBuffer sb = new StringBuffer();
                while (!stack.isEmpty()) {
                    String curr = stack.removeFirst();
                    if (curr.equals("(")) break;
                    sb.insert(0, curr);
                }
                stack.addFirst(sb.reverse().toString());
                i += 1;
            } else {
                StringBuffer sb = new StringBuffer();
                while ((i < s.length()) && (s.charAt(i) != '(') && (s.charAt(i) != ')')) {
                    sb.append(s.charAt(i));
                    i += 1;
                }
                stack.addFirst(sb.toString());
            }
        }

        StringBuffer ans = new StringBuffer();
        while (!stack.isEmpty()) {
            ans.insert(0, stack.removeFirst());
        }
        return ans.toString();
    }
}
