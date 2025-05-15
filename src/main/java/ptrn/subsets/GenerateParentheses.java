package ptrn.subsets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //0) My soln.
    private void revise(int n, int oc, int cc, List<String> ans, String str) {
        if (oc == n && cc == n) {
            ans.add(str);
            return;
        }

        if (oc > n) return;
        if (cc > n) return;

        if (oc > cc) {
            revise(n, oc, cc+1, ans, str+")");
        }

        revise(n, oc+1, cc, ans, str+"(");
    }

    //3) edctv soln
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

    //2) swd soln.
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


    /* 1)
        Tried the approach used in generate permutations.
        But TLE. So this approach wont' work.
    */
    private List<String> revise(int n) {

        List<Character> parens = new ArrayList<>();
        for (int i=0; i<n; i++) {
            parens.add('(');
            parens.add(')');
        }

        List<String> perms = new ArrayList<>();
        generatePerms(parens, 2*n, perms, new HashSet<>(), new StringBuffer(), 0, 0);

        System.out.println(perms.size() + " " + (new HashSet<>(perms)).size());
        return null;
    }

    private void generatePerms(List<Character> parens, int size, List<String> perm,
                               Set<Integer> st, StringBuffer curr, int oc, int cc) {

        if (cc > oc) return;

        if (curr.length() == size) {
            perm.add(curr.toString());
        }


        for (int i=0; i<size; i++) {
            char ch = parens.get(i);

            if (!st.contains(i)) {
                st.add(i);
                curr.append(ch);
                generatePerms(parens, size, perm, st,
                        curr, (ch=='(' ? oc+1: oc), (ch==')' ? cc+1: cc));
                curr.setLength(curr.length()-1);
                st.remove(i);
            }
        }
    }
}
