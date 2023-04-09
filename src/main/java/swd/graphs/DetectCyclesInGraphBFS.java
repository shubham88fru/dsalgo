package swd.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/*
 `Kahn's algorithm` is used to detect cycles in graph using a BFS approach.
 The algorithm is as follows -
 1) Find the indegree of all the vertices present in the graph (in a arrays ds)
 2) Store the vertices with indegree 0 into a queue.
 3) Run BFS on the above queue. For each element in the queue, decrement the indegree of the neighbours
    of current element and add the neighbours to queue if their indegree becomes zero as part of the decrement
    operation.
 4) If at the end of bfs, we're not able to visit all vertices (i.e. some vertices remain un-visited) it means
    there is a cycle in the graph.
 */
public class DetectCyclesInGraphBFS {
    /*** Kahn's algorithm ***/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        //Step 1: Find indegree array
        int[] inDegrees = new int[V];
        for (int vertex=0; vertex<V; vertex++) {
            ArrayList<Integer> neighbours = adj.get(vertex);
            for (int neighbour: neighbours) {
                inDegrees[neighbour] += 1;
            }
        }

        //Step 2: Add all vertices with 0 indegree to queue.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int vertex=0; vertex<V; vertex++) {
            if (inDegrees[vertex] == 0) queue.addLast(vertex);
        }

        //Step 3: Run bfs. If after bfs, we're not able to
        //visit all vertices, then we have a cycle in the graph.
        boolean[] visited = new boolean[V];
        int totalVisitedVertices = bfsKahn(adj, queue, inDegrees, visited);
        return (totalVisitedVertices != V);
    }

    //BFS for kahn's algorithm
    private int bfsKahn(ArrayList<ArrayList<Integer>> adj, Deque<Integer> queue, int[] inDegrees, boolean[] visited) {
        int visitedVertices = 0;

        while (!queue.isEmpty()) {
            int curr = queue.removeFirst();
            if (visited[curr]) continue;

            visited[curr] = true;
            visitedVertices += 1;

            ArrayList<Integer> neighbours = adj.get(curr);
            for (int neighbour: neighbours) {

                //Decrement the indegree of each neighbour,
                //and if indegree becomes 0, add to queue.
                inDegrees[neighbour] -= 1;
                if (inDegrees[neighbour] == 0) {
                    queue.addLast(neighbour);
                }
            }
        }

        return visitedVertices;
    }
}
