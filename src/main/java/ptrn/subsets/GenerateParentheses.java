package ptrn.subsets;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/generate-parentheses/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5264554243391488
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        /* SWD Soln */
        // return generateAllBalancedParenthesis(n, "", 0, 0);

        /* Edctv Soln */
        List<String> ans = new ArrayList<>();
        generateEdctv(n, new ArrayList<>(), 0, 0, ans);
        return ans;
    }

    //edctv soln
    private void generateEdctv(int n, ArrayList<Character> seq, int openCnt, int closeCnt, List<String> ans) {
        if (openCnt == closeCnt && openCnt == n) {
            String outputstr = seq.toString();
            ans.add(outputstr.substring(1, outputstr.length()-1).replace(", ", ""));
        }

        if (openCnt < n) {
            seq.add('(');
            generateEdctv(n, seq, openCnt+1, closeCnt, ans);
            seq.remove(seq.size()-1);
        }

        if (closeCnt < openCnt) {
            seq.add(')');
            generateEdctv(n, seq, openCnt, closeCnt+1, ans);
            seq.remove(seq.size()-1);
        }
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

        //can add `(` only if we've not exhausted them already.
        if (openCount < n)
            open = generateAllBalancedParenthesis(n, expression+"(", openCount+1, closeCount);

        //in a balanced expression, at index in the expression,
        //no. of closed parens will be less or equal to open parens.
        //therefore, we can add a close paren only if we have at least one
        //open paren to balance it.
        if (closeCount < openCount)
            close = generateAllBalancedParenthesis(n, expression+")", openCount, closeCount+1);

        //combine all result and return.
        open.addAll(close);
        return open;
    }
}
