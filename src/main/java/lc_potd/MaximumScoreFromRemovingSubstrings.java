package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/maximum-score-from-removing-substrings/description/
//@check - https://www.youtube.com/watch?v=jxng2IcJyCk&ab_channel=AryanMittal
public class MaximumScoreFromRemovingSubstrings {
    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            return maxScore('a', 'b', s, x, y);
        }

        return maxScore('b', 'a', s, y, x);
    }

    private int maxScore(char f, char s, String str, int larger, int smaller) {
        Deque<Character> stack = new ArrayDeque<>();

        //remove all larger.
        int points = 0;
        int i = 0;
        while (i < str.length()) {
            if (stack.isEmpty()) stack.addFirst(str.charAt(i));
            else if (str.charAt(i) == s && stack.peekFirst() == f) {
                stack.removeFirst();
                points += larger;
            } else {
                stack.addFirst(str.charAt(i));
            }
            i += 1;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }
        sb.reverse();

        //then remove all smaller.
        i = 0;
        while (i<sb.length()) {
            if (stack.isEmpty()) stack.addFirst(sb.charAt(i));
            else if (sb.charAt(i) == f && stack.peekFirst() == s) {
                stack.removeFirst();
                points += smaller;
            } else {
                stack.addFirst(sb.charAt(i));
            }
            i += 1;
        }

        return points;

    }

    /// ////////////
    /*
        My solution. Pretty much similar to aryan's
     */
    public int maximumGain2(String s, int x, int y) {
        return revise(s, x, y);
    }

    private int revise(String s, int x, int y) {
        if (x >= y) {
            return delete(s, 'a', 'b', x, y);
        }

        return delete(s, 'b', 'a', y, x);
    }

    private int delete(String str, char f, char s, int x, int y) {
        int n = str.length();
        Deque<Character> stack = new ArrayDeque<>();
        int score = 0;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (stack.isEmpty() || stack.peekFirst() != f || ch != s) stack.addFirst(ch);
            else if (stack.peekFirst() == f && ch == s) {
                stack.removeFirst();
                score += x;
            }
        }


        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) sb.append(stack.removeFirst());

        n = sb.length();
        for (int i=0; i<n; i++) {
            char ch = sb.charAt(i);
            if (stack.isEmpty() || stack.peekFirst() != f || ch != s) stack.addFirst(ch);
            else if (stack.peekFirst() == f && ch == s) {
                stack.removeFirst();
                score += y;
            }
        }

        return score;

    }
}

