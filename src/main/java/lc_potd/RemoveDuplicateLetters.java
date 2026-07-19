package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/remove-duplicate-letters/
//@check - https://www.youtube.com/watch?v=rU5p0MRm5zU
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // return mikssol(s);
        return cleaner(s);
    }

    /*
        Coded by me completely based on
        mik's approach.
     */
    private String cleaner(String s) {
        int n = s.length();

        int[] lastIndexes = new int[26];
        boolean[] taken = new boolean[26];

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            lastIndexes[ch-'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);

            if (taken[ch-'a']) continue;
            while (!stack.isEmpty() && ch < stack.peekFirst() && lastIndexes[stack.peekFirst()-'a'] > i) {
                taken[stack.peekFirst()-'a'] = false;
                stack.removeFirst();
            }
            stack.addFirst(ch);
            taken[ch-'a'] = true;
        }

        StringBuffer ans = new StringBuffer();
        while (!stack.isEmpty()) ans.append(stack.removeLast());

        return ans.toString();
    }

    private String mikssol(String s) {
        int n = s.length();

        int[] lastIndexes = new int[26];
        boolean[] taken = new boolean[26];

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            lastIndexes[ch-'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);

            if (taken[ch-'a']) continue;
            if (stack.isEmpty() || (stack.peekFirst() < ch)) {
                stack.addFirst(ch);
                taken[ch-'a'] = true;
            } else {
                while (!stack.isEmpty() && ch < stack.peekFirst() && lastIndexes[stack.peekFirst()-'a'] > i) {
                    taken[stack.peekFirst()-'a'] = false;
                    stack.removeFirst();
                }
                stack.addFirst(ch);
                taken[ch-'a'] = true;
            }
        }

        StringBuffer ans = new StringBuffer();
        while (!stack.isEmpty()) {
            ans.append(stack.removeLast());
        }

        return ans.toString();
    }
}
