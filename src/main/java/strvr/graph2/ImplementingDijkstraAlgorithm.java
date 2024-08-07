package strvr.graph2;

/*
    Dijkstra's algorithm (to find the shortest paths in a graph)
    --------------------------------------------------------
    --> Only for 'positively' weighted graph.
    --> Algorithm is simply a BFS over the graph but using a Priority queue instead
        of  usual queue.
        Dijkstra = BFS - Queue + Priority Queue.
    --> Dijkstra gives shortest path to each node from a fixes/given source node.
        While Floyd Warshall gives, shorted path to each node from each node.
        Hence, imagine the output of Dijkstra as being a list while the output
        of Floyd Warshall being a List of List.
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
        //Means - Vertex 0 is connected to vertex 1 with weight of 1 and to Vertex 2 with weight of 6.
        //and so on so forth for other vertices.
        return dijsktraFromGivenNode(V, adj, S);
    }

    //Note that this algorithm will give the sum of the shortest path (sum of weights/costs of shortest path)
    //It wont be directly clear from this algorithm as to which path exactly was the shortest.
    private int[] dijsktraFromGivenNode(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        //After completion of dijkstra, costs array will
        //consist of shortest path of each node from the node `S`.
        int[] costs = new int[V];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, -1);

        //Priority queue (instead of a usual queue) is used in implementing
        //dijkstra. Here, the priority queue will sort the added values in
        //natural order of the cost.
        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new VertexCost(S, 0)); //cost of start index to itself is zero.

        while (!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;

            //curr cost stores our current min cost of reaching the currnode from S.
            int currCost = vertexAndCost.cost;

            //Means already visited, hence just skip it.
            //this costs array kind of also acts like the
            //visited array of a normal graph traversal.
            if (costs[currVertex] != -1) continue;

            costs[currVertex] = currCost;
            ArrayList<ArrayList<Integer>> neighboursWithCost
                    = adj.get(currVertex);

            for (ArrayList<Integer> neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost.get(0);
                int currentEdgeCost = neighbourWithCost.get(1);
                //add to queue with current cost. The total cost (currcost + currEdgeCost)
                //will actually be the total cost of reaching that vertex from the source vertex.
                pq.add(new VertexCost(currentNeighbour, (currCost + currentEdgeCost)));
            }
        }

        return costs;
    }

    /**
     * Note: Below is a slightly different flavor of the same code which strvr showed.
     * It initializes the costs with max value and in the for loop there's a check to
     * see if cost of a reach a node till now plus the next edge cost is lesser than
     * whats' stored int the cost.
     *
     * Note that with this approach we don't need the visited array.
     *
     * TODO: think why both work and whats the diff.
     */
    private int[] dijsktraFromGivenNodeStrvr(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        //After completion of dijkstra, costs array will
        //consist of shortest path of each node from the node `S`.
        int[] costs = new int[V];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, Integer.MAX_VALUE);

        //Priority queue (instead of a usual queue) is used in implementing
        //dijsktra. Here, the priority queue will sort the added values in
        //natural order of the cost.
        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        costs[S] = 0;
        pq.add(new VertexCost(S, 0)); //cost of start index to itself is zero.

        while (!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            ArrayList<ArrayList<Integer>> neighboursWithCost
                    = adj.get(currVertex);

            for (ArrayList<Integer> neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost.get(0);
                int currentEdgeCost = neighbourWithCost.get(1);
                //add to queue with current cost. The total cost (currcost + currEdgeCost)
                //will actually be the total cost of reaching that vertex from the source vertex.
                if (currCost + currentEdgeCost < costs[currentNeighbour]) {
                    costs[currentNeighbour] = currCost + currentEdgeCost;
                    pq.add(new VertexCost(currentNeighbour, (currCost + currentEdgeCost)));
                }
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