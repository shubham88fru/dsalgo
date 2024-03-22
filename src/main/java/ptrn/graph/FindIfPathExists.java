package ptrn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/find-if-path-exists-in-graph/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5758360890376192
public class FindIfPathExists {
    /*
        This problem looks like a simple graph traversal problem,
        but both BFS and DFS give TLE. People mentioned this problem works
        with Union find pattern. So, try this problem once again after
        doing the union find pattern from edctv.
    */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = getGraph(n, edges);
        return bfsForValidPath(graph, n, source, destination);
    }

    private boolean bfsForValidPath(List<List<Integer>> graph, int n, int src, int dest) {
        Deque<Integer> q = new ArrayDeque<>();
        int[] visited = new int[n];
        q.addLast(src);
        visited[src] = 1;
        while (!q.isEmpty()) {
            int node = q.removeFirst();
            if (node == dest) return true;
            visited[node] = 1;
            List<Integer> neighbours = graph.get(node);
            for (int neighbour: neighbours) {
                if (visited[neighbour] != 1) q.addLast(neighbour);
            }
        }

        return false;

    }

    private List<List<Integer>> getGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
