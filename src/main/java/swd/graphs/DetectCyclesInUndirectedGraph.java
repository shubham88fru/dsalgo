package swd.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
public class DetectCyclesInUndirectedGraph {
    /***
     * recording missing. SWD Soln, but looks like it doesn't work.
     *  Need to check from other sources
     * ***/
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int currVertex = 0; currVertex < V; currVertex += 1) {
            if (!visited[currVertex]) {
                if (hasCycle(adj, currVertex, -1, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int currVertex, int currParent, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(currVertex);
        visited[currVertex] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.remove();
            if (visited[currentNode]) {
                continue;
            }

            for (int neighbour: adj.get(currentNode)) {
                if (visited[neighbour] == false) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                } else if (neighbour != currentNode) return true;
            }
        }

        return false;
    }
}
