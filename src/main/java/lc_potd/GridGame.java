package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/grid-game/
//@check - https://www.youtube.com/watch?v=Y8VC_OnkazE&t=1741s&ab_channel=codestorywithMIK
public class GridGame {
    public long gridGame(int[][] grid) {
        // return pass1(grid);
        return pass2(grid);
    }

    /*
        Solution completely based on mik's explanation.
        This problem is based on game strategy.

        Notes:
        1. R1 will try to minimize R2's points. Doesn't matter
        if R1 points is max, min or whatever. All it cares is
        that R2 is left with options such that it can score min.

        2. R2 will play optimally. In every strategy that R1 takes,
        R2 will try to choose max (best) points possible for itself
        with available moves.

        Hint: Whenever question has keywords like "both play optimally"
        then it is very likely that problem is based on game strategy.
    */
    private long pass2(int[][] grid) {
        long row1Remaining = 0;
        long row2Remaining = 0;
        for (int j=0; j<grid[0].length; j++) {
            row1Remaining += grid[0][j];
        }

        long min = Long.MAX_VALUE;
        for (int j=0; j<grid[0].length; j++) {
            row1Remaining -= grid[0][j];
            long r2Choice1 = row1Remaining;
            long r2Choice2 = row2Remaining;

            long r2Choice = Math.max(r2Choice1, r2Choice2);
            min = Math.min(min, r2Choice);

            row2Remaining += (long)grid[1][j];
        }

        return min;
    }

    /*
        Following soln was based on my intial intuition for this question.
        The very first thing that came to my mind seeing this question was
        that it is bactracking/dfs question.
        And although the backtracking algorithm I wrote below was correct,
        but I kinda misunderstood the problem statement and my approach didn't
        work.
        My thought process was that robot 1 will move such that it acquires max
        points first, which would ensure that only least points are left for
        robot 2. However, this assumption is not correct.
        The question doesn't necessarily ask us to maximize robot 1's points.
        All it asks is that robot 1 should move such that it makes elements 0
        such that with possible move options with robot 2, it can collect the
        least points.
     */
    private long pass1(int[][] grid) {
        long[] max = {0l};
        long[] pathSum = {0l};
        List<int[]> maxPath = new ArrayList<>();
        dfs1(grid, 0, 0, max, pathSum, new ArrayList<>(), maxPath);

        for (int[] path: maxPath) {
            System.out.println(Arrays.toString(path));
        }

        for (int[] pos: maxPath) {
            grid[pos[0]][pos[1]] = 0;
        }

        long[] max2 = {0l};
        long[] pathSum2 = {0l};
        dfs2(grid, 0, 0, max2, pathSum2);

        return max2[0];
    }

    private void dfs1(int[][] grid, int i, int j, long[] max, long[] pathSum, List<int[]> path, List<int[]> maxPath) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;
        int temp = grid[i][j];
        grid[i][j] = 0;
        pathSum[0] += temp;
        path.add(new int[] { i, j });
        if (i == (grid.length-1) && j == (grid[0].length-1)) {
            if (pathSum[0] > max[0]) {
                max[0] = pathSum[0];
                maxPath.clear();
                maxPath.addAll(new ArrayList<>(path));
            }
        }


        dfs1(grid, i+1, j, max, pathSum, path, maxPath);
        dfs1(grid, i, j+1, max, pathSum, path, maxPath);

        grid[i][j] = temp;
        pathSum[0] -= temp;
        path.remove(path.size()-1);
    }


    private void dfs2(int[][] grid, int i, int j, long[] max, long[] pathSum) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1) return;
        int temp = grid[i][j];
        grid[i][j] = -1;
        pathSum[0] += temp;
        if (i == (grid.length-1) && j == (grid[0].length-1)) {
            if (pathSum[0] > max[0]) {
                max[0] = pathSum[0];
            }
        }


        dfs2(grid, i+1, j, max, pathSum);
        dfs2(grid, i, j+1, max, pathSum);
        grid[i][j] = temp;
        pathSum[0] -= temp;
    }
}
