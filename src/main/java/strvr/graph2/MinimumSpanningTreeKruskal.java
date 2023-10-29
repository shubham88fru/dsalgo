package strvr.graph2;

/**
         Kruskal's algorithm
 *             --> Step 1 of kruskal algo is to sort the edges array in ascending order of weights.
 *             --> Step 2, pick each edge one by one, if for an edge, parents of both vertices are same, means it is forming a cycle, ignore it.
 *                 For each edge we pick, we have to check if the two vertices in the edge belong to the same component or
 *                 not. We have to ignore if they belong to same component, because if we add them, they'll form a cycle and spanning tree
 *                 can not have cycles.
 *             --> TC: O(MlogM)
 *             --> Works with the help of Disjoint set data structure (aka union find data structure, aka disjoint set union)
 *             --> A disjoint set is DS that stores a collection of non-overlapping sets. It supports two operations - union, and find.
 *             --> union operation combines two subsets, while find operation returns the root node of subset in which the element belongs.
 *             -------------------------------------------
 *             ********Simple Find function (O(n))********
 *             -------------------------------------------
 *             private int find(int[] parents, int a) {
 *                 if (parents[a] == a)
 *                     return a;
 *                 return find(parents, parents[a]);
 *             }
 *
 *             -----------------------------------------------------------
 *             ********Find by path compression function (O(logn))********
 *             -----------------------------------------------------------
 *             private int find(int[] parents, int a) {
 *                 if (parents[a] == a) return a;
 *                 else
 *                     parents[a] = find(parents, parents[a]);
 *                 return parents[a];
 *             }
 *
 *             --------------------------------------------
 *             ********Simple Union function (O(n))********
 *             **gives a degenerate tree, hence linear TC**
 *             --------------------------------------------
 *             private void union(int[] parents, int a, int b) {
 *                 int rootA = find(parents, a);
 *                 int rootB = find(parents, b);
 *
 *                 parents[rootA] = rootB;
 *             }
 *
 *             ------------------------------------------------
 *             ********Union by rank function (O(logn))********
 *             *****gives a balanced tree, hence better TC*****
 *             ------------------------------------------------
 *             private void union(int[] parents, int[] ranks, int a, int b) {
 *                 int rootA = find(parents, a);
 *                 int rootB = find(parents, b);
 *
 *                 if (ranks[rootA] > ranks[rootB])
 *                     parents[rootB] = rootA;
 *                 else if (ranks[rootB] > ranks[rootA])
 *                     parents[rootA] = rootB;
 *                 else {//if equal, make anyone as parent and increase the parents rank by 1.
 *                     parents[rootA] = rootB;
 *                     ranks[rootB] += 1;
 *                 }
 *
 *                 parents[rootA] = rootB;
 *             }
 */
//@strvr - https://takeuforward.org/data-structure/kruskals-algorithm-minimum-spanning-tree-g-47/
public class MinimumSpanningTreeKruskal {
    //Refer strvr. Didn't write it.
    //Ignore till you really don't need it.
    //follow the link when needed.
}
