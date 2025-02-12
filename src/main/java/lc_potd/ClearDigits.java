package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/clear-digits/
//@check - https://www.youtube.com/watch?v=WI9PhnDxAik&ab_channel=codestorywithMIK
public class ClearDigits {
    public String clearDigits(String s) {
        return pass1(s);
    }

    //1) Using stack.
    //My Soln. Mik showed a inplace soln as well.
    //If frequently occurring problem for some
    //company; @check.
    private String pass1(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty()) {
                stack.addFirst(ch);
            } else if (Character.isDigit(ch)) {
                stack.removeFirst();
            } else {
                stack.addFirst(ch);
            }
        }

        String str = stack.stream().map(String::valueOf).collect(Collectors.joining());
        return (new StringBuffer(str)).reverse().toString();
    }


    //2) Brute force.
    //Take a stringbuffer and keep
    //deleting.
}
