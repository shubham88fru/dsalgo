package ptrn.graph;

import java.util.*;

//@link - https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
//@strvr - https://www.youtube.com/watch?v=BPlrALf1LDU&ab_channel=takeUforward
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5758360890376192

public class DetectCyclesInUndirectedGraphBFS {
    /*
        Intuition behind detecting a cycle using bfs in a graph is that
        while performing dfs if we encounter a node that is already visited during
        bfs from a parent node other than current node, it means that there are two different paths
        to reach the same node, therefore there is a cycle in the graph.

        Note that we can't directly say a graph has a cycle if we encounter a node that has been visited.
        The visited node could very well be curr node's parent
        (since the graph is undirected, we can go to parent from child). Hence, we need to be sure that
        the visited node is not curr node's parent.
    * */
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        return swd(V, adj);
    }

    private boolean swd(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        int[] parents = new int[V];
        Arrays.fill(parents, -1);

        /*
            Note that this for loop is only needed to account for the
            fact that the graph may be disjoint, so when the first bfs runs,
            the nodes which are disjoint won't be visited.
        * */
        for (int currVertex = 0; currVertex < V; currVertex += 1) {
            if (!visited[currVertex]) {
                if (hasCycleBFSSWD(adj, currVertex, parents, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCycleBFSSWD(ArrayList<ArrayList<Integer>> adj, int currVertex, int[] parents, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(currVertex);
        parents[currVertex] = -1;
        visited[currVertex] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.remove();

            for (int neighbour: adj.get(currentNode)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    parents[neighbour] = currentNode; //record the parent before putting to queue.
                    queue.add(neighbour);
                }
                //if the adjacent node is already visited
                //and is not the parent of currVertex, it means:
                //1) its a strict neighbour (not parent)
                //2) the neighbour was visited by someone else before.
                //i.e. if the neighbour was already visited and the
                //neighbour was not the parent of current node - we have a cyle.
                else if (parents[currentNode] != neighbour) return true;
            }
        }

        return false;
    }
}
