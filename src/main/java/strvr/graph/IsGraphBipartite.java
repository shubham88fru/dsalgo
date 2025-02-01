package strvr.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
    Bipartite graph
    ---------------
    It is an undirected graph such that all its vertices can be divided in 2
    subsets such that no two vertices sharing an edge belong to the same
    subset.

    In layman terms, if we can color each node of the graph, only in two colors,
    such that no two adjacent nodes (nodes having an edge in between) have the
    same color, then we can say that the graph is bipartite.

    By observation, a graph is bipartite when it is either acyclic (i.e. no cycle)
    or if it is cyclic then the no. of vertices in the cycle should be even.
 */
//@link - https://leetcode.com/problems/is-graph-bipartite/
//@strvr - https://www.youtube.com/watch?v=-vu34sct1g8&t=1s&ab_channel=takeUforward
//@check - https://www.youtube.com/watch?v=NeU-C1PTWB8&t=1583s&ab_channel=codestorywithMIK
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        //visited array will keep track of which bucket/color (0 or 1)
        //a vertex lies in.
        int[] visited = new int[n];
        Arrays.fill(visited, -1); //-1 means vertex not visited yet.

        for (int i=0; i < n; i++) {
            //if vertex not visited, visit and color it.
            if (visited[i] == -1) {
                //if (!isBipartiteDfs(graph, visited, i, 0)) return false;
                if (!isBipartiteBfs(graph, visited, i)) return false;
            }
        }

        return true;
    }

    /*
    * Below is Mik's DFS approach, feels more intuitive.
    * */
    private boolean mikDFS(int[][] graph) {
        int n = graph.length;

        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i=0; i<n; i++) {
            if (colors[i] == -1) {
                if (!isBipartiteMikDFS(graph, colors, i, 0)) return false;
            }
        }

        return true;
    }

    private boolean isBipartiteMikDFS(int[][] graph, int[] colors, int curr, int color) {
        colors[curr] = color;

        for (int ngbr: graph[curr]) {
            if (colors[ngbr] == color) {
                return false;
            }

            if (colors[ngbr] == -1) {
                if (!isBipartiteMikDFS(graph, colors, ngbr, 1-color)) return false;
            }
        }

        return true;
    }

    /*
    * Based on mik's intuition.
    * */
    private boolean mikBFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);

        for (int i=0; i<n; i++) {
            if (colors[i] == -1) {
                if (!isBipartiteMikBFS(graph, colors, i, 0)) return false;
            }
        }

        return true;
    }

    private boolean isBipartiteMikBFS(int[][] graph, int[] colors, int n, int color) {

        Deque<int[]> q = new ArrayDeque<>();

        q.addLast(new int[] { n, color });
        colors[n] = color;

        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            int currNode = node[0];
            int currColor = node[1];


            for (int ngbr: graph[currNode]) {
                if (colors[ngbr] == currColor) {
                    return false;
                }


                if (colors[ngbr] == -1) {
                    colors[ngbr] = 1 - currColor;
                    q.addLast(new int[] { ngbr, colors[ngbr] });
                }
            }
        }

        return true;
    }

    //1) DFS Solution
    //checks if there's a cycle of even length in a
    //undirected graph - i.e. cycles of length 0, 2, 4...
    private boolean isBipartiteDfs(int[][] graph, int[] visited, int vertex, int color) {
        //If vertex visited and it is colored with the color
        //with which it should ideally be colored, then return true.
        //else false.
        if (visited[vertex] != -1) {
            return (color == visited[vertex]);
        }

        //if not visited, visit it (i.e. color it)
        visited[vertex] = color;

        boolean ans = true;
        //visit all neighbours of the vertex and color them with the
        //opposite color of the vertex (cause in bipartite graph, all
        //neighbors must lie in other bucket)
        for (int neighbour: graph[vertex]) {
            ans = ans && isBipartiteDfs(graph, visited, neighbour, (color == 1) ? 0 : 1);
        }

        return ans;
    }

    //2) BFS Soln
    private boolean isBipartiteBfs(int[][] graph, int[] visited, int startVertex) {
        Deque<VertexInfo> q = new ArrayDeque<>();
        q.addLast(new VertexInfo(startVertex, 0));

        while (!q.isEmpty()) {
            VertexInfo info = q.removeFirst();
            int vertex = info.vertex;
            int color = info.color;

            if (visited[vertex] != -1) {
                if (color != visited[vertex]) return false;
                else continue;
            }

            visited[vertex] = color;

            for (int neighbour: graph[vertex]) {
                q.addLast(new VertexInfo(neighbour, (color == 1) ? 0 : 1));
            }
        }

        return true;
    }
}

class VertexInfo {
    int vertex;
    int color;

    public VertexInfo(int vertex, int color) {
        this.vertex = vertex;
        this.color = color;
    }
}
