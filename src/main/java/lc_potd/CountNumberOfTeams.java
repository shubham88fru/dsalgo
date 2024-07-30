package lc_potd;

//@link - https://leetcode.com/problems/count-number-of-teams/description/
public class CountNumberOfTeams {
    public int numTeams(int[] rating) {
        return  larger(rating, 0, -1, 0, new Integer[rating.length][rating.length+1][3])
                + smaller(rating, 0, -1, 0, new Integer[rating.length][rating.length+1][3]);
    }

    private int smaller(int[] rating, int curr, int prev, int count, Integer[][][] memo) {
        if (count == 3) {
            return 1;
        }

        if (curr >= rating.length) return 0;
        if (memo[curr][prev+1][count] != null) return memo[curr][prev+1][count];

        int select = 0;
        if (prev == -1 || rating[curr] < rating[prev]) {
            select = smaller(rating, curr+1, curr, count + 1, memo);
        }

        int notSelect = smaller(rating, curr+1, prev, count, memo);

        memo[curr][prev+1][count] = select + notSelect;
        return memo[curr][prev+1][count];
    }

    private int larger(int[] rating, int curr, int prev, int count, Integer[][][] memo) {
        if (count == 3) {
            return 1;
        }

        if (curr >= rating.length) return 0;

        if (memo[curr][prev+1][count] != null) return memo[curr][prev+1][count];

        int select = 0;
        if (prev == -1 || rating[curr] > rating[prev]) {
            select = larger(rating, curr+1, curr, count + 1, memo);
        }

        int notSelect = larger(rating, curr+1, prev, count, memo);

        memo[curr][prev+1][count] = select + notSelect;
        return memo[curr][prev+1][count];
    }
}
