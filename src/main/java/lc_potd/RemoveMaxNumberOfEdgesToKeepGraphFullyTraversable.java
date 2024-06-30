package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/description/
//@check - https://www.youtube.com/watch?v=ukLyFDlBFW0&t=1048s&ab_channel=AryanMittal
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    /**
     Based on the Union find concept. Need to understand union find
     well first and retry this problem again.
     Check aryan and other's soln (after understanding union find properly)
     when this question reappears.
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {

        //Doesn't work if this sorting is not done. Whilst, aryan claims
        //that this sorting is not needed.
        Arrays.sort(edges, (edge1, edge2) -> edge2[0]-edge1[0]);

        UnionFind u_bob = new UnionFind(n);
        UnionFind u_alice = new UnionFind(n);

        int removedEdges = 0;
        int aliceEdges = 0;
        int bobEdges = 0;

        for (int[] edge: edges) {
            if (edge[0] == 3) {
                if (u_alice.union(edge[1], edge[2])) { //both alice and bob
                    u_bob.union(edge[1], edge[2]);
                    aliceEdges += 1;
                    bobEdges += 1;
                } else removedEdges += 1;
            } else if (edge[0] == 2) { //only bob.
                if (u_bob.union(edge[1], edge[2])) {
                    bobEdges += 1;
                } else removedEdges += 1;
            } else { //only alice.
                if (u_alice.union(edge[1], edge[2])) {
                    aliceEdges += 1;
                } else removedEdges += 1;
            }
        }

        return (aliceEdges == n-1 && bobEdges == n-1) ? removedEdges: -1;
    }
}

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

