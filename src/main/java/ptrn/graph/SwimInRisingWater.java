package ptrn.graph;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/swim-in-rising-water/description/
//@check - https://www.youtube.com/watch?v=amvrKlMLuGY&ab_channel=NeetCode
public class SwimInRisingWater {
    /*
        This problem is basically finding a path
        from source (0,0) to the dest (n-1, n-1)
        such that the maximum height observed in that
        path is minimized.
    */
    public int swimInWater(int[][] grid) {
        return ncsol(grid);
    }


    //1) Greedy using slight modification of dijkstra.
    // Following is a soln completely based on nc's explanation.
    //Instead of finding all the paths,
    //greedily chose the most likely path
    //per the dijkstra algorithm.
    private int ncsol(int[][] grid) {
        int n = grid.length;

        int[][] dirs = { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> q =
                new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        int[][] visited = new int[n][n];

        int ans = Integer.MAX_VALUE;
        q.add(new int[]{ grid[0][0], 0, 0 });
        while (!q.isEmpty()) {
            int[] curr = q.remove();

            if (curr[1] == n-1 && curr[2] == n-1) {
                ans = Math.min(ans, curr[0]);
                continue;

                // return curr[0]; //this also works.
            }

            visited[curr[1]][curr[2]] = -1;

            for (int[] dir: dirs) {
                int nextI = curr[1] + dir[0];
                int nextJ = curr[2] + dir[1];

                if (nextI < 0 || nextI >= n || nextJ < 0
                        || nextJ >= n || visited[nextI][nextJ] == -1) continue;

                q.add(new int[] { Math.max(grid[nextI][nextJ], curr[0]), nextI, nextJ });
            }

        }

        return ans;
    }


    //2) Brute force - find all paths from
    //source to dest and then from all the paths
    //choose the one that has the smallest minimum.
}
