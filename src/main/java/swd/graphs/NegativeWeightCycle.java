package swd.graphs;

/*
    Bellman Ford algorithm (find shortest path and detect -ve cycles in graph)
    --------------------------------------------------------------------------
    --> Can be used to find shortest path to each node from a source node as well as
        shortest path of each node from each node.
    --> Works with graphs with -ve weights also.
    --> Doesn't work with undirected graphs have -ve weights.
    --> STEPS:
        1) Initialize a costs array, where each index represents the cost of visiting the vertex (index)
           from the chosen source vertex.
        2) Relax all the edges in the graph V-1 time (V is the no. of vertex)
            //for every edge (u, v, w)
            //cost of reaching vertex v from source is min of cost of reaching v from source
            //and cost of reaching u from source plus weight of edge.
           cost[v] = Math.min(cost[v], cost[u]+w);
        3) Relax all weights one more time. If after this relaxation costs reduce, means we have a -ve cycle.
        4) If there is a -ve weight cycle in a graph, then its not possible to find min cost.
 */

//@link - https://practice.geeksforgeeks.org/problems/negative-weight-cycle3504/1
public class NegativeWeightCycle {
    public int isNegativeWeightCycle(int n, int[][] edges) {
        //costs array that will store the min cost
        //of each vertex from the source node.
        int[] costs = new int[n];
        final int MAX_VALUE = 9999999;

        //assuming 0 as source. cost of 0 to 0 will be 0.
        for (int i=1; i<n; i++) {
            costs[i] = MAX_VALUE;
        }

        //Bellman ford algorithm.
        //STEP 1: Relax all the edges `V-1` times.
        for (int i=0; i<n-1; i++) {
            for (int[] currentEdge: edges) {
                int src = currentEdge[0];
                int dest = currentEdge[1];
                int weight = currentEdge[2];

                if (costs[src]+weight < costs[dest])
                    costs[dest] = costs[src]+weight;
            }
        }

        //at this point costs will be having smallest paths for each vertex from
        //vertex 0.

        //STEP 2: Relax the edge one more time, to see if
        //we have a negative weight edge.
        for (int[] currentEdge: edges) {
            int src = currentEdge[0];
            int dest = currentEdge[1];
            int weight = currentEdge[2];

            if (costs[dest] > costs[src]+weight) {
                return 1;
            }

        }

        return 0;
    }

}
