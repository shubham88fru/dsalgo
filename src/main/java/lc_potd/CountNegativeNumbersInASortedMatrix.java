package lc_potd;

//@link - https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/description/?
public class CountNegativeNumbersInASortedMatrix {
    public int countNegatives(int[][] grid) {
        // return brute(grid);
        // return better(grid);
        return optimal(grid);
    }

    private int optimal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int i = 0, j = n-1;

        int count = 0;
        while (i < m && j >= 0) {
            if (grid[i][j] < 0) {
                count += (m-i);
                j -= 1;
            } else {
                i += 1;
            }
        }

        return count;
    }

    private int better(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int count = 0;
        for (int i=0; i<m; i++) {
            int ng = bs(grid[i], n);
            count += (n-ng);
        }

        return count;
    }


    private int brute(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] < 0) count += 1;
            }
        }

        return count;
    }

    private int bs(int[] row, int n) {
        int l = 0, r = n-1;

        int idx = n;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (row[mid] < 0) {
                idx = mid;
                r = mid - 1;
            } else l = mid + 1;
        }

        return idx;
    }
}
