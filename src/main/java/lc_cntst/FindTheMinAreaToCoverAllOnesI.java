package lc_cntst;

//@link - https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/
public class FindTheMinAreaToCoverAllOnesI {
    public int minimumArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int smallestRow = -1;
        int largestRow = -1;
        int smallestCol = Integer.MAX_VALUE;
        int largestCol = Integer.MIN_VALUE;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    //first row which has a one.
                    if (smallestRow == -1) smallestRow = i;
                    largestRow = i; //keep updating the largest row, since i is increasing.

                    smallestCol = Math.min(smallestCol, j); //earliest col that has a one.
                    largestCol = Math.max(largestCol, j); //latest col that has a one.
                }
            }
        }

        return (largestRow-smallestRow + 1) * (largestCol - smallestCol + 1);

    }
}
