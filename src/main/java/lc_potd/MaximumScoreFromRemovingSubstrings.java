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
}

