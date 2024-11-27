package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/
public class ShortestDistanceAfterRoadAdditionQueriesI {

    /*
        Following is my dijkstra soln.
        However, dijkstra is an overkill because all weights are same.
        Simple BFS is enough for this question.
     */
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<List<Integer>> graph = getGraph(n);
        int[] ans = new int[queries.length];
        for (int i=0; i<queries.length; i++) {
            graph.get(queries[i][0]).add(queries[i][1]);
            int[] minDist = dijks(graph, n, 0);
            ans[i] = minDist[n-1];
        }

        return ans;
    }

    private int[] dijks(List<List<Integer>> adj, int n, int S) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        costs[S] = 0;
        pq.add(new VertexCost(S, 0));

        while(!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            List<Integer> neighboursWithCost
                    = adj.get(currVertex);

            for (int neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost;
                int currentEdgeCost = 1;
                if (currCost + currentEdgeCost < costs[currentNeighbour]) {
                    costs[currentNeighbour] = currCost + currentEdgeCost;
                    pq.add(new VertexCost(currentNeighbour, (currCost + currentEdgeCost)));
                }
            }
        }

        return costs;
    }

    private List<List<Integer>> getGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());
        for (int i=0; i<n-1; i++) {
            graph.get(i).add(i+1);
        }

        return graph;
    }
}

class VertexCost {
    int vertex;
    int cost;

    public VertexCost(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}
