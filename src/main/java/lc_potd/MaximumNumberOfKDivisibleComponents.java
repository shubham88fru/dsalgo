package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/maximum-number-of-k-divisible-components/description/?
//@check - https://www.youtube.com/watch?v=RnFCrjkkIUE
public class MaximumNumberOfKDivisibleComponents {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] vals, int k) {
        return mikssol(n, edges, vals, k);
    }

    /**
     I had a hunch but got confused and couldn't complete
     the soln on my own. Following is coded completely by
     me after taking hints from mik.
     Note, the most important piece of info in this problem
     that significantly reduces the complexity of the problem
     is that the entire sum is divisble by k. Meaning, when
     breaking a group of nodes having sum divisible by k, we
     don't really need to worry about the sum of remaining elements.
     That'll also be divisible by k for sure.
     */
    private int mikssol(int n, int[][] edges, int[] vals, int k) {
        List<List<Integer>> graph = getGraph(edges, n);
        int[] components = {0};
        dfs(graph, 0, components, vals, new HashSet<>(), k);
        return components[0];
    }

    private int dfs(List<List<Integer>> graph, int i, int[] components, int[] vals, Set<Integer> visited, int k) {
        if (visited.contains(i)) return 0;

        visited.add(i);
        List<Integer> ngs = graph.get(i);
        int sum = vals[i];
        for (int ng: ngs) {
            sum = (sum + dfs(graph, ng, components, vals, visited, k))%k;
        }

        if (sum%k == 0) {
            components[0] += 1;
            return 0;
        }

        return sum;

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
