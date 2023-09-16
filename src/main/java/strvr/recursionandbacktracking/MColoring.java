package strvr.recursionandbacktracking;

//@link - https://practice.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1#
//@strvr - https://takeuforward.org/data-structure/m-coloring-problem/
public class MColoring {
    public boolean graphColoring(boolean[][] graph, int m, int n) {
        return dfs(graph, m, n, 0, new int[n+1], 0);
    }

    private boolean dfs(boolean[][] graph, int m, int n, int curr, int[] colors, int colorCount) {
        if (curr >= n) return true;

        for (int i=1; i<=m; i++) {
            if (canColor(graph, curr, colors, i, n)) {
                colors[curr] = i;
                if (dfs(graph, m, n, curr+1, colors, colorCount+1)) return true;
                colors[curr] = 0;
            }
        }
        //System.out.println("Couldn't color" + curr + " at all");
        return false;
    }

    private boolean canColor(boolean[][] graph, int node, int[] colors, int color, int n) {
        for (int i=0; i<n; i++) {
            if (graph[node][i] && colors[i] == color) {
                //System.out.println("Can't color " + node + " " + color);
                return false;
            }
        }
        //System.out.println("Can color " + node + " " + color);
        return true;
    }
}
