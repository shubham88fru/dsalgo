package strvr.graph2;

import java.util.*;

//@link - https://practice.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1
//@strvr - https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/

/** More or less strvr's soln. DOESN'T WORK. Copy paste strvr code also doesn't run.
 * Maybe GFG added some new test cases?
 * Check strvr code again maybe.
 * */
public class ShortestPathInWeightedUndirectedGraph {
    public List<Integer> shortestPath(int n, int m, int[][] edges){
        //  Code Here.
        ArrayList<ArrayList<ArrayList<Integer>>> adj = getGraph(n, m, edges);
        return dijsktraFromGivenNode(n, m, adj, 1);
        //return Arrays.asList(dijsktraFromGivenNode(0))
    }

    private ArrayList<ArrayList<ArrayList<Integer>>> getGraph(int n, int m, int[][] edges) {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }


        for (int i=0; i<m; i++) {
            ArrayList<Integer> nodeAndWeight = new ArrayList<>();
            nodeAndWeight.add(edges[i][1]);
            nodeAndWeight.add(edges[i][2]);
            adj.get(edges[i][0]).add(nodeAndWeight);

            nodeAndWeight = new ArrayList<>();
            nodeAndWeight.add(edges[i][0]);
            nodeAndWeight.add(edges[i][2]);
            adj.get(edges[i][1]).add(nodeAndWeight);
        }
        return adj;
    }

    private List<Integer> dijsktraFromGivenNode(int n, int m, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        //After completion of dijkstra, costs array will
        //consist of shortest path of each node from the node `S`.
        int[] costs = new int[n+1];
        int[] parents = new int[n+1];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, Integer.MAX_VALUE);
        for (int i=1; i<=n; i++) {
            costs[i] = Integer.MAX_VALUE;
            parents[i] = i;
        }

        //Priority queue (instead of a usual queue) is used in implementing
        //dijsktra. Here, the priority queue will sort the added values in
        //natural order of the cost.
        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        costs[S] = 0;
        pq.add(new VertexCost(S, 0)); //cost of start index to itself is zero.

        while (!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            //Means already visited, hence just skip it.
            //this costs array kind of also acts like the
            //visited array of a normal graph traversal.
            //if (costs[currVertex] != -1) continue;

            //costs[currVertex] = currCost;
            ArrayList<ArrayList<Integer>> neighboursWithCost
                    = adj.get(currVertex);

            for (ArrayList<Integer> neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost.get(0);
                int currentEdgeCost = neighbourWithCost.get(1);
                //add to queue with current cost. The total cost (currcost + currEdgeCost)
                //will actually be the total cost of reaching that vertex from the source vertex.
                if (currCost + currentEdgeCost < costs[currentNeighbour]) {
                    costs[currentNeighbour] = currCost + currentEdgeCost;
                    pq.add(new VertexCost(currentNeighbour, (currCost + currentEdgeCost)));
                    parents[currentNeighbour] = currVertex;
                    //System.out.println("HERE");
                }

            }
        }

        List<Integer> path = new ArrayList<>();
        if (costs[n] == Integer.MAX_VALUE) {
            path.add(-1);
            return path;
        }

        int node = n;
        while (parents[node] != node) {
            path.add(node);
            node = parents[node];
        }

        path.add(1);
        Collections.reverse(path);
        return path;
    }
}
