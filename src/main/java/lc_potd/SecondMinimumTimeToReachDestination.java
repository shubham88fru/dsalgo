package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Got most of it, wrote the most code also by self after watching the explanation.
 * However, might not be able to do again all by self. @check if needed.
 * Also, there is a more optimized approach using plain BFS.
 * If interested @check
 */
//@link - https://leetcode.com/problems/second-minimum-time-to-reach-destination/description/
//@check - https://www.youtube.com/watch?v=_rnQKrA9xzA&t=2881s&ab_channel=codestorywithMIK
public class SecondMinimumTimeToReachDestination {

    /**
     * This Dijkstra approach is based on strvr's method for dijkstra.
     * @see strvr.graph2.ImplementingDijkstraAlgorithm#dijsktraFromGivenNodeStrvr
     */
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> graph = getGraph(n, edges);

        int[] fmin = new int[n+1]; //first minimum.
        Arrays.fill(fmin, Integer.MAX_VALUE);

        int[] smin = new int[n+1]; //second minimum.
        Arrays.fill(smin, Integer.MAX_VALUE);

        PriorityQueue<NodeCostPair> minHeap
                = new PriorityQueue<>((np1, np2) -> np1.cost-np2.cost);

        minHeap.add(new NodeCostPair(1, 0));

        while (!minHeap.isEmpty()) {
            NodeCostPair pair = minHeap.remove();
            int currNode = pair.node;
            int currCost = pair.cost;

            if (currNode == n && smin[n] != Integer.MAX_VALUE) return smin[n];

            int costWithWait = ((currCost/change)%2==0) ? currCost :  ((currCost/change+1)*change);
            List<Integer> neighbours = graph.get(currNode);
            for (int neighbour: neighbours) {
                if (fmin[neighbour] > costWithWait + time) {
                    smin[neighbour] = fmin[neighbour];
                    fmin[neighbour] = costWithWait + time;
                    minHeap.add(new NodeCostPair(neighbour, costWithWait + time));
                } else if ((smin[neighbour] > costWithWait + time) && (fmin[neighbour] != costWithWait + time)) {
                    smin[neighbour] = costWithWait + time;
                    minHeap.add(new NodeCostPair(neighbour, costWithWait + time));
                }
            }
        }

        return -1;
    }

    private List<List<Integer>> getGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int src = edge[0];
            int des = edge[1];

            graph.get(src).add(des);
            graph.get(des).add(src);
        }

        return graph;
    }
}

class NodeCostPair {
    int node;
    int cost;

    public NodeCostPair(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}
