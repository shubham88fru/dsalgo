package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
public class MaximizeTheNumberOfTargetNodesAfterConnectingTreeI {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        return pass1(edges1, edges2, k);
    }

    /*
        My soln. Mik had the exact same soln.
     */
    private int[] pass1(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        List<List<Integer>> graph1 = graph(edges1, n);
        List<List<Integer>> graph2 = graph(edges2, m);

        //nodes with max connection within dist k-1 in tree2.
        int maxConn = 0;
        for (int i=0; i<m; i++) {
            int[] visited = new int[m];
            int[] nodesWithinK = {0};
            dfs(graph2, nodesWithinK, i, k-1, visited);
            maxConn = Math.max(maxConn, nodesWithinK[0]);
        }

        for (int i=0; i<n; i++) {
            int[] visited = new int[n];
            int[] nodesWithinK = {0};
            dfs(graph1, nodesWithinK, i, k, visited);
            ans[i] = nodesWithinK[0] + maxConn;
        }


        return ans;

    }

    private void dfs(List<List<Integer>> graph, int[] nodesWithinK, int node, int k, int[] visited) {

        if (visited[node] == -1) return;
        if (k < 0) return;

        nodesWithinK[0] += 1;
        visited[node] = -1;

        List<Integer> ngs = graph.get(node);
        for (int ng: ngs) {
            dfs(graph, nodesWithinK, ng, k-1, visited);
        }

    }

    private List<List<Integer>> graph(int[][] edges, int n) {
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
