package swd.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
public class DetectCyclesInUndirectedGraphBFS {

    /*** SWD Soln - Video was missing so don't understand the code completely. ***/
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] parents = new int[V];
        Arrays.fill(parents, -1);

        for (int currVertex = 0; currVertex < V; currVertex += 1) {
            if (!visited[currVertex]) {
                if (hasCycle(adj, currVertex, parents, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int currVertex, int[] parents, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(currVertex);
        visited[currVertex] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.remove();

            for (int neighbour: adj.get(currentNode)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    parents[neighbour] = currentNode;
                    queue.add(neighbour);
                } else if (parents[currentNode] != neighbour) return true;
            }
        }

        return false;
    }
}
