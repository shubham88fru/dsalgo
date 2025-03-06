package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/find-missing-and-repeated-values/description/
//@check - https://www.youtube.com/watch?v=9tUv0SfUyGg&ab_channel=codestorywithMIK
public class FindMissingAndRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        // return brute(grid);
        return optimal(grid);
    }

    //Optimal approach based on Mik's explanation.
    //TC: O(N^2)
    //SC: O(1)
    private int[] optimal(int[][] grid) {
        long n = grid.length;

        long N = n*n;
        long sumOfFirstN = (N*(N+1))/2;
        long gridSum = 0;
        long gridSqSum = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                gridSum += grid[i][j];
                gridSqSum += (grid[i][j]*grid[i][j]);
            }
        }

        long aMinusB = gridSum - sumOfFirstN;

        long sumOfFirstNSq = (N*(N+1)*(2*N+1))/6;
        long aSqMinusBSq = gridSqSum - sumOfFirstNSq;

        long a = ((aSqMinusBSq/aMinusB) + aMinusB)/2;
        long b = ((aSqMinusBSq/aMinusB) - aMinusB)/2;

        return new int[] {(int)a, (int)b};
    }

    //T: O(N^2)
    //S: O(N^2)
    private int[] brute(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                freq.put(grid[i][j], freq.getOrDefault(grid[i][j], 0)+1);
            }
        }

        int[] ans = {-1, -1};
        for (int i=1; i<=n*n; i++) {
            if (!freq.containsKey(i)) ans[1] = i;
            else if (freq.get(i) == 2) ans[0] = i;
            if (ans[0] != -1 && ans[1] != -1) break;
        }

        return ans;
    }
}
