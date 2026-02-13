package ptrn.graph;

import java.util.*;

//@link - https://leetcode.com/problems/find-if-path-exists-in-graph/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5758360890376192
public class FindIfPathExistsInGraph {
    /*
        This problem looks like a simple graph traversal problem,
        but both BFS and DFS give TLE. People mentioned this problem works
        with Union find pattern. So, try this problem once again after
        doing the union find pattern from edctv.
    */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        return recursion(n, edges, source, destination);
    }

    /*
        UPDATE: This DFS approach of mine works.
    */
    private boolean recursion(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        return dfs(graph, source, destination, visited);
    }

    private boolean dfs(List<List<Integer>> graph, int s, int d, Set<Integer> v) {
        if (v.contains(s)) return false;

        if (s == d) return true;
        v.add(s);

        for (int ng: graph.get(s)) {
            if(dfs(graph, ng, d, v)) return true;
        }

        return false;
    }
}
