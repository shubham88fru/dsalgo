package swd.graphs;

import java.util.ArrayList;

/*
    Representation of graph
    -----------------------
    A graph is represented either through an `adjacency list` of `adjacency matrix`.
        1) Adjacency list
           eg: [[1,2,3], [0], [1], []]
           Here, vertex 0 is connected to 1, 2, and 3
           vertex 1 is connected to 0
           vertex 2 is connected to 1
           and vertex 3 is connected to no other node.
        2) Adjacency matrix
            eg: [[0, 0, 1], [1, 1, 1], [0 , 1, 0]]
            Here, vertex 0 is connected to 3
            vertex 1 is connected to 1 ,2 , and 3
            vertex 2 is connected to 1
 */

//@link - https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class DFSOfGraph {
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        return traverseDFS(0, adj, new ArrayList<Integer>());
    }

    private ArrayList<Integer> traverseDFS(int currVertex, ArrayList<ArrayList<Integer>> adj,
                                           ArrayList<Integer> visited) {
        //If already visited, no need to visit again.
        if (visited.contains(currVertex)) {
            return null;
        }

        //else, visit and add to list.
        ArrayList<Integer> currList = new ArrayList<>();
        visited.add(currVertex);
        currList.add(currVertex);

        //then visit depth first visit the neighbours.
        ArrayList<Integer> neighbours = adj.get(currVertex);
        ArrayList<Integer> neighbourList = new ArrayList<>();
        for (int i=0; i<neighbours.size(); i++) {
            neighbourList = traverseDFS(neighbours.get(i), adj, visited);
            if (neighbourList != null) currList.addAll(neighbourList);
        }

        return currList;
    }
}
