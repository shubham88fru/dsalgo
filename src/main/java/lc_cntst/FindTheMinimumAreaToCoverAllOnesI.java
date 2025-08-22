package lc_cntst;

//@link - https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/
public class FindTheMinimumAreaToCoverAllOnesI {
    public int minimumArea(int[][] grid) {
        return revise(grid);
    }

    private int revise(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int cs = Integer.MAX_VALUE;
        int ce = -1;
        int rs = Integer.MAX_VALUE;
        int re = -1;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {
                    cs = Math.min(cs, j);
                    ce = Math.max(ce, j);

                    rs = Math.min(rs, i);
                    re = Math.max(re, i);
                }
            }
        }

        return (Math.abs(cs-ce)+1) * (Math.abs(rs-re)+1);
    }
}
