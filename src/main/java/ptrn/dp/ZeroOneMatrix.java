package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/01-matrix/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5695289679413248
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        //return dfs1(mat);
        //return dfs2(mat);
        return bottomup(mat);
    }

    //1) Edctv solution.
    private int[][] bottomup(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        //first pass - update each cell with min from up and left.
        for (int i=0; i < m; i++) {
            for (int j=0; j < n; j++) {
                if (mat[i][j] != 0) {
                    /**
                     Note that we only need to check the element just before and
                     above, because in each iteration, we are ensuring that a cell
                     is updated with the least distance from it to a zero. So, for
                     a cell, its closest distance to a zero (by moving left or up)
                     will be 1 plus minimum from its left and up.
                     */
                    //check the element above, if there's no elment above, set to infi.
                    int upd = (i > 0) ? mat[i-1][j] : Integer.MAX_VALUE - 1000;

                    //check the left element, if there's no left element, set to infi.
                    int leftd = (j > 0) ? mat[i][j-1] : Integer.MAX_VALUE - 1000;

                    //Update the current element with the minimum of element above
                    //and left, +1
                    mat[i][j] = Math.min(upd, leftd) + 1;
                }
            }
        }

        /**
         At this point, we know the minimum distance to 0 from each cell
         when we could only move up or left. Now, we will traverse the matrix
         again from bottom-right to top-left and compute the minimum distance to
         0 again but this time, we'll look for closest 0's on right and down.
         */
        //Second pass - bottom right to top left.
        for (int i=m-1; i>=0; i--) {
            for (int j=n-1; j>=0; j--) {
                if (mat[i][j] != 0) {
                    // check the element below, if there's no element below, set to infi.
                    int down = (i < m-1) ? mat[i+1][j]: Integer.MAX_VALUE-1000;

                    //check the right element, if there's no right element, set to infi.
                    int right = (j < n-1) ? mat[i][j+1]: Integer.MAX_VALUE - 1000;

                    //update the current element with the min of its value (note curr value at this
                    //points is already the min you could get by moving up or left) ,element below
                    //and to its right, +1
                    mat[i][j] = Math.min(mat[i][j], Math.min(down+1, right+1));
                }
            }
        }

        return mat;
    }


    //2) My dfs solution.
    //Gives, TLE. DFS is not the best approach
    //for this problem b/c it is basically a shorted distance problem.
    //This problem is very similar to the rotten oranges/tomatoes problem and ideal
    //approach to solve this problem is BFS or tabulation dp.
    private int[][] dfs1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                //Note: we need a fresh cache everytime
                //we start a new dfs. Can't use a global cache
                //between vairous dfs'. Backtracking will screw
                //the results, if we use prepopulated cache from
                //and older dfs to a next dfs.
                Map<String, Integer> cache = new HashMap<>();
                if (mat[i][j] != 0) {
                    ans[i][j] = find(mat, i, j, m, n, cache);
                } else ans[i][j] = 0;
            }
        }

        return ans;
    }

    private int find(int[][] mat, int i, int j, int m, int n, Map<String, Integer> cache) {
        if (i >= m || i<0 || j>=n || j<0 || mat[i][j] == -1) return 999999999;
        if (mat[i][j] == 0) return 0;

        String key = i + "_" + j;
        if (cache.containsKey(key)) return cache.get(key);

        mat[i][j] = -1;
        int up = 1 + find(mat, i-1, j, m, n, cache);
        int down = 1 + find(mat, i+1, j, m, n, cache);
        int left = 1 + find(mat, i, j-1, m, n, cache);
        int right = 1 + find(mat, i, j+1, m, n, cache);
        mat[i][j] = 1;

        cache.put(key, Math.min(up, Math.min(down, Math.min(left, right))));
        return cache.get(key);
    }

    //3) Someone else's dfs solution on leetcode. Barely runs.
    //Not sure, why they are rercursing on find a 0 instead of 1.
    private int[][] dfs2(int[][] mat) {
        int[][] visit = new int[mat.length][mat[0].length];
        for (int i=0; i<mat.length; i++){
            for (int j=0; j<mat[0].length; j++){
                if (mat[i][j] == 0){
                    find2(mat, i, j, 0, visit);
                }
            }
        }
        return mat;
    }

    public void find2(int[][] mat, int row, int col, int v, int[][] visit){
        if (row < 0 || row >= mat.length || col < 0 || col >= mat[0].length || (visit[row][col] == 1 && mat[row][col] <= v)){
            return;
        }
        if (mat[row][col] == 0 && v != 0){
            return;
        }
        mat[row][col] = v;
        visit[row][col] = 1;
        find2(mat, row, col-1, v+1, visit);
        find2(mat, row, col+1, v+1, visit);
        find2(mat, row-1, col, v+1, visit);
        find2(mat, row+1, col, v+1, visit);
    }
}
