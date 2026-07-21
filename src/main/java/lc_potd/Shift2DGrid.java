package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/shift-2d-grid/description/?
public class Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // return brute(grid, k);
        return optimal(grid, k);

    }

    private List<List<Integer>> optimal(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, p=0;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i=0; i<m; i++) {
            ans.add(new ArrayList<>());
            for (int j=0; j<n; j++) {
                ans.get(i).add(-1);
            }
        }

        int _k = k%(m*n);
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                int t = p+_k;
                int tm = t%(m*n);
                int nr = tm/n;
                int nc = t%n;
                ans.get(nr).set(nc, grid[i][j]);
                p += 1;
            }
        }

        return ans;
    }

    private List<List<Integer>> brute(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        while (k > 0) {
            int prev = grid[m-1][n-1];
            int i = 0;
            for (int j=0; j<=n; j++) {
                if (j == n) {
                    j = j%n;
                    i += 1;
                }

                int temp = grid[i][j];
                grid[i][j] = prev;
                prev = temp;

                if (i==m-1 && j == n-1) break;
            }

            k -= 1;
        }


        List<List<Integer>> ans = new ArrayList<>();
        for (int p=0; p<m; p++) {
            List<Integer> row = new ArrayList<>();
            for (int q=0; q<n; q++) {
                row.add(grid[p][q]);
            }
            ans.add(row);
        }

        return ans;
    }
}
