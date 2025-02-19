package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/construct-smallest-number-from-di-string/
public class ConstructSmallestNumberFromDIString {
    public String smallestNumber(String pattern) {
        StringBuffer sb = new StringBuffer();
        int prevNum = -1;
        char prevChar = '\0';
        boolean[] found = {false};
        pass1(pattern, sb, prevNum, prevChar, 0, new HashSet<>(), found);
        return sb.toString();
    }

    /*
    * Following is my backtracking solution.
    * This problem can also be solved using stacks and
    * Mik showed the stack approach for this problem.
    *  */
    private void pass1(String pattern, StringBuffer sb, int prevNum, char prevChar, int idx, Set<Integer> used, boolean[] found) {

        for (int i=1; i<=9; i++) {
            if (found[0]) return;
            if (used.contains(i)) continue;
            if (prevChar == 'I' && i < prevNum) return;
            if (prevChar == 'D' && i > prevNum) return;

            if (idx >= pattern.length()) {
                sb.append(i);
                found[0] = true;
                return;
            }

            char curr = pattern.charAt(idx);

            sb.append(i);
            used.add(i);
            pass1(pattern, sb, i, curr, idx+1, used, found);
            if (found[0]) return;
            sb.deleteCharAt(sb.length()-1);
            used.remove(i);

        }
    }
}
