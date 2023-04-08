package swd.graphs;

import java.util.ArrayList;
import java.util.*;

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
public class DetectCyclesInGraph {
    //This is some nasty code, which not even swd was confident about.
    //check day 4 video on graphs if need refresher.
    /*** 1) SWD 2nd soln - Works ***/
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        //This will keep track of all nodes in recursive stack
        //in the current recursive call. For a graph to be cyclic,
        //we should be revisiting a already visited node **and** also
        //the node should be in the current recursive stack.
        Set<Integer> recursiveStack = new HashSet<>();

        for (int currentVertex = 0; currentVertex < V; currentVertex++) {
            if (!visited[currentVertex]){
                if (hasCycle(adj, currentVertex, recursiveStack, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycle(ArrayList<ArrayList<Integer>> adj,
                             int currentVertex, Set<Integer> recursiveStack, boolean[] visited) {

        visited[currentVertex] = true;
        recursiveStack.add(currentVertex);

        ArrayList<Integer> neighbours = adj.get(currentVertex);

        for (int neighbour: neighbours) {
            if (!visited[neighbour]) {
                if (hasCycle(adj, neighbour, recursiveStack, visited)) {
                    return true;
                }
            }

            if (recursiveStack.contains(neighbour)) {
                return true;
            }
        }

        recursiveStack.remove(currentVertex);
        return false;
    }

    /*** 2) SWD Original Soln - Doesn't work ***/
    // public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
    //     boolean[] visited = new boolean[V];
    //     Set<Integer> recursiveStack = new HashSet<>();

    //     for (int currentVertex = 0; currentVertex < V; currentVertex++) {
    //         if (!visited[currentVertex]){
    //             if (hasCycle(adj, currentVertex, recursiveStack, visited)) {
    //                 return true;
    //             }
    //         }
    //     }

    //     return false;
    // }

    // private boolean hasCycle(ArrayList<ArrayList<Integer>> adj,
    //                             int currentVertex, Set<Integer> recursiveStack, boolean[] visited) {

    //     visited[currentVertex] = true;
    //     recursiveStack.add(currentVertex);

    //     ArrayList<Integer> neighbours = adj.get(currentVertex);

    //     for (int neighbour: neighbours) {
    //         if (!visited[neighbour]) {
    //             return hasCycle(adj, neighbour, recursiveStack, visited);
    //         }
    //         return recursiveStack.contains(neighbour);
    //     }

    //     recursiveStack.remove(currentVertex);
    //     return false;
    // }

    /*** 3) My Soln - Doesn't work ***/
    // public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
    //     // code here
    //     boolean[] visited = new boolean[adj.size()];

    //     for (int i=0; i<adj.size(); i++) {
    //         boolean ans = dfs(adj, i, new boolean[adj.size()]);
    //         if (ans) return true;
    //     }

    //     return false;
    // }

    // private boolean dfs(ArrayList<ArrayList<Integer>> adj, int curr, boolean[] visited) {
    //     if (visited[curr]) return true;

    //     visited[curr] = true;
    //     ArrayList<Integer> neighbours = adj.get(curr);

    //     boolean ans = false;
    //     for (int neighbour: neighbours) {
    //         ans = ans || dfs(adj, neighbour, visited);
    //         if (ans) return true;
    //     }

    //     return ans;
    // }
}
