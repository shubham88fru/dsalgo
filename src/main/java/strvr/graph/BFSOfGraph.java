package strvr.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class BFSOfGraph {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        return traverseBFS(V, adj);
    }

    private ArrayList<Integer> traverseBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        if (adj == null) return null;
        List<Integer> visited = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(0);
        visited.add(0);

        //keep processing till the queue is empty.
        while (!queue.isEmpty()) {
            int currVertex = queue.removeFirst();
            ans.add(currVertex);

            //Get all neighbours of current vertex.
            //and add them all to queue. Add to queue
            //only if they are not already visited.
            ArrayList<Integer> neighbours = adj.get(currVertex);
            for (int neighbour: neighbours) {
                if (!visited.contains(neighbour)) {
                    queue.addLast(neighbour);
                    visited.add(neighbour);
                }
            }
        }

        return ans;
    }
}
