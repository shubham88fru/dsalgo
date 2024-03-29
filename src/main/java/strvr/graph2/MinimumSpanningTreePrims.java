package strvr.graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
    Spanning Tree
    -------------
    --> A spanning tree of a graph is possible only for weighted, undirected and connected graphs
    --> It is a tree consisting of all the vertices of the graph.
    --> Since it is a tree, it doesn't have any cycles ofc.
    --> In a graph of n vertices, the spanning tree has n-1 edges.
    --> A minimum spanning tree is a spanning tree which has the least sum of edge weights.
    --> Two algorithms are used to find MST:
        1) Prim's algorithm
            --> Very similar to Dijkstra, just with a slight modification.
        2) Kruskal's algorithm
            --> Step 1 of kruskal algo is to sort the edges array in ascending order of weights.
            --> Step 2, pick each edge one by one, if for an edge, parents of both vertices are same, means it is forming a cycle, ignore it.
                otherwise, do union of the vertices.
            --> Works with the help of Disjoint set data structure (aka union find data structure, aka disjoint set union)
            --> A disjoint set is DS that stores a collection of non-overlapping sets. It supports two operations - union, and find.
            --> union operation combines two subsets, while find operation returns the root node of subset in which the element belongs.
            -------------------------------------------
            ********Simple Find function (O(n))********
            -------------------------------------------
            private int find(int[] parents, int a) {
                if (parents[a] == a)
                    return a;
                return find(parents, parents[a]);
            }

            -----------------------------------------------------------
            ********Find by path compression function (O(logn))********
            -----------------------------------------------------------
            private int find(int[] parents, int a) {
                if (parents[a] == a) return a;
                else
                    parents[a] = find(parents, parents[a]);
                return parents[a];
            }

            --------------------------------------------
            ********Simple Union function (O(n))********
            **gives a degenerate tree, hence linear TC**
            --------------------------------------------
            private void union(int[] parents, int a, int b) {
                int rootA = find(parents, a);
                int rootB = find(parents, b);

                parents[rootA] = rootB;
            }

            ------------------------------------------------
            ********Union by rank function (O(logn))********
            *****gives a balanced tree, hence better TC*****
            ------------------------------------------------
            private void union(int[] parents, int[] ranks, int a, int b) {
                int rootA = find(parents, a);
                int rootB = find(parents, b);

                if (ranks[rootA] > ranks[rootB])
                    parents[rootB] = rootA;
                else if (ranks[rootB] > ranks[rootA])
                    parents[rootA] = rootB;
                else {//if equal, make anyone as parent and increase the parents rank by 1.
                    parents[rootA] = rootB;
                    ranks[rootB] += 1;
                }

                parents[rootA] = rootB;
            }


 */
//@link - https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
//@strvr - https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
public class MinimumSpanningTreePrims {
    int spanningTree(int V, int E, int[][] edges){

        //get adjacency list rep of the graph
        //neighbours with their costs.
        ArrayList<ArrayList<ArrayList<Integer>>> adj
                = getNeighboursWithCost(V, E, edges);

        //Apply prims algorithm.
        //output will be array of costs
        //where each index will represent the cost of
        //reaching the vertex from previous node
        //in the min spanning tree.
        int[] minSpanningTreeCosts = primsAlgoForMinSpanTree(V, adj);

        //sum the costs because question needs that.
        int sum = 0;
        for (int cost: minSpanningTreeCosts) sum += cost;
        return sum;
    }

    //Retruns list of neighbours for each vertex with their costs.
    private ArrayList<ArrayList<ArrayList<Integer>>> getNeighboursWithCost(int V, int E, int[][] edges) {
        ArrayList<ArrayList<ArrayList<Integer>>> neighboursWithCost
                = new ArrayList<>();
        for (int i=0; i<V; i++) {
            neighboursWithCost.add(new ArrayList<>());
        }

        int e = edges.length;
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            //Since this is an undirected graph
            //and edge [u, v, w] means an edge from u to v with weight w
            //as well as an edge from v to u with weight w.
            ArrayList<Integer> neighbourWithCost1 = new ArrayList<>();
            neighbourWithCost1.add(v);
            neighbourWithCost1.add(cost);

            ArrayList<Integer> neighbourWithCost2 = new ArrayList<>();
            neighbourWithCost2.add(u);
            neighbourWithCost2.add(cost);

            neighboursWithCost.get(u).add(neighbourWithCost1);
            neighboursWithCost.get(v).add(neighbourWithCost2);
        }

        return neighboursWithCost;
    }

    //1) Prim's algorithm to find min spanning tree.
    //Note, even thought this algorithm is simpler to understand (for it being similar to dijkstra),
    //it's shortcoming is that it only gives the costs array of the minimum spanning tree, it doesn't
    //give the spanning tree edges. Strvr's algo gives the tree also.
    private int[] primsAlgoForMinSpanTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        //After completion of prims algo, costs array will
        //consist of min costs of reaching each vertex from its
        //previous node in the min spanning tree.
        int[] costs = new int[V];

        //Initially cost of visiting all vertices from
        //vertex S is -1.
        Arrays.fill(costs, -1);

        //Priority queue (instead of a usual queue) is used in implementing
        //prims. Here, the priority queue will sort the added values in
        //natural order of the cost.
        PriorityQueue<VertexCost> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new VertexCost(0, 0)); //cost of start index to itself is zero.

        while (!pq.isEmpty()) {
            VertexCost vertexAndCost = pq.remove();
            int currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            //Means already visited, hence just skip it.
            if (costs[currVertex] != -1) continue;

            costs[currVertex] = currCost;
            ArrayList<ArrayList<Integer>> neighboursWithCost
                    = adj.get(currVertex);

            for (ArrayList<Integer> neighbourWithCost: neighboursWithCost) {
                int currentNeighbour = neighbourWithCost.get(0);
                int currentEdgeCost = neighbourWithCost.get(1);

                //IMP: This is basically the only diff between dijkstra and
                //prims. In dijkstra, we add current cost and current edge cost
                //but in prims we only consider the current edge cost because we
                //dont bother about the cost from the source. All we need it the
                //cost of the edge (i.e. from the previous vertex)
                pq.add(new VertexCost(currentNeighbour, (currentEdgeCost)));
            }
        }

        return costs;
    }

    //2) Kruskal's algorithm to find minimum spanning tree.
}