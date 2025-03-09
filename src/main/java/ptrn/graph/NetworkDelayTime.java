package ptrn.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/network-delay-time/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5166651391344640
public class NetworkDelayTime {
    /** 1)
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

    //2) Revise.
    private int revise(int[][] times, int n, int k) {
        List<List<int[]>> graph = getGraph(times, n);

        int[] minDist = new int[n+1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[k] = 0;

        dijkstra(graph, n, k, minDist);

        /*
            Todo: Check if this min value can be found during dijkstra itself.
        */
        int max = Integer.MIN_VALUE;
        for (int i=1; i<minDist.length; i++) {
            if (minDist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, minDist[i]);
        }

        return max;
    }

    private void dijkstra(List<List<int[]>> graph, int n, int k, int[] minDist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);

        pq.add(new int[] {k, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.remove();
            List<int[]> ngs = graph.get(node[0]);


            for (int[] ng: ngs) {
                int nextNode = ng[0];
                int edgeWeight = ng[1];

                if (minDist[node[0]]+edgeWeight < minDist[nextNode]) {
                    minDist[nextNode] = minDist[node[0]] + edgeWeight;
                    pq.add(ng);
                }

            }
        }
    }

    private List<List<int[]>> getGraph(int[][] times, int n) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time: times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph.get(u).add(new int[] {v, w});
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