package ptrn.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/network-delay-time/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5166651391344640
public class NetworkDelayTime {
    /**
     * Note that Dijkstra's algo gives us the min cost/distance
     * to each node of a graph from a given source node.
     * And so, the most straightforward thing to do in this question
     * will be to calculate the distances of each node of graph from
     * the source node and then find the max distance amongst that,
     * coz the minimum time will be the time taken to reach
     * the farthest node.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        //Get graph from times array.
        ArrayList<ArrayList<ArrayList<Integer>>> weightedGraph
                = getWeightedGraph(times, n);

        //Apply dijkstra.
        int[] costs = dijsktraFromGivenNode(n, weightedGraph, k);

        //If any vertex not visited from vertex k, return -1
        //else minimum time will be the max time needed to visit
        //any node.
        //NOTE: This is an extra loop that we have to do
        //to find the max cost. Check edctv soln,
        //this extra loop can be removed with a slight
        //modification during the BFS.
        int max = 0;
        for (int i=1; i<costs.length; i++) {
            if (costs[i] < 0) return -1;
            else {
                if (costs[i] >= max) max = costs[i];
            }
        }
        return max;
    }

    private ArrayList<ArrayList<ArrayList<Integer>>> getWeightedGraph(int[][] times, int n) {
        ArrayList<ArrayList<ArrayList<Integer>>> adj
                = new ArrayList<ArrayList<ArrayList<Integer>>>();

        for (int i=0; i <= n; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int[] edge: times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            ArrayList<Integer> weightedNeighbour = new ArrayList<>();
            weightedNeighbour.add(v);
            weightedNeighbour.add(w);
            adj.get(u).add(weightedNeighbour);
        }

        return adj;
    }

    //Dijkstra Algorithm.
    private int[] dijsktraFromGivenNode(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] costs = new int[V+1];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, -1);

        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new VertexCost(S, 0)); //cost of start index to itself is zero.

        while (!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            if (costs[currVertex] != -1) continue;

            costs[currVertex] = currCost;
            ArrayList<ArrayList<Integer>> neighboursWithCost
                    = adj.get(currVertex);

            for (ArrayList<Integer> neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost.get(0);
                int currentEdgeCost = neighbourWithCost.get(1);

                pq.add(new VertexCost(currentNeighbour, (currCost + currentEdgeCost)));
            }
        }

        return costs;
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