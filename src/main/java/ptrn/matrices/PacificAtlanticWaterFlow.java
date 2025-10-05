package ptrn.matrices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/pacific-atlantic-water-flow/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5304723076022272
public class PacificAtlanticWaterFlow {

    /*
    * Following is based on my initial intuition to
    * solve the problem. I don't think this is the
    * optimal soln though. This q is part of
    * nc150 so check optimal solution when revising.
    * */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans  = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (
                        (i==m-1 && j==0) ||
                                (i==0 && j==n-1)
                ) ans.add(Arrays.asList(i, j));
                else {
                    int[][] visited = new int[m][n];
                    boolean[] ocean = { false, false };
                    dfs(heights, Integer.MAX_VALUE, i, j, m, n, visited, ocean, ans);
                    if (ocean[0] && ocean[1]) ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] heights, int prev, int i, int j, int m, int n, int[][] visited, boolean[] ocean, List<List<Integer>> ans) {

        if (i < 0 || j < 0) {
            ocean[0] = true;
        }

        if (i >= m || j >= n) {
            ocean[1] = true;
        }

        if (ocean[0] && ocean[1]) {
            return;
        }

        if (
                i < 0 || i >= m ||
                j < 0 || j >= n ||
                visited[i][j] == -1 ||
                heights[i][j] > prev
        ) return;

        visited[i][j] = -1;

        dfs(heights, heights[i][j], i-1, j, m, n, visited, ocean, ans);
        dfs(heights, heights[i][j], i+1, j, m, n, visited, ocean, ans);
        dfs(heights, heights[i][j], i, j-1, m, n, visited, ocean, ans);
        dfs(heights, heights[i][j], i, j+1, m, n, visited, ocean, ans);
    }
}
