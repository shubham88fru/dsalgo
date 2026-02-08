package lc_potd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/?
public class MinimumEdgeReversalsSoEveryNodeIsReachable {
    public int[] minEdgeReversals(int n, int[][] edges) {
        return brute(n, edges);
    }

    /**
     * My brute force soln.
     * Idea is to create the graph and also
     * add reverse edges with an indicator.
     * Then do a dfs from each node and count
     * how many reverse edges we've encountered.
     *
     * TC is poor O(V*(V+E)) therefore gives TLE.
     */
    private int[] brute(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(new int[]{v, 0});
            graph.get(v).add(new int[]{u, -1});
        }

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            int[] revs = {0};
            dfs(graph, i, revs, new HashSet<>());
            ans[i] = revs[0];
        }

        return ans;
    }

    private void dfs(List<List<int[]>> graph, int i, int[] revs, Set<Integer> visited) {

        visited.add(i);

        List<int[]> ngs = graph.get(i);
        for (int[] ng: ngs) {
            if (!visited.contains(ng[0])) {
                if (ng[1] == -1) revs[0] += 1;
                dfs(graph, ng[0], revs, visited);
            }
        }
    }
}
