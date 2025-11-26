package lc_potd;

//@link - https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/description/?
//@check - https://www.youtube.com/watch?v=k-9LZZEB1sI
public class PathInMatrixWhoseSumIsDivisibleByK {
    public int numberOfPaths(int[][] grid, int k) {
        // return pass1(grid, k);
        return bottomUp(grid, k);
    }

    /**
     Code by me, based on mik's explanation of
     the bottom up approach. Mik explained the
     process of converting top-down memo approach
     to bottom up approach pretty well and I should
     refer it back when needed.
     */
    private int bottomUp(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m+1][n+1][k];

        //base case of top-down approach
        for (int sum=0; sum<k; sum++) {
            dp[m-1][n-1][sum] = (sum+grid[m-1][n-1])%k == 0 ? 1: 0;
        }


        //3 loops because 3 changing variables in top-down approach.
        for (int i=m-1; i>=0; i--) {
            for (int j=n-1; j>=0; j--) {
                for (int sum=0; sum<k; sum++) {
                    if (i==m-1 && j==n-1) continue; //cause already filled.

                    int modk = (sum+grid[i][j])%k;
                    int down = dp[i+1][j][modk];
                    int right = dp[i][j+1][modk];
                    dp[i][j][sum] = (down+right)%1000000007;
                }
            }
        }
        return dp[0][0][0];
    }

    /**
     Imp concept when running sum and divisible by k is
     involved - Instead of keeping track of sum (which may
     become large and overflow), since we are only interested
     in knowing whether the sum is divisible by k, just keep
     track of sum%k. This will give the same result.

     Following problems are based on this concept too -
     1. https://leetcode.com/problems/smallest-integer-divisible-by-k/description/
     2. https://leetcode.com/problems/binary-prefix-divisible-by-5/
     3. https://leetcode.com/problems/greatest-sum-divisible-by-three/

     Following are my top-down approaches.
     */
    private int pass1(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        Integer[][][] cache = new Integer[m][n][k];
        // return dp1(grid, grid.length, grid[0].length, k, 0, 0, 0, cache);
        return dp2(grid, grid.length, grid[0].length, k, 0, 0, 0, cache);
    }

    private int dp2(int[][] grid, int m, int n, int k, int i, int j, int sum, Integer[][][] cache) {
        if (i == m-1 && j == n-1) {
            if ((sum+grid[i][j])%k == 0) {
                return 1;
            }
            return 0;
        }

        if (i >= m || j >= n) return 0;

        if (cache[i][j][sum] != null) return cache[i][j][sum];

        int modk = (sum + grid[i][j])%k; //mod instead of actual sum.
        int right = dp2(grid, m, n, k, i, j+1, modk, cache);
        int down = dp2(grid, m, n, k, i+1, j, modk, cache);

        int total = (right + down)%1000000007;
        cache[i][j][sum] = total;
        return total;
    }

    private int dp1(int[][] grid, int m, int n, int k, int i, int j, int sum, Integer[][][] cache) {
        if (i == m-1 && j == n-1) {
            if ((sum+grid[i][j])%k == 0) {
                return 1;
            }
            return 0;
        }

        if (i >= m || j >= n) return 0;

        int modk = sum%k;
        if (cache[i][j][modk] != null) return cache[i][j][modk];

        int currSum = sum + grid[i][j];
        int right = dp1(grid, m, n, k, i, j+1, currSum, cache);
        int down = dp1(grid, m, n, k, i+1, j, currSum, cache);

        int total = (right + down)%1000000007;
        cache[i][j][modk] = total;
        return total;
    }
}
