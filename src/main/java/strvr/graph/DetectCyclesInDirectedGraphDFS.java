package strvr.graph;

import java.util.ArrayList;
import java.util.*;

/*
*  Although it might seem that the same algorithm used for detecting cycles in
*   undirected might work for directed graphs as well, but there are some cases
*  where it would fail. E.g. if a graph has a component like below, the algo for
* detecting cycle in undirected graph would say it has a cycle, while in reality,
* it doesn't.
*           >4
*         /     \
*        /       \
*    ----3        >5
*         \      /
*          \   /
*            >6
* */


//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
//@strvr - https://www.youtube.com/watch?v=uzVUw90ZFIg&ab_channel=takeUforward
public class DetectCyclesInDirectedGraphDFS {
    //This is some nasty code, which not even swd was confident about.
    //check day 4 video on graphs if need refresher.
    /*** 1) SWD 2nd soln - Works ***/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        //This will keep track of all nodes in recursive stack
        //in the current recursive call. For a graph to be cyclic,
        //we should be revisiting a already visited node **and** also
        //the node should be in the current recursive stack.
        Set<Integer> currRecursiveStack = new HashSet<>();

        for (int currentVertex = 0; currentVertex < V; currentVertex++) {
            if (!visited[currentVertex]){
                if (hasCycle(adj, currentVertex, currRecursiveStack, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    //Try to follow this code on the diagram above, for
    //understanding better why we need the currRecursiveStack.
    private boolean hasCycle(ArrayList<ArrayList<Integer>> adj,
                             int currentVertex, Set<Integer> currRecursiveStack, boolean[] visited) {

        visited[currentVertex] = true;
        currRecursiveStack.add(currentVertex);

        ArrayList<Integer> neighbours = adj.get(currentVertex);

        for (int neighbour: neighbours) {
            if (!visited[neighbour]) {
                if (hasCycle(adj, neighbour, currRecursiveStack, visited)) {
                    return true;
                }
            }

            //if the node has been visited in the dfs means,
            //while traversing from some parent, we are encountering the current node
            //for the second time.
            if (currRecursiveStack.contains(neighbour)) {
                return true;
            }
        }

        //remove the curr node from recursive stack when we back track.
        //Note: we don't remove like this from the visited array.
        currRecursiveStack.remove(currentVertex);
        return false;
    }


}
