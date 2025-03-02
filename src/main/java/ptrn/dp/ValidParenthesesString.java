package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/valid-parenthesis-string/description/
//@check - https://www.youtube.com/watch?v=QhPdNS143Qg&t=618s
public class ValidParenthesesString {
    public boolean checkValidString(String s) {
        return pass2(s);
    }

    //1) There's a greedy soln which runs in O(N)
    //But its nasty. NC showed that.

    //2) My DP + Memo soln.
    private boolean pass2(String s) {
        //just keeping count of open count.
        //instead of open and close both. Its a bit
        //clever.
        return dp(s, 0, 0, new HashMap<>());
    }

    private boolean dp(String s, int i, int oc, Map<String, Boolean> memo) {
        if (i>=s.length() && oc==0) return true;
        if (i>=s.length()) return false;
        if (oc < 0) return false;

        String key = i + "_" + oc;
        if (memo.containsKey(key)) return memo.get(key);

        boolean normal = false;
        boolean wild = false;
        if (s.charAt(i) == '*') {
            wild = dp(s, i+1, oc-1, memo) || dp(s, i+1, oc+1, memo) || dp(s, i+1, oc, memo);

        } else if (s.charAt(i) == '(') {
            normal = dp(s, i+1, oc+1, memo);
        } else {
            normal = dp(s, i+1, oc-1, memo);
        }

        memo.put(key, (normal || wild));
        return memo.get(key);
    }
}
