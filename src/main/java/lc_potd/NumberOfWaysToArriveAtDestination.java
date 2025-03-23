package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
//@check - https://www.youtube.com/watch?v=F_jnqx14idY&t=1278s&ab_channel=codestorywithMIK
public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        return mikssol(n, roads);
    }

    /*
        Based on my understanding and mik's explanation.
        Mik didn't explain this problem very well.
        Especially the path counting part.

        Note that this problem is interesting because
        it uses dijkstra not just to find the shortest
        distance but to also find the number of paths
        with that shortest distance.
    */
    private int mikssol(int n, int[][] roads) {
        List<List<int[]>> graph = getGraph(n, roads);

        long[] costs = new long[n];
        Arrays.fill(costs, Long.MAX_VALUE);
        costs[0] = 0l;

        int[] counts = new int[n];
        Arrays.fill(counts, 0);
        counts[0] = 1;

        /*
            Below are the two differnt flavors of
            dijkstra that I keep swithich and getting
            confused between.

            For some reason, the dijsktra2 method (which to me is the
            intuitive one) isn't returing the correct path count.
            It returns correct cost though.
        */
        dijkstra1(graph, costs, counts, n);
        // dijkstra2(graph, costs, counts, n);

        return counts[n-1];
    }

    private void dijkstra1(List<List<int[]>> graph, long[] costs, int[] counts, int n) {
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        q.add(new long[] {0l, 0l});

        while (!q.isEmpty()) {
            long[] curr = q.remove();
            long currCost = curr[1];
            int currNode = (int)curr[0];

            List<int[]> ngs = graph.get(currNode);
            for (int[] ng: ngs) {
                int neighborNode = ng[0], roadTime = ng[1];
                if (currCost + roadTime < costs[neighborNode]) {
                    costs[neighborNode] = currCost + roadTime;
                    counts[neighborNode] = counts[currNode]; //tricky - reset the count if a minimum weight path is found.
                    q.add(new long[]{ neighborNode, costs[neighborNode]});
                } else if (currCost + roadTime == costs[neighborNode]) { //tricky - add to count if a new path with min weight found.
                    counts[neighborNode] = (counts[neighborNode] + counts[currNode])%1000000007;
                }
            }
        }
    }

    private void dijkstra2(List<List<int[]>> graph, long[] costs, int[] counts, int n) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        q.add(new int[] {0, 0});

        while (!q.isEmpty()) {
            int[] curr = q.remove();
            int currCost = curr[1];
            int currNode = curr[0];

            List<int[]> ngs = graph.get(currNode);
            for (int[] ng: ngs) {
                int neighborNode = ng[0], roadTime = ng[1];
                if (costs[curr[0]] + ng[1] < costs[ng[0]]) {
                    costs[ng[0]] = costs[curr[0]] + ng[1];
                    counts[ng[0]] = counts[curr[0]];
                    q.add(ng);
                } else if (costs[curr[0]] + ng[1] == costs[ng[0]]) {
                    counts[ng[0]] = (counts[ng[0]] + counts[curr[0]])%1000000007;
                }
            }
        }
    }

    private List<List<int[]>> getGraph(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: roads) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        return graph;
    }
}
