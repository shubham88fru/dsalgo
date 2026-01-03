package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/
//@check - https://www.youtube.com/watch?v=Qz8F-nC8oxQ&t=1284s&ab_channel=codestorywithMIK
public class NumberOfWaysToPaintNBy3Grid {
    public int numOfWays(int n) {
        // return pass2(n);
        return mikssol(n);
    }

    private int mikssol(int n) {
        List<String> possibilities = new ArrayList<>();
        char[] colors = {'R', 'Y', 'G'};
        generatePossibilities(possibilities, colors, "");

        int ans = 0;
        for (int i=0; i<possibilities.size(); i++) {
            Integer[][] memo = new Integer[13][n+1];
            ans = (ans + dp(i, n-1, possibilities, memo))%1000000007;
        }

        return ans;
    }

    private int dp(int pi, int n, List<String> possibilities, Integer[][] memo) {
        if (n <= 0) return 1;

        if (memo[pi][n] != null) return memo[pi][n];

        int res = 0;
        for (int i=0; i<12; i++) {
            if (i == pi) continue;

            String prev = possibilities.get(pi);
            boolean okay = true;
            for (int col=0; col<3; col+=1) {
                String next = possibilities.get(i);
                if (prev.charAt(col) == next.charAt(col)) {
                    okay = false;
                    break;
                }
            }

            if (okay) {
                res = (res + dp(i, n-1, possibilities, memo))%1000000007;
            }
        }

        memo[pi][n] = res;
        return res;
    }

    private void generatePossibilities(List<String> possibilities, char[] colors, String perm) {
        if (perm.length() == 3) {
            possibilities.add(perm);
            return;
        }

        for (char ch: colors) {
            if (perm.length() == 0 || perm.charAt(perm.length()-1) != ch) {
                generatePossibilities(possibilities, colors, perm+ch);
            }
        }
    }


    /**
     * @see lc_potd.PaintingGridWithThreeDifferentColors
     */
    /*
        Coded by me based on mik's explanation for problem - PaintingGridWithThreeDifferentColors
        Idea is to solve row by row
        (since possibilities for row is lesser than for cols,
        according to given constraints)

        I'm not sure if this is the best approach though because
        this is not bottom up approach.
    */
    private int pass2(int n) {
        List<String> rowStates = new ArrayList<>();
        getRowStates(3, "RGB", "", rowStates);

        Integer[][] memo = new Integer[rowStates.size()+1][n+1];

        return topdowndp(rowStates, n, -1, 0, memo);
    }

    private int topdowndp(List<String> states, int n, int picked, int row, Integer[][] memo) {
        if (row == n) return 1;

        if (picked != -1 && memo[picked][row] != null) return memo[picked][row];
        int ways = 0;
        for (int i=0; i<states.size(); i++) {
            if (i == picked) continue;
            if (picked == -1 || check(states.get(picked), states.get(i))) {
                ways = (ways + topdowndp(states, n, i, row+1, memo))%1000000007;
            }
        }

        if (picked != -1) memo[picked][row] = ways;
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
        states of a row.
    */
    private void getRowStates(int n, String options, String curr, List<String> possibilities) {
        if (n==0) {
            possibilities.add(curr);
            return;
        }

        for (int i=0; i<options.length(); i++) {
            char ch = options.charAt(i);
            if (curr.length() == 0 || curr.charAt(curr.length()-1) != ch) {
                getRowStates(n-1, options, curr+ch, possibilities);
            }
        }
    }
}
