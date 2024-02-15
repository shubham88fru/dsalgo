package ptrn.backtracking;

import java.util.Arrays;

//@link - https://leetcode.com/problems/matchsticks-to-square/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4624316152152064
//@tag - TO_REVISIT
public class MatchsticksToSquare {
    public boolean makesquare(int[] matchsticks) {
        //square is not possible with less than
        //four squares.
        int n = matchsticks.length;
        if (n < 4) return false;

        int sum = 0;
        for (int i=0; i < n; i++) {
            sum += matchsticks[i];
        }

        //since each side must be included in the square
        //the sum of the array must be a multiple of 4.
        //Otherwise, a square is not possible.
        if (sum%4 != 0) return false;

        int each = sum/4;

        // sort the array and place the largest sides first. required optimization to not TLE
        Arrays.sort(matchsticks);
        return mkSquare(matchsticks, matchsticks.length - 1, 0, 0, 0, 0, each);
    }

    private boolean mkSquare(int[] matchsticks, int index, int top, int bottom, int left, int right, int target) {

        if (top == target && bottom == target && left == target && right == target) return true;

        if (top > target || bottom > target || left > target || right > target) return false;

        int val = matchsticks[index];

        boolean t = mkSquare(matchsticks, index - 1, top + val, bottom, left, right, target);
        if (t) return true;
        boolean b = mkSquare(matchsticks, index - 1, top, bottom + val, left, right, target);
        if (b) return true;
        boolean l = mkSquare(matchsticks, index - 1, top, bottom, left + val, right, target);
        if (l) return true;
        boolean r = mkSquare(matchsticks, index - 1, top, bottom, left, right + val, target);
        if (r) return true;

        return false;
    }
}
