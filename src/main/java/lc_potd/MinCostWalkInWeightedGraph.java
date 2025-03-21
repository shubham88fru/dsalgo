package lc_potd;

//@link - https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/description/
//@check - https://www.youtube.com/watch?v=B3Wgf-g6i-k&ab_channel=codestorywithMIK
public class MinCostWalkInWeightedGraph {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        return mikssol(n, edges, query);
    }

    /*
        The important observation to solve this problem
        is that when performing `&` on values, the resultant
        will at max be equal to max of the two value, result
        of `&` operation can never be larger.

        However, there are small details that need regular
        revision.
    */
    private int[] mikssol(int n, int[][] edges, int[][] query) {
        UnionFind22 uf = new UnionFind22(n);

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            uf.union(u, v, w);
        }

        int[] ans = new int[query.length];
        for (int i=0; i<query.length; i++) {
            int s = query[i][0];
            int d = query[i][1];

            ans[i] = uf.query(s, d);
        }

        return ans;
    }
}

class UnionFind22 {
    int[] parents;
    int[] costs;

    public UnionFind22(int n) {
        parents = new int[n];
        costs = new int[n];

        for (int i=0; i<n; i++) {
            parents[i] = i;
            costs[i] = -1; //this is important. a & -1 = a. Mik told this.
        }
    }

    public int find(int u) {
        if (parents[u] == u) return u;
        return parents[u] = find(parents[u]);
    }

    public void union(int u, int v, int newEdgeWeight) {
        int p1 = find(u);
        int p2 = find(v);

        costs[p2] &= newEdgeWeight;

        if (p1 == p2) {
            return ;
        }

        parents[p1] = p2;
        costs[p2] &= costs[p1];
    }

    public int query(int u, int v) {
        if (u == v) return 0;
        int p1 = find(u);
        int p2 = find(v);

        if (p1 != p2) return -1;
        return costs[p2];
    }
}
