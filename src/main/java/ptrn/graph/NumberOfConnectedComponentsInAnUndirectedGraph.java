package ptrn.graph;

import java.util.ArrayList;
import java.util.List;

//@link - https://neetcode.io/problems/count-connected-components
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5296217631490048
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    /*
    * This was a challenge problem in edctv and so no model soln
    * was provided. Below is my soln but I don't think this is the
    * optimal soln. This problem can be solved using Union find pattern
    * and that might have a better TC.
    * Check for optimal soln on YT.
    * */
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = getGraph(edges, n);

        int connectedCom = 0;
        int[] visited = new int[n];
        for (int i=0; i<n; i++) {
            if (visited[i] != -1) {
                connectedCom += 1;
                visit(graph, visited, i, n);
            }
        }

        return connectedCom;
    }

    private void visit(List<List<Integer>> graph, int[] visited, int curr, int n) {
        if (visited[curr] == -1) return;

        visited[curr] = -1;
        List<Integer> children = graph.get(curr);
        for (int child: children) {
            visit(graph, visited, child, n);
        }
    }

    private List<List<Integer>> getGraph(int[][] edges, int n) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
