package lc_potd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/remove-methods-from-project/description/?
//@check - https://www.youtube.com/watch?v=2D13j9J62g4
public class RemoveMethodsFromProject {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        return mikssol(n, k, invocations);
    }

    /*
        Coded by me completely based on mik's
        explanation. There's slight improvement
        possible in my code.
    */
    private List<Integer> mikssol(int n, int k, int[][] invocations) {
        int[] indeg = new int[n];
        for (int[] inv: invocations) {
            int v = inv[1]; //u -> v
            indeg[v] += 1;
        }

        List<List<Integer>> graph = getGraph(n, invocations);
        boolean[] suspicious = new boolean[n];
        mark(graph, suspicious, indeg, k);

        List<Integer> ans = new ArrayList<>();
        List<Integer> all = new ArrayList<>();
        for (int i=0; i<n; i++) all.add(i);
        for (int i=0; i<n; i++) {
            if (suspicious[i] && indeg[i] > 0) return all;
            else if (!suspicious[i]) ans.add(i);
        }

        return ans;
    }

    private void mark(List<List<Integer>> graph, boolean[] suspicious, int[] indeg, int root) {
        Deque<Integer> q = new ArrayDeque<>();
        indeg[root] += 1;
        q.addLast(root);

        while (!q.isEmpty()) {
            int node = q.removeFirst();
            indeg[node] -= 1;
            if (suspicious[node]) continue;
            suspicious[node] = true;


            List<Integer> ngs = graph.get(node);
            for (int ng: ngs) {

                q.addLast(ng);
            }
        }
    }

    private List<List<Integer>> getGraph(int n, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());

        for (int[] inv: invocations) {
            int u = inv[0], v = inv[1]; //u -> v
            graph.get(u).add(v);
        }

        return graph;
    }
}
