package ptrn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/*
 `Kahn's algorithm` is used to detect cycles in a directed graph using a BFS approach.
 The algorithm is as follows -
 1) Find the indegree of all the vertices present in the graph (in an arrays ds)
 2) Store the vertices with indegree 0 into a queue.
 3) Run BFS on the above queue. For each element in the queue, decrement the indegree of the neighbours
    of current element and add the neighbours to queue if their indegree becomes zero as part of the decrement
    operation.
 4) If at the end of bfs, we're not able to visit all vertices (i.e. some vertices remain un-visited) it means
    there is a cycle in the graph.

  `kahn's` algorithm is also used to find topological sort in directed graph.
  Topological sort is only applicable on directed-acyclic-graphs. If we apply toposort on
  a cyclic graphs, it won't give correct result. Infact, in Kahn's algo, we apply toposort regardles
  if the graph is cyclic or not. If the graph is not cyclic, after topsort also, we'll be able to visit all nodes.
  but if the graph is cyclic, the application of topsort cuts a node from the rest of the graph and we are not able to
  visit the entire graph - (intuition in strvr)
 */

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
//@strvr - https://www.youtube.com/watch?v=iTBaI90lpDQ&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5758360890376192
public class DetectCyclesInDirectedGraphBFS {
    /*** Kahn's algorithm ***/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        //Step 1: Find indegree array
        int[] inDegrees = new int[V];
        for (int vertex=0; vertex<V; vertex++) {
            ArrayList<Integer> neighbours = adj.get(vertex);
            for (int neighbour: neighbours) {
                inDegrees[neighbour] += 1; //coz the current vertex is pointing all these neighbours.
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
