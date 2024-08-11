package lc_cntst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/count-the-number-of-good-nodes/
public class CountNumberOfGoodNodes {
    public int countGoodNodes(int[][] edges) {
        List<List<Integer>> graph = getGraph(edges);

        int[] visited = new int[edges.length+1];
        int[] count = {0};
        dfs(graph, 0, visited, count);

        return count[0];
    }

    private int dfs(List<List<Integer>> graph, int curr, int[] visited, int[] count) {

        visited[curr] = 1;
        List<Integer> children = graph.get(curr);

        int sz = 0;
        Set<Integer> st = new HashSet<>();
        for (int child: children) {
            if (visited[child] != 1) {
                int childSz = dfs(graph, child, visited, count);
                sz += childSz;
                st.add(childSz);
            }

        }
        if (st.size() <= 1) count[0] += 1;
        return 1+sz;
    }

    private List<List<Integer>> getGraph(int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        int n = edges.length + 1;
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
