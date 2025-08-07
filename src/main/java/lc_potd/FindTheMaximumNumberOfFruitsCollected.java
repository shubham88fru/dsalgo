package lc_potd;

//@link - https://leetcode.com/problems/find-the-maximum-number-of-fruits-collected/description/
//@check - https://www.youtube.com/watch?v=K9-ZV-_UJ0Q&ab_channel=codestorywithMIK
public class FindTheMaximumNumberOfFruitsCollected {
    public int maxCollectedFruits(int[][] fruits) {
        return miksTopDown(fruits);
    }

    /**
     The fact that each player can
     make atmost n-1 moves is the single
     most important observation for this problem.
     It basically brings down the question to a simple
     classic dp on grid problem.

     Coded by me based on mik's explanation.
     Mik also showed bottom-up approach.
     */
    private int miksTopDown(int[][] fruits) {
        int n = fruits.length;

        //p1 has no choice but to only
        //move diagonally.
        int p1 = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) p1 += fruits[i][j];
            }
        }

        //p2 can only move in the upper diagonal.
        Integer[][] dp2 = new Integer[1001][1001];
        int p2 = maxP2(fruits, 0, n-1, n, dp2);

        //p3 can only move in lower diagonal.
        Integer[][] dp3 = new Integer[1001][1001];
        int p3 = maxP3(fruits, n-1, 0, n, dp3);

        // System.out.println(p1 + " " + p2 + " " + p3);

        return p1 + p2 + p3;
    }

    private int maxP2(int[][] fruits, int i, int j, int n, Integer[][] dp) {
        if (i==n-1 && j==n-1) return 0;
        if (i >= j) return 0;
        if (j < 0 || j >= n || i >= n) return 0;

        if (dp[i][j] != null) return dp[i][j];

        int ld = fruits[i][j] + maxP2(fruits, i+1, j-1, n, dp);
        int d = fruits[i][j] + maxP2(fruits, i+1, j, n, dp);
        int rd = fruits[i][j] + maxP2(fruits, i+1, j+1, n, dp);

        dp[i][j] = Math.max(ld, Math.max(d, rd));
        return dp[i][j];
    }

    private int maxP3(int[][] fruits, int i, int j, int n, Integer[][] dp) {
        if (i==n-1 && j==n-1) return 0;
        if (i <= j) return 0;
        if (i < 0 || j >= n || i >= n) return 0;

        if (dp[i][j] != null) return dp[i][j];

        int tr = fruits[i][j] + maxP3(fruits, i-1, j+1, n, dp);
        int r = fruits[i][j] + maxP3(fruits, i, j+1, n, dp);
        int rd = fruits[i][j] + maxP3(fruits, i+1, j+1, n, dp);

        dp[i][j] = Math.max(tr, Math.max(r, rd));
        return dp[i][j];
    }
}
