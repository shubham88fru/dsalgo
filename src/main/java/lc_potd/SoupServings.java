package lc_potd;

//@link - https://leetcode.com/problems/soup-servings/description/
//@check -
public class SoupServings {
    public double soupServings(int n) {
        // return pass1(n);
        return pass2(n);
    }

    /*
        Although, this is a typical db template
        however, I could never have come up with
        the nuances.
     */
    private double pass2(int n) {
        if (n >= 5_000) return 1; //No way in hell I can come up with this in an interview.

        int[][] servings = {{100, 0}, {75, 25}, {50, 50}, {25, 75}};
        Double[][] dp = new Double[n+1][n+1];
        return dp2(n, n, servings, dp);
    }

    private double dp2(int a, int b, int[][] servings, Double[][] dp) {
        //couldn't have thought of this.
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;

        if (dp[a][b] != null) return dp[a][b];

        double probability = 0.0;
        for (int[] serving: servings) {
            probability += 0.25*(dp2(a-serving[0], b-serving[1], servings, dp)); //couldn't have thought this way.
        }

        dp[a][b] = probability;
        return dp[a][b];
    }


    /**
     This was my first intuition.
     Calculate the total sample size
     and use standard probability formula.
     However, this doesn't seem to work.
     */
    private double pass1(int n) {
        int[] ac = {0};
        int[] bc = {0};
        int[] abc = {0};

        dp(n, n, bc, ac, abc);
        return 0.0;
    }

    private void dp(int a, int b, int[] bc, int[] ac, int[] abc) {

        if (a <= 0 && b <= 0) {
            abc[0] += 1;
            return;
        }

        if (a <= 0) {
            ac[0] += 1;
            return;
        }

        if (b <= 0) {
            // bc[0] += 1;
            return;
        }

        bc[0] += 4;
        dp(a-100, b, bc, ac, abc);
        dp(a-75, b-25, bc, ac, abc);
        dp(a-50, b-50, bc, ac, abc);
        dp(a-25, b-75, bc, ac, abc);

    }
}
