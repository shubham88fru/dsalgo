package ptrn.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://www.geeksforgeeks.org/problems/is-it-a-tree/1
         //https://leetcode.com/problems/graph-valid-tree/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5787948425150464
public class GraphValidTree {
    public boolean isTree(int n, int m, ArrayList<ArrayList<Integer>> edges) {

        //For a tree with n nodes, the
        //no. of edges must be n-1.
        //More than n-1 edges show that the graph has cycles.
        //Less the n-1 edges show that the graph is disconnected (trees can never be disconnected)
        if (m != n-1) return false;
        /**
         * If the graph has required no. of nodes,
         * we'll still need to check if we are able to visit all
         * the nodes of the graph. Note that, even having n-1 edges doesn't
         * guarantee that the graph is a tree e.g. consider this disconnected
         * graph with 4 nodes and 3 edges.
         * 1 -- 0
         *  \  /  3
         *   2
         *  And so, having n-1 edges is a necessary condition but not the
         *  sufficient. We'll need to traverse the graph also and check if
         *  we are able to visit all the nodes.
         */

        //for the graph.
        List<List<Integer>> graph = getGraph(n, edges);
        Set<Integer> visited = new HashSet<>();
        dfs(graph, 0, visited);
        return (visited.size() == n); // if not able to visit all the nodes, means graph is diconnected.
    }

    private void dfs(List<List<Integer>> graph, int curr, Set<Integer> visited){
        visited.add(curr);
        List<Integer> neighbors = graph.get(curr);
        for (int neighbor: neighbors) {
            if (!visited.contains(neighbor)) dfs(graph, neighbor, visited);
        }
    }

    private List<List<Integer>> getGraph(int n, ArrayList<ArrayList<Integer>> edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> edge: edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
