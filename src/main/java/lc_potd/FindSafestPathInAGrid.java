package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/
//@check - https://www.youtube.com/watch?v=vsM-uGbLDyU&ab_channel=AryanMittal
public class FindSafestPathInAGrid {
    //(-1, 0) --> left; (1, 0) --> right; (0, -1) --> down; (0, 1) --> up.
    int[] rowDir = {-1, 1, 0, 0};
    int[] colDir = {0, 0, -1, 1};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int[][] distToTheif = new int[n][n];

        //1) put all theif positions in the BFS queue to start with.
        for (int i=0; i < n; i++) {
            for (int j=0; j<n; j++) {
                if (grid.get(i).get(j) == 1) {
                    visited[i][j] = true;
                    q.addLast(new int[]{i, j});
                }
            }
        }

        /**
         Example of how to run a BFS on a matrix
         without forming a graph object explicitly.
         Note that BFS is our choice (over DFS) because somewhere
         the idea is to find min distances, and whenever we have to
         find min distances in a graph, BFS is the way to go.
         */
        //2) Run BFS from theif positions.
        //The aim of this BFS run is to populate the `distToTheif` matrix,
        //which contains the minimum (manhattan) distance of each cell of the `grid`
        //to its' nearest theif position. Since its a BFS, we are guranteed to get
        //the shortest distance and since we have started the BFS with all the theif
        //positions to start with, when the BFS runs, each cell will be populated
        //with the distance from its nearest theif and not from some farther theif.
        //This is a (sort of) parallel BFS running from all the theif positions at start.
        int len = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            //finish the current level (i.e. direct neighbours from the curr.)
            while (size > 0) {
                int[] item = q.removeFirst();
                int currRow = item[0];
                int currCol = item[1];

                //for all nodes are curr level, len is same (manhattan dist)
                distToTheif[currRow][currCol] = len;

                //enque all the nieghbours for each node of current level - 4 directions.
                for (int dirIdx = 0; dirIdx < 4; dirIdx += 1) {
                    int newRow = currRow + rowDir[dirIdx];
                    int newCol = currCol + colDir[dirIdx];
                    if (!isValid(visited, newRow, newCol)) continue;

                    /*
                        Note that marking a cell visited as soon as its enqued is
                        important. First, a cell will be reached first via the BFS that started
                        from the closest theif position, and so marking it visited will ensure that
                        it is not enqued again when is it encountered by the BFS that started
                        from a different (farther) theif position, which will overwrite its
                        distToTheif value.
                    */
                    visited[newRow][newCol] = true;
                    q.addLast(new int[] {newRow, newCol});
                }
                size -= 1;
            }
            len += 1; //note: increasing the len only after each level. This will be manhattan dist 🤯
        }

        /**
         At this point, we have a matrix (distToTheif) which is populated
         with the min distance to its nearest theif. All that we need to
         do now is to start a probe. The probe does the following:
         1. Chooses a possible value of the max safe distance.
         2. Check if we can find a path with that safe distance.
         3. If we can, then we become more ambitious and try to find a path
         with a higher max safe distance.
         else if not, then we try with a lower max safe distance.
         Aim is the find the max possible safe distance for which a path from (0, 0) to (n-1, n-1) exists.
         Note that the min possible max safe distance is zero (a path that passes
         through a theif). And the max possible max safe distance is `len` (which
         at this point will be the max dist from a theif location).
         Optimization: We know that if a max safe distance x (belonging [0, len]) is not possible,
         then there's no point checking any distance between [x+1, len] since they won't be possible
         either. Similarly, if a max safe distance x is possible, then there's no point checking
         any distance between [0, x-1] because we have to return the max possible max safe
         distance. Hence, we can apply a bianry search over the range of possible max
         safe distances to make the search faster.
         */

        //3) Binary search.
        //range: [0, len]
        int low = 0;
        int high = len;

        int ans = 0;
        while (low <= high) {
            int mid = low + (high-low)/2; //check if mid is possible max safe distance.
            if (isSafe(distToTheif, mid)) {
                //if we are able to find a path from (0, 0) to (n-1, n-1)
                //such that the safe distance is atleast mid, record it
                //as a possible answer and check for a higher possible max safe distance value.
                ans = mid;
                low = mid + 1;
            }
            //else if current choice (mid) of max safe distance is not possible,
            //we'll try with a lower value.
            else high = mid - 1;
        }

        return ans;
    }

    //during bfs, check if the cell we are landing on is
    //withing bounds or not already visited.
    private boolean isValid(boolean[][] visited, int i, int j) {
        if (i < 0 ||
                i >= visited.length ||
                j < 0 ||
                j >= visited.length ||
                visited[i][j]
        ) return false;
        return true;
    }

    //This BFS traversal traverses the grid from (0, 0) uptill (n-1, n-1)
    //and tries to pick a cell to move to such that min distance of the
    //cell is atleast equal to `safeDist`. If we are able to do this
    //continuosly and reach the end (n-1, n-1), we return true, indicating
    //that there is a possible path with the given safeDist.
    private boolean isSafe(int[][] distToTheif, int safeDist) {
        int n = distToTheif.length;
        Deque<int[]> q = new ArrayDeque<>();
        if (distToTheif[0][0] < safeDist) return false;

        //start from top left (ATQ)
        q.addLast(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] item = q.removeFirst();
            int currRow = item[0];
            int currCol = item[1];

            //if able to reach the bottom right cell (ATQ), return true and end BFS.
            if (currRow == n-1 && currCol == n-1) return true;

            //on each cell, explore all its valid neighbours,
            //if the neighbours min distance to nearest theif is
            //atleast the given safe distance, else skip it.
            for (int dirIdx = 0; dirIdx < 4; dirIdx += 1) {
                int newRow = currRow + rowDir[dirIdx];
                int newCol = currCol + colDir[dirIdx];
                if (isValid(visited, newRow, newCol)) {
                    if (distToTheif[newRow][newCol] < safeDist) continue;
                    visited[newRow][newCol] = true;
                    q.addFirst(new int[] {newRow, newCol});
                }
            }
        }
        return false;
    }
}
