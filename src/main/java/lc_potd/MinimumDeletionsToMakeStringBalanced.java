package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/description/
public class MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        return stack(s);
    }

    /*
      My stack soln.
     */
    private int stack(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        int ops = 0;
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (stack.isEmpty() || ch == 'b' || (stack.peekFirst() == 'a')) {
                stack.addFirst(ch);
            } else {
                ops +=1;
                stack.removeFirst();
            }
        }

        return ops;
    }


    //My top-dwn dp soln. Gives TLE.
    private int mySoln(String s) {
        return solve(s, 0, 0, 0, 0, new Integer[99][99][s.length()][s.length()]);
    }

    private int solve(String orig, int last, int slast, int idx, int bc, Integer[][][][] memo) {

        // if (bc != 0 && curr.charAt(curr.length()-1) == 'a') return 1000001;
        if (bc != 0 && last == 97) return 1000001;
        if (idx >= orig.length()) return 0;

        // String key = curr + "_" + idx + "_" + bc;
        // if (memo.containsKey(key)) return memo.get(key);
        if (memo[last][slast][idx][bc] != null) return memo[last][slast][idx][bc];

        int ch = orig.charAt(idx);
        int select = solve(orig, ch, last, idx + 1, (ch == 'b' ? bc + 1 : bc), memo);

        int delete = 1 + solve(orig, last, slast, idx + 1, bc, memo);

        // memo.put(key, Math.min(select, delete));
        // return memo.get(key);
        memo[last][slast][idx][bc] = Math.min(select, delete);
        return memo[last][slast][idx][bc];
    }
}
