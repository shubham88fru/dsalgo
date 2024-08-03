package ptrn.unionfind;

//@link - https://leetcode.com/problems/regions-cut-by-slashes/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5683710436573184
//@copyPasta - from @check. Has nice visual rep.
public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        UnionFind9 findUnion = new UnionFind9(4 * N * N);

        // Traversing the list
        for (int r = 0; r < N; ++r) {

            for (int c = 0; c < N; ++c) {

                int root = 4 * (r * N + c);
                char val = grid[r].charAt(c);
                if ((val == '/') || (val == ' ')) {

                    // Connecting the north and west components of the box
                    findUnion.union(root + 0, root + 1);

                    // Connecting the east and south components of the box
                    findUnion.union(root + 2, root + 3);
                }

                if ((val == '\\') || (val == ' ')) {

                    // Connecting the north and east components of the box
                    findUnion.union(root + 0, root + 2);

                    // Connecting the west and south components of the box
                    findUnion.union(root + 1, root + 3);
                }

                // Connecting the south component of the current box with the north component of the box below it
                if (r + 1 < N)
                    findUnion.union(root + 3, (root + 4 * N) + 0);

                // Connecting the north component of the current box with the south component of the box above it
                if (r - 1 >= 0)
                    findUnion.union(root + 0, (root - 4 * N) + 3);

                // Connecting the east component of the current box with the west component of the box on its right
                if (c + 1 < N)
                    findUnion.union(root + 2, (root + 4) + 1);

                // Connecting the west component of the current box with the east component of the box on its left
                if (c - 1 >= 0)
                    findUnion.union(root + 1, (root - 4) + 2);
            }
        }

        // Finding the number of connected components
        int count = 0;
        for (int x = 0; x < 4 * N * N; ++x) {
            if (findUnion.find(x) == x)
                count++;
        }

        return count;
    }
}

class UnionFind9 {
    private int[] parent;
    private int[] rank;

    // Constructor
    public UnionFind9(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Function to find which subset a particular element belongs to.
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }

    // Function to join two subsets into a single subset.
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 != p2) {
            if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] += rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] += rank[p1];
            }
        }
    }
}
