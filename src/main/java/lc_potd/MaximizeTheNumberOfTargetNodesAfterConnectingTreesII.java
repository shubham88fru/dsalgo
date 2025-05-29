package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/
//@check - https://www.youtube.com/watch?v=W1xTqEcoSkI&ab_channel=codestorywithMIK
public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesII {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        // return pass1(edges1, edges2);
        return pass2(edges1, edges2);
    }

    /*
        1. Coded by me but based on Mik's explanation.
        Idea is to do a pass on tree one from any (say node 0) node
        and determining the color (odd or even) of each node wrt the starting
        node. Interestingly, if say node b is at even distance from starting node
        a, then for node b node a is also at even distance. Using this intuition,
        we can say that once we know the nodes at odd and even distance from one
        particular node, we actually know odd and even nodes from each node.
        This works so because the given graph is undirected.
    */
    private int[] pass2(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        List<List<Integer>> graph1 = graph(edges1, n);
        List<List<Integer>> graph2 = graph(edges2, m);

        int[] visited = new int[m];
        int[] evenCount = {0};
        int[] oddCount = {0};
        int[] color = new int[m];
        int maxConn = 0;
        dfs2(graph2, evenCount, oddCount, color, 0, 0, visited);
        maxConn = Math.max(maxConn, Math.max(oddCount[0], evenCount[0])); //imp

        int[] ans = new int[n];
        visited = new int[n];
        evenCount[0] = 0;
        oddCount[0] = 0;
        color = new int[n];
        dfs2(graph1, evenCount, oddCount, color, 0, 0, visited);

        for (int i=0; i<n; i++) {
            if (color[i] == 0) ans[i] = evenCount[0] + maxConn;
            else ans[i] = oddCount[0] + maxConn;
        }

        return ans;

    }

    //2. TLE
    private int[] pass1(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        List<List<Integer>> graph1 = graph(edges1, n);
        List<List<Integer>> graph2 = graph(edges2, m);

        int maxConn = 0;
        for (int i=0; i<m; i++) {
            int[] visited = new int[m];
            int[] nodesWithinK = {0};
            dfs(graph2, nodesWithinK, i, 1, visited);
            maxConn = Math.max(maxConn, nodesWithinK[0]);

        }

        for (int i=0; i<n; i++) {
            int[] visited = new int[n];
            int[] nodesWithinK = {0};
            dfs(graph1, nodesWithinK, i, 0, visited);
            ans[i] = nodesWithinK[0] + maxConn;
        }


        return ans;
    }

    private void dfs(List<List<Integer>> graph, int[] nodesWithinK, int node, int k, int[] visited) {

        if (visited[node] == -1) return;

        if (k%2 == 0) nodesWithinK[0] += 1;
        visited[node] = -1;

        List<Integer> ngs = graph.get(node);
        for (int ng: ngs) {
            dfs(graph, nodesWithinK, ng, k+1, visited);
        }

    }

    private void dfs2(List<List<Integer>> graph, int[] evenCount, int[] oddCount, int[] color, int node, int k, int[] visited) {

        if (visited[node] == -1) return;

        if (k%2 == 0) {
            evenCount[0] += 1;
            color[node] = 0;
        } else {
            oddCount[0] += 1;
            color[node] = 1;
        }
        visited[node] = -1;

        List<Integer> ngs = graph.get(node);
        for (int ng: ngs) {
            dfs2(graph, evenCount, oddCount, color, ng, k+1, visited);
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
