package swd.graphs;

import java.util.ArrayList;

//@link - https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class DFSOfGraph {
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        return traverseDFS(0, adj, new ArrayList<Integer>());
    }

    private ArrayList<Integer> traverseDFS(int currEl, ArrayList<ArrayList<Integer>> adj,
                                           ArrayList<Integer> visited) {
        //If already visited, no need to visit again.
        if (visited.contains(currEl)) {
            return null;
        }

        //else, visit and add to list.
        ArrayList<Integer> currList = new ArrayList<>();
        visited.add(currEl);
        currList.add(currEl);

        //then visit depth first visit the neighbours.
        ArrayList<Integer> neighbours = adj.get(currEl);
        ArrayList<Integer> neighbourList = new ArrayList<>();
        for (int i=0; i<neighbours.size(); i++) {
            neighbourList = traverseDFS(neighbours.get(i), adj, visited);
            if (neighbourList != null) currList.addAll(neighbourList);
        }

        return currList;
    }
}
