package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
//@check - https://www.youtube.com/watch?v=V6LnZaX7zpk&ab_channel=codestorywithMIK
public class LargestColorValueInADirectedGraph {
    public int largestPathValue(String colors, int[][] edges) {
        return mikssol(colors, edges);
    }

    /*
      I was on the right track but was missing details.
      Didn't waste time solving myself and thought of verifying
      my intuition with mik first.

      This is an interesting question in that it solves the
      question while doing the toposort and not first calculating
      the toposort and then running a separate traversal.

      Toposort is important because we know for sure that the longest
      path will start from the node that comes first int the sequence.
    */
    private int mikssol(String colors, int[][] edges) {
        int n = colors.length();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i=0; i<n; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] indeg = new int[n];
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            indeg[v] += 1;
        }

        //max frequency of each color at each node
        //among all paths passing through that node.
        int[][] freqs = new int[n][26];

        return toposort(graph, colors, indeg, freqs);
    }

    private int toposort(Map<Integer, List<Integer>> graph, String colors, int[] indeg, int[][] freq) {
        int n = colors.length();
        int visitedCount = 0;

        Deque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            if (indeg[i] == 0) {
                q.addLast(i);
                freq[i][colors.charAt(i)-'a'] = 1; //coz, first node of a valid path.
                visitedCount += 1;
            }
        }

        int maxi = 1;
        while (!q.isEmpty()) {
            int node = q.removeFirst();
            int nodeColor = colors.charAt(node) - 'a';

            maxi = Math.max(maxi, freq[node][nodeColor]);

            List<Integer> ngs = graph.get(node);
            for (int ng: ngs) {
                int ngbrColor = colors.charAt(ng) - 'a';
                for (int i=0; i<26; i++) {
                    if (i == ngbrColor) {
                        freq[ng][i] = Math.max(freq[ng][i],freq[node][i]+1);
                    } else {
                        freq[ng][i] = Math.max(freq[ng][i],freq[node][i]);
                    }
                }

                indeg[ng] -= 1;
                if (indeg[ng] == 0) {
                    visitedCount += 1;
                    q.addLast(ng);
                }
            }
        }

        if (visitedCount < n) return -1;
        return maxi;
    }
}
