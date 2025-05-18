package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/painting-a-grid-with-three-different-colors/
//@check - https://www.youtube.com/watch?v=Qz8F-nC8oxQ&t=1284s&ab_channel=codestorywithMIK
public class PaintingGridWithThreeDifferentColors {
    public int colorTheGrid(int m, int n) {
        // return pass1(m, n);
        return pass2(m, n);
    }


    /*
        Coded by me based on mik's explanation.
        Idea is to solve column by column
        (since possibilities for column is lesser than for rows,
        according to given constraints)

        Mik only showed this soln but I'm not sure if this is the
        most optimal solution.
    */
    private int pass2(int m, int n) {
        List<String> colPossibilities = new ArrayList<>();
        getColPossibilities(m, "RGB", "", colPossibilities);

        Integer[][] memo = new Integer[colPossibilities.size()+1][n+1];

        return topdowndp(colPossibilities, n, -1, 0, memo);
    }

    private int topdowndp(List<String> states, int n, int picked, int col, Integer[][] memo) {
        if (col == n) return 1;

        if (picked != -1 && memo[picked][col] != null) return memo[picked][col];
        int ways = 0;
        for (int i=0; i<states.size(); i++) {
            if (i == picked) continue;
            if (picked == -1 || check(states.get(picked), states.get(i))) {
                ways = (ways + topdowndp(states, n, i, col+1, memo))%1000000007;
            }
        }

        if (picked != -1) memo[picked][col] = ways;
        return ways;

    }

    private boolean check(String s1, String s2) { //check if any matching chars in columns.
        for (int i=0; i<s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            if (ch1 == ch2) return false;
        }

        return true;
    }

    /*
        Returns a list of all possible
        states of a column.
    */
    private void getColPossibilities(int m, String options, String curr, List<String> possibilities) {
        if (m==0) {
            possibilities.add(curr);
            return;
        }

        for (int i=0; i<options.length(); i++) {
            char ch = options.charAt(i);
            if (curr.length() == 0 || curr.charAt(curr.length()-1) != ch) {
                getColPossibilities(m-1, options, curr+ch, possibilities);
            }
        }
    }

    /*
        Pass1.
        First try at topdown approach.
        Didn't work.

        Mik said that this type of problem
        is not a good fit for trying each value on
        each cell. Because there will be too many possibilities.
        Pass2 tackles this problem column-by-column.
    */
}
