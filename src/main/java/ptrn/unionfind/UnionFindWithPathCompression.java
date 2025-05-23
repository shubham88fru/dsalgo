package ptrn.unionfind;

public class UnionFindWithPathCompression {
    public int[] parent;

    // Constructor
    public UnionFindWithPathCompression(int n) {

        //not sure why edctv took a size of n+1.
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    // Function to find which subset a particular element belongs to
    // Returns FALSE if both vertices have the same parent, otherwise,
    //updates the parent and rank lists by making a connection based on the passed edge
    // Returns TRUE if no cycle exits in the graph
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]); //this is the path compression step and the only diff from vanilla impl.
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

        parent[p1] = p2; //choose any of p1 and p2 to be the parent of the merged group.

        return true;
    }
}
