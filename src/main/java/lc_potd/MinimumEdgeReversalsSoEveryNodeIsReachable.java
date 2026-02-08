package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/?
//@check - https://www.youtube.com/watch?v=L8GJkBbAj8E
public class MinimumEdgeReversalsSoEveryNodeIsReachable {
    public int[] minEdgeReversals(int n, int[][] edges) {
        // return brute(n, edges);
        return optimal(n, edges);
    }

    /**
     Coded by me based on aryan's explanation.
     Idea similar to my brute for sol, but instead
     of a dfs from each node, we take any node (say node 0)
     as the root and do a dfs from this root node and visit
     every other node. While visiting the nodes, store
     the value of reversals needed from node to current node
     and the depth (edges between root to current node).

     Finally, after the traversal, we can find the answer for
     every other node very easily by subtracting the reversals
     needed from root to current node from the total reversals
     from the root node and adding reversals needed from current
     node to root node.

     A bit confusing so watch aryan's explanation again to revise.
     */
    private int[] optimal(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(new int[]{v, 0});
            graph.get(v).add(new int[]{u, -1});
        }

        Map<Integer, int[]> revsAndEdgesFromRoot = new HashMap<>();
        int[] totalRevsFromRoot = {0};
        dfs2(graph, revsAndEdgesFromRoot, 0, 0, 0, totalRevsFromRoot, new HashSet<>());

        int[] ans = new int[n];
        ans[0] = totalRevsFromRoot[0];
        for (int i=1; i<n; i++) {
            int revsNodeToRoot = revsAndEdgesFromRoot.get(i)[1] - revsAndEdgesFromRoot.get(i)[0];
            ans[i] = (totalRevsFromRoot[0] - revsAndEdgesFromRoot.get(i)[0]) + revsNodeToRoot;
        }
        return ans;
    }

    private void dfs2(List<List<int[]>> graph, Map<Integer, int[]> revsAndEdgesFromRoot, int curr, int edges, int revs, int[] totalRevsFromRoot, Set<Integer> visited) {
        visited.add(curr);

        revsAndEdgesFromRoot.put(curr, new int[]{revs, edges});
        List<int[]> ngs = graph.get(curr);
        for (int[] ng: ngs) {
            if (!visited.contains(ng[0])) {
                if (ng[1] != 0) totalRevsFromRoot[0] += 1;
                dfs2(graph, revsAndEdgesFromRoot, ng[0], edges+1, (ng[1] != 0)? revs+1: revs, totalRevsFromRoot, visited);
            }
        }
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
