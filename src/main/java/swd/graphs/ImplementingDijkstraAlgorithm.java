package swd.graphs;

/*
    Dijkstra's algorithm (to find shortest paths in a graph)
    --------------------------------------------------------
    --> Only for 'positively' weighted graph.
    --> Algorithm is simply a BFS over the graph but using a Priority queue instead
        of  usual queue.
        Dijkstra = BFS - Queue + Priority Queue.
    --> For graphs that can have -ve weights, we can use:
            a) Floyd Warshall algorithm.
            b) Bellman ford algorithm.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//@link - https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
public class ImplementingDijkstraAlgorithm {
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        //Representation of a weighted graph
        //eg adj = [[[1, 1], [2, 6]], [[2, 3], [0, 1]], [[1, 3], [0, 6]]]
        //Means - Vertex 0 is connected to vertex 1 with weight of 1 and to Veterx 2 with weight of 6.
        //and so on so forth for other vertices.
        return dijsktraFromGivenNode(V, adj, S);
    }

    private int[] dijsktraFromGivenNode(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        //After completion of dijkstra, costs array will
        //consist of shortest path of each node from the node `S`.
        int[] costs = new int[V];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, -1);

        //Priority queue (instead of a usual queue) is used in implementing
        //dijsktra. Here, the priority queue will sort the added values in
        //natural order of the cost.
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
                //add to queue with current cost.
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