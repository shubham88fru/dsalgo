package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/predict-the-winner/?
//@check - https://www.youtube.com/watch?v=ak3DkeUBo-c
public class PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        // return wrong(nums);
        return mikssol(nums);
    }

    /*
        Coded by me based on mik's approach.
        This problem is based on Game Strategy.
        Any game related problem that mentions
        something like "plays optimally", etc is
        usually always a game strategy problem.
        For a game strategy problem, the most
        important thing to note is -
            1. In your turn do/select your best/max/anything to reduce other's score.
            2. In other's turn assume the worst/min/anything to reduce your score.
        @see

        Mik also showed a second approach to solve such problems.
    */
    private boolean mikssol(int[] nums) {
        int n = nums.length;
        int ts = Arrays.stream(nums).sum();

        int p1 = gamestrategy(nums, 0, n-1);
        return p1 >= ts-p1;
    }

    private int gamestrategy(int[] nums, int i, int j) {

        if (i > j) return 0;

        //expect worst from other player.
        int l = nums[i] + Math.min(gamestrategy(nums, i+2, j), gamestrategy(nums, i+1, j-1));
        int r = nums[j] + Math.min(gamestrategy(nums, i+1, j-1), gamestrategy(nums, i, j-2));

        return Math.max(l, r); //my best.
    }

    /*
        DOESN'T WORK.
        My approach.
        The problem is that this approach
        doesn't take into account the fact that
        the second player will also play optimally
        and try everything to reduce player 1's score.
    */
    private boolean wrong(int[] nums) {
        int n = nums.length;
        int ts = Arrays.stream(nums).sum();
        return recursion(nums, n, ts, 0, 0, n-1);
    }

    private boolean recursion(int[] nums, int n, int ts, int ps, int i, int j) {

        if (i > j) return ps >= (ts-ps);

        boolean ls = recursion(nums, n, ts, ps+nums[i], i+1, j);
        boolean rs = recursion(nums, n, ts, ps+nums[j], i, j-1);

        return (ls || rs);
    }
}
