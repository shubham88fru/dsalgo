package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/count-the-number-of-complete-components/description/
//@check - https://www.youtube.com/watch?v=7b7GbuFMWRw&t=2431s&ab_channel=codestorywithMIK
public class CountTheNumberOfCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        return approach1(n, edges);
    }

    /*
        The idea is that for a completely connected
        component of a graph, the number of edges
        will be equal to nC2 = n(n-1)/2 where n is
        the no. of nodes in the component.
        This is because, by definition, a full connected
        component is one where each node is connected to every
        other node of the component.
    */

    /*
    * Mik also showed DSU by size soln for this problem,
    * but I didn't do it coz it wasn't very intuitive to me.
    *  */
    private int approach1(int n, int[][] edges) {
        List<List<Integer>> graph = getGraph(n, edges);
        int[] visited = new int[n];

        int fcc = 0;
        for (int i=0; i<n; i++) {

            if (visited[i] == 0) {
                int[] nc = {0};
                int[] ec = {0};
                dfs(graph, i, nc, ec, visited);
                int ideal = (nc[0]*(nc[0]-1))/2;
                if (ec[0]/2 == ideal) fcc += 1;
            }

        }
        return fcc;
    }

    private void dfs(List<List<Integer>> graph, int curr, int[] nodes, int[] edges, int[] visited) {
        if (curr >= visited.length) return;
        if (visited[curr] != 0) return;

        visited[curr] = -1;
        nodes[0] += 1;

        List<Integer> ngs = graph.get(curr);
        for (int ng: ngs) {
            edges[0] += 1; //edges will be double counted, but we'll halve it during comparision.
            dfs(graph, ng, nodes, edges, visited);
        }
    }

    private List<List<Integer>> getGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
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
