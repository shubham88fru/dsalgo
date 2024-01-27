package swd.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/generate-parentheses/description/
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        return generateAllBalancedParenthesis(n, "", 0, 0);
    }

    private List<String> generateAllBalancedParenthesis(int n, String expression, int openCount, int closeCount) {
        //Since we have same no. of open and closed parens
        //where each is equal to n. Given that input only has
        //n pairs of parens (i.e. equal no. of open and close)
        //and we've ensured that each point we only make recursive
        //calls for valid expression, therefore at this point
        //we must be having a complete expression. So, store it.
        if (closeCount == openCount && closeCount == n) {
            List<String> ans = new ArrayList<>();
            ans.add(expression);
            return ans;
        }

        List<String> open = new ArrayList<>();
        List<String> close = new ArrayList<>();

        //these `if` checks ensure that we only add `(` or `)`
        //if they are legal.

        //can add `(` only if we've not exhausted them alredy.
        if (openCount < n)
            open = generateAllBalancedParenthesis(n, expression+"(", openCount+1, closeCount);

        //in a balanced expression, at at index in the expression,
        //no. of closed parens will be less or equal to open parens.
        //therefore, we can add a close paren only if we have atleast one
        //open paren to balance it.
        if (closeCount < openCount)
            close = generateAllBalancedParenthesis(n, expression+")", openCount, closeCount+1);

        //combine all result and return.
        open.addAll(close);
        return open;
    }
}
