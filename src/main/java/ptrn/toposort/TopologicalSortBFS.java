package ptrn.toposort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/*
 Topological sort is nothing but Kahn's algorithm.
 Definition: Linear order of vertices such that if there is an edge from
 u to v, u appears before v in the ordering.
 Topological sort is only valid in Directed acyclic graphs, because
 concept of directional edges makes sense in directed graph only.

 topo sort algo:
    step 1: Insert all nodes with indegree 0 to the bfs queue (i.e. all the sources)
    step 2: keep removing elements from queues (add them to ans array)
            and decrease the indegrees of the removed element's neighbours.
    step 3: If in step 2 indegree of any neighbour becomes 0 then insert it also
            in the queue.
    steps 4: keep repeating till queue is empty.
* */
//@link - https://practice.geeksforgeeks.org/problems/topological-sort/1
//@strvr -https://takeuforward.org/data-structure/topological-sort-bfs/
public class TopologicalSortBFS {
    int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        /*
        Note that for topo sort using bfs, we don't
        loop over visited array to account for disjoint
        graph - Maybe because the question states that the graph is
        not disjoint? not sure. We do it however for the DFS approach.
        * */
        return topologicalSortBFS(V, adj);
    }

    private int[] topologicalSortBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegrees = new int[V];
        //find indegrees.
        for (int i=0; i<V; i++) {
            ArrayList<Integer> neighbours = adj.get(i);
            for (int neighbour: neighbours) {
                indegrees[neighbour] += 1;
            }
        }

        //typical bfs using queue.
        int[] ans = new int[V];
        Deque<Integer> q = new ArrayDeque<>();
        //add initial vertices with 0 indegree to queue.
        for (int i=0; i<V; i++) {
            if (indegrees[i] == 0) q.addLast(i);
        }

        int i=0;
        while (!q.isEmpty()) {
            //keep removing from queue,
            //decreasing indegrees of neighbours
            //and adding neighbours to queue if their
            //indegree becomes 0 after decreasing.
            int v = q.removeFirst();
            ans[i++] = v;
            ArrayList<Integer> neighbours = adj.get(v);
            for (int neighbour: neighbours) {
                indegrees[neighbour] -= 1;
                if (indegrees[neighbour] == 0) q.addLast(neighbour);
            }
        }

        return ans;
    }
}
