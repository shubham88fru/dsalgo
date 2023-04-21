package swd.graphs;

import java.util.*;

//@link - https://leetcode.com/problems/possible-bipartition/description/
public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        //visited array will keep track of which bucket/color (0 or 1)
        //a vertex lies in. size is `n+1` since vertices start from 1 instead of 0.
        int[] visited = new int[n+1];

        //Get graph from the dislikes array.
        List<List<Integer>> graph = getGraph(n, dislikes);

        Arrays.fill(visited, -1); //-1 means vertex not visited yet.

        //Note: start from index 1 (not 0) because of the question.
        for (int i=1; i <=n; i++) {
            //if vertex not visited, visit and color it.
            if (visited[i] == -1) {
                if (!isBipartiteDfs(graph, visited, i, 0)) return false;
                //if (!isBipartiteBfs(graph, visited, i)) return false;
            }
        }

        return true;
    }

    //1) DFS Solution
    //checks if there's a cycle of even length in a
    //undirected graph - i.e. cycles of length 0, 2, 4...
    private boolean isBipartiteDfs(List<List<Integer>> graph, int[] visited, int vertex, int color) {
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
        //neihbours must lie in other bucket)
        for (int neighbour: graph.get(vertex)) {
            ans = ans && isBipartiteDfs(graph, visited, neighbour, (color == 1) ? 0 : 1);
        }

        return ans;
    }

    //2) BFS Soln
    private boolean isBipartiteBfs(List<List<Integer>> graph, int[] visited, int startVertex) {
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

            for (int neighbour: graph.get(vertex)) {
                q.addLast(new VertexInfo(neighbour, (color == 1) ? 0 : 1));
            }
        }

        return true;
    }

    //Return graph.
    private List<List<Integer>> getGraph(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());

        for (int i=0; i<dislikes.length; i++) {
            int u = dislikes[i][0];
            int v = dislikes[i][1];

            //the question doesn't mention that for an entry
            //[u, v] in the dislikes array, both u and v dislike
            //each other. Hoewever, considering so and hence need
            //to make the graph undirectional.
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
