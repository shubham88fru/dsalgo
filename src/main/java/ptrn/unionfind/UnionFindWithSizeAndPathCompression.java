package ptrn.unionfind;

public class UnionFindWithSizeAndPathCompression {
    public int[] parent;
    public int[] size;

    // Constructor
    public UnionFindWithSizeAndPathCompression(int n) {

        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1; //size is initialized to 1 (unlike rank which is initialized to 0).
        }
    }

    // Function to find which subset a particular element belongs to
    // Returns FALSE if both vertices have the same parent, otherwise,
    // updates the parent and rank lists by making a connection based on the passed edge
    // Returns TRUE if no cycle exits in the graph
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]); //path compression.
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

        // size.
        else if (size[p1] > size[p2]) {
            parent[p2] = p1;
            size[p1] += size[p2];
        } else {
            parent[p1] = p2;
            size[p2] += size[p1];
        }

        return true;
    }
}
