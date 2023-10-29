package strvr.graph2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Dijsktra's shortest path algorithm fails when the graph has -ve weights.
 * Also, when the graph has -ve cycles (even though it doesn't have -ve weights),
 * dijsktra's algo will go into an infinite loop.
 *
 * A -ve cycle in a graph is a cycle whose path weight (path sum) is -ve.
 *
 * For such graphs with -ve weights or -ve cycles, bellmanford algorithm can be used
 * to find the shortest path to each node of the graph from a starting node.
 * Bellmanford algorithm helps detecting -ve cycles in graph.
 * It is applicable only in Directed graphs. However, if we have to apply
 * bellmanford on a undirected graph, all we need to do is sort of convert
 * the undirected graph to a directed graph where edge a<--->b with weight W will
 * become two edges a-->b and a<--b each with weight W.
 */

//@link - https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/0
//@strvr - https://takeuforward.org/data-structure/bellman-ford-algorithm-g-41/
public class BellmanFordAlgorithm {
    int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
        return bellmanford(V, edges, S);
    }

    private int[] bellmanford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        //Step 1: setup weights.
        int[] costs = new int[V]; //cost of reaching each node from S.
        int MAX_WEIGHT = (int)1e8; //note we're not putting INT_MAX. Could overflow during sum, othewise.
        Arrays.fill(costs, MAX_WEIGHT);
        costs[S] = 0; // S to S doesn't cost anything.

        //Step 2: relax edges V-1 times.
        for (int i=0; i<V-1; i++) {
            for (ArrayList<Integer> edge: edges) {
                int v1 = edge.get(0);
                int v2 = edge.get(1);
                int w = edge.get(2);

                //costs[v1] != MAX_WEIGHT --> to prevent overflow.
                if (costs[v1] != MAX_WEIGHT)
                    costs[v2] = Math.min(costs[v2], costs[v1]+w);
            }
        }

        //Step 3: relax Vth time to see if graph has negative cycle.
        for (ArrayList<Integer> edge: edges) {
            int v1 = edge.get(0);
            int v2 = edge.get(1);
            int w = edge.get(2);
            //costs[v1] != MAX_WEIGHT --> to prevent overflow.
            if (costs[v1] != MAX_WEIGHT && costs[v1]+w < costs[v2]) {
                return new int[]{-1}; //ATQ
            }
        }

        return costs;
    }
}
