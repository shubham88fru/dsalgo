package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/delete-characters-to-make-fancy-string/submissions/1439899437/
public class DeleteCharacterToMakeFancyString {
    public String makeFancyString(String s) {
        return revise(s);
    }

    /*
    * my approach using stack.
    * */
    private String revise(String s) {
        int n = s.length();
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i=n-1; i>=0; i--) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch != stack.peekFirst()[0]) stack.addFirst(new int[]{ch, 1});
            else if (ch == stack.peekFirst()[0]) {
                if (stack.peekFirst()[1] == 1) stack.addFirst(new int[]{ch, 2});
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append((char)stack.removeFirst()[0]);
        }

        return sb.toString();
    }

    private String optimal(String str) {
        StringBuilder sb = new StringBuilder(str);
        int f = 0;
        int s = 1;
        int t = 2;

        if (str.length() < 3) return str;
        while (t < sb.length()) {
            char atf = sb.charAt(f);
            char atb = sb.charAt(s);
            char att = sb.charAt(t);

            if (atf == atb && atf == att) {
                int ptr = t+1;
                while (ptr < sb.length() && sb.charAt(ptr) == atf) {
                    ptr += 1;
                }
                sb.delete(t, ptr);
            } else {
                f += 1;
                s += 1;
                t += 1;
            }

        }

        return sb.toString();
    }

    private String notsooptimized(String str) {
        StringBuilder sb = new StringBuilder(str);
        int f = 0;
        int s = 1;
        int t = 2;

        if (str.length() < 3) return str;
        while (t < sb.length()) {
            char atf = sb.charAt(f);
            char atb = sb.charAt(s);
            char att = sb.charAt(t);

            if (atf == atb && atf == att) {
                sb.deleteCharAt(t);

            } else {
                f += 1;
                s += 1;
                t += 1;
            }

        }

        return sb.toString();
    }
}
