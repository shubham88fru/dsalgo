package strvr.graph;

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
            Here, vertex 0 is connected to 2
            vertex 1 is connected to 0 ,1 , and 2
            vertex 2 is connected to 1
*/

/*
Note how there's no concept of preorder,postorder or inorder in graphs.
Graphs just have DFS and BFS.
Also, be it DFS or BFS, both have the concept of visited array.
* */
//@link - https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
//@strvr - https://takeuforward.org/data-structure/depth-first-search-dfs/
public class DFSOfGraph {
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        //return traverseDFS(0, adj, new ArrayList<Integer>());
        simpleDfs(0, adj, new ArrayList<>(), ans);
        return ans;
    }

    //2) Maybe a little simpler to comprehend?
    private void simpleDfs(int curr, ArrayList<ArrayList<Integer>> adj,
                           ArrayList<Integer> visited, ArrayList<Integer> ans) {
        visited.add(curr);
        ans.add(curr);

        ArrayList<Integer> neighbours = adj.get(curr);
        for (int neighbour: neighbours) {
            if (!visited.contains(neighbour)) simpleDfs(neighbour, adj, visited, ans);
        }
    }

    //1) If want a recursive dfs that returns the list instead of storing in global list.
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
