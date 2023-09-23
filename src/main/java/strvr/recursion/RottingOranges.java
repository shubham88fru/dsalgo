package strvr.recursion;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/rotting-oranges/description/
//@strvr - https://takeuforward.org/data-structure/rotten-oranges-min-time-to-rot-all-oranges-bfs/
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        return bfs(grid, new boolean[grid.length][grid[0].length]);
    }

    //Note that, if at any momement the grid has rotten
    //oranges, it will start rottening the nearby oranges (if any)
    //simoultaneouly. Hence, its like going to children simoultneously (BFS)
    //instead of covering all children of one parent and then moving to next (DFS).
    //Bottomline, DFS will not work for this question.
    private int bfs(int[][] grid, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        int countEmpty = 0;
        int countFresh = 0;
        int countRotten = 0;

        Deque<Orange> q = new ArrayDeque<>();
        int t = -1; //for the oranges that were already rotten to start with.

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 2) {
                    q.addLast(new Orange(i, j));
                    countRotten += 1;
                } else if (grid[i][j] == 1) {
                    countFresh += 1;
                } else {
                    countEmpty += 1;
                }
            }
        }

        //edge case. If no rotten
        //oragnes to start with..
        if (countRotten == 0) {
            //but there are fresh oranges,
            //its impossible to rot them.
            if (countFresh != 0) return -1;

            //otherwise, the grid is alrady
            //in the final state.
            return 0;
        }

        //apply bfs.
        while (!q.isEmpty()) {
            int size = q.size();

            //time increase only once for a level.
            t += 1;
            while (size > 0) { //process each level.
                Orange orange = q.removeFirst();

                //If can move up/down/left/right, they are fresh and not visited before - rot them.
                int iup = orange.i-1;
                int jup = orange.j;
                if (validPos(m, n, iup, jup) && grid[iup][jup] == 1 && !visited[iup][jup]) {
                    visited[iup][jup] = true;
                    countFresh -= 1;
                    q.addLast(new Orange(iup, jup));

                }

                int idn = orange.i+1;
                int jdn = orange.j;
                if (validPos(m, n, idn, jdn) && grid[idn][jdn] == 1 && !visited[idn][jdn]) {
                    visited[idn][jdn] = true;
                    countFresh -= 1;
                    q.addLast(new Orange(idn, jdn));
                }

                int il = orange.i;
                int jl = orange.j-1;
                if (validPos(m, n, il, jl) && grid[il][jl] == 1 && !visited[il][jl]) {
                    visited[il][jl] = true;
                    countFresh -= 1;
                    q.addLast(new Orange(il, jl));
                }

                int ir = orange.i;
                int jr = orange.j+1;
                if (validPos(m, n, ir, jr) && grid[ir][jr] == 1 && !visited[ir][jr]) {
                    visited[ir][jr] = true;
                    countFresh -= 1;
                    q.addLast(new Orange(ir, jr));
                }

                size -=1;
            }

        }

        //if after bfs, we still have fresh oranges,
        //mean its impossible to rot all oranges.
        if (countFresh != 0) return -1;

        //otherwise, return the time.
        return t;
    }

    private boolean validPos(int m, int n, int i, int j) {
        return (i>=0 && i<m && j>=0 && j<n);
    }
}

class Orange {
    int i;
    int j;

    public Orange(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
