package ptrn.graph;

import java.util.ArrayList;

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
//@strvr - https://www.youtube.com/watch?v=zQ3zgFypzX4&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5758360890376192
public class DetectCyclesInUndirectedGraphDFS {
    /*
        idea is exactly the same as detecting cycle in undirected graph using BFS
    * */
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                Vertex v = new Vertex(i, -1);
                if (dfs(v, adj, visited)) return true;
            }
        }
        return false;
    }

    private boolean dfs(Vertex currVertex, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        visited[currVertex.vertex] = true;
        ArrayList<Integer> neighbours = adj.get(currVertex.vertex);
        for (int neighbour: neighbours) {
            if (!visited[neighbour]) {
                if (dfs(new Vertex(neighbour, currVertex.vertex), adj, visited)) return true;
            } else if (neighbour != currVertex.parent) return true;
        }

        return false;
    }
}

class Vertex {
    int vertex;
    int parent;

    public Vertex(int vertex, int parent) {
        this.vertex = vertex;
        this.parent = parent;
    }
}
