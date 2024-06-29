package lc_potd;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//@link - https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/description/
public class AllAncestorsOfANodeInADAG {
    /**
     No clue what happened in this question.
     Ideal soln involves toposort.
     Rewatch a soln other than aryan's when this question comes up again.
     */
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indeg = new int[n];

        // populateGraphAndIndeg(n, edges, graph, indeg);
        // return populateAncestors2(n, edges, graph, indeg);
        return getAncestorsdfs(n, edges);
    }

    public List<List<Integer>> getAncestorsdfs(int n, int[][] edges) {
        List<List<Integer>> ans = new ArrayList();
        List<List<Integer>> directChild = new ArrayList();
        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList());
            directChild.add(new ArrayList());
        }
        for (int[] e: edges)
            directChild.get(e[0]).add(e[1]);
        for (int i = 0; i < n; i++)
            dfs2(i, i, ans, directChild);
        return ans;
    }
    private void dfs2(int x, int curr, List<List<Integer>> ans, List<List<Integer>> directChild) {
        for (int ch: directChild.get(curr))
            if(ans.get(ch).size() == 0 || ans.get(ch).get(ans.get(ch).size() - 1) != x) {
                ans.get(ch).add(x);
                dfs2(x, ch, ans, directChild);
            }
    }

    //Soln 2 - TLE.
    //For each node of the graph, run a dfs.
    //Whichever nodes are encountered during the DFS, will have the current node (for which DFS is run)
    //as its ancestor. Since a node can have multiple edges reaching to same node, to avoid duplicates,
    //when adding current node as an ancestor, we check if its not already present.
    private List<List<Integer>> populateAncestors2(int n, int[][] edges, List<List<Integer>> graph) {
        List<Set<Integer>> ancestors = new ArrayList<>();
        for (int i=0; i<n; i++) {
            ancestors.add(new TreeSet<>());
        }

        for (int i=0; i<n; i++) {
            dfs(i, i, graph, ancestors);
        }

        // for (List<Integer> ancestor: ancestors) {
        //     Collections.sort(ancestor);
        // }
        List<List<Integer>> ans = new ArrayList<>();
        for (Set<Integer> st: ancestors) {
            ans.add(new ArrayList<>(st));
        }

        return ans;
    }

    //Note that this dfs is guranteed to stop coz the graph is given to be a Acyclic.
    //So, we are not going to end up in a loop.
    private void dfs(int node, int curr, List<List<Integer>> graph, List<Set<Integer>> ancestors) {
        if (graph.get(curr).size() == 0) return;
        List<Integer> neighbours = graph.get(curr);
        for (int neighbour: neighbours) {
            // if (!ancestors.get(neighbour).contains(node)) {
            ancestors.get(neighbour).add(node);
            // }
            dfs(node, neighbour, graph, ancestors);
        }
    }

    private void getGraph(int n, int[][] edges, List<List<Integer>> graph, int[] indeg) {

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            indeg[v] += 1;
            graph.get(u).add(v);
        }
    }
}
