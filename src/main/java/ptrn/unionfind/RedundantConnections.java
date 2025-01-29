package ptrn.unionfind;

/**
 * I have almost no clue what just happened in this question.
 * Or for that matter, in the union find pattern in general.
 * Not sure, how commonly is this pattern a part of technical assessments/interviews.
 *
 * Soln below is direct copypasta from edctv.
 */
//@link - https://leetcode.com/problems/redundant-connection/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5106281544417280
//       - https://www.youtube.com/watch?v=EawxDEOxeIk&t=1165s&ab_channel=codestorywithMIK

/**
 * To recap, the solution to this problem can be divided into the following two main parts:
 *
 * Initialize parent and rank arrays based on the length of the edges array.
 *
 * Traverse the edges array and for each edge, compare the parents of both vertices:
 *
 * If the parents are the same, the current edge is redundant, so we return it.
 *
 * Otherwise, we connect the two vertices based on their respective ranks.
 */

public class RedundantConnections {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind connections = new UnionFind(edges.length);

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if (!connections.union(v1, v2)) {
                return edge;
            }
        }
        return new int[]{};
    }
}

//Template for Union find algorithm class.
//With path compression and union by rank.
class UnionFind {

    public int[] parent;
    public int[] rank;

    // Constructor
    public UnionFind(int n) {

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Function to find which subset a particular element belongs to
    // Returns FALSE if both vertices have the same parent, otherwise,
    //updates the parent and rank lists by making a connection based on the passed edge
    // Returns TRUE if no cycle exits in the graph
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }

    // Function to join two subsets into a single subset
    public boolean union(int v1, int v2) {

        // Find the root parents of v1 and v2
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return false;
        }

        // Updates the parent and rank lists otherwise
        else if (rank[p1] > rank[p2]) {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        } else {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        }

        return true;
    }
}

//2) Sol2: Still brute force, but slightly better.
//Iterate the given edges array from the start and keep forming the graph, for each
//edge, keep checking if the src and dest nodes are already present in the graph. If yes, then run a bfs/dfs from either and check if we can already reach from src to dest or vice-versa. If we can, then it means that adding this new edge is redundant and going to form a cycle in the graph.

//3) Sol3: Brute force.
//Form the graph. Then iterate the given edges from back
//and for each edge and for edge, remove the edge (temporarily) from
//the graph and see if the resultant graph has a cycle. If yes, then
//current edge is the redundant edge.
