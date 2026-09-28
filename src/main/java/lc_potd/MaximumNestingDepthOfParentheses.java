package lc_potd;

//@link - https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/?
public class MaximumNestingDepthOfParentheses {
    public int maxDepth(String s) {
        return revise(s);
    }

    private int revise(String s) {
        int n = s.length();
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') count += 1;
            else if (ch == ')') count -= 1;

            max = Math.max(max, count);
        }

        return max;
    }
}
