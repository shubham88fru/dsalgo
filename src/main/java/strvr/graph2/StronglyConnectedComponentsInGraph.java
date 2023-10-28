package strvr.graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Strongly connected components of a graph are those group (components) of nodes
 * where if you start traversing from any node of a group (component), you can
 * reach all other nodes of the group (component).
 *
 * Apparently, there are many graph algorithms to find strongly connected components.
 * a) Kosaraju's algorithm.
 * b) Tarjans's algorithm.
 *
 * --------------------
 * Kosaraju's algorithm
 * --------------------
 * step 1: Sort all the nodes in order of finishing time --> using topological sort. ---- `O(N)`
 * step 2: Transpose the graph --> transposing a graph means, reversing all the edges of graph. --- `O(N+E)`
 * step3 : Run a dfs on the transposed graph (result of step 2) starting with the nodes in --- `O(N+E)`
 *         order of finishing time --> nodes will be in a stack after step 1.
 */

//@link - https://practice.geeksforgeeks.org/problems/strongly-connected-component-tarjanss-algo-1587115621/1
//@strvr - https://www.youtube.com/watch?v=iTBaI90lpDQ
public class StronglyConnectedComponentsInGraph {
    public ArrayList<ArrayList<Integer>> tarjans(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        return kosaraju(V, adj);
    }

    /**
     * Doens't work on the above gfg link though.
     * The OG gfg question requires to implement it using tarjan
     * but I thought that even kosaraju should be able to submit the question
     * but it doesn't.
     */
    private ArrayList<ArrayList<Integer>> kosaraju(int n, ArrayList<ArrayList<Integer>> adj) {

        //Step 1: Topo sort.
        int vis[] = new int[n];
        Stack<Integer> st = new Stack<Integer>();
        for (int i=0; i<n; i++) {
            if (vis[i] == 0) dfs(i, st, adj, vis); //dfs for topological sort.
        }

        //Step 2: Transpose
        //transpose the graph. i.e. e1--->e2 becomes e1<---e2
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<n; i++) transpose.add(new ArrayList<Integer>());
        for (int i=0; i<n; i++) {
            vis[i] = 0;
            for (int it: adj.get(i)) transpose.get(it).add(i);
        }

        //Step 3: DFS on transpose.
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (st.size() > 0) {
            ArrayList<Integer> components = new ArrayList<>();
            int node = st.peek();
            st.pop();
            if (vis[node] == 0) {
                revDfs(node, transpose, vis, components);
                ans.add(components);
            }
        }

        return ans;
    }


    private void dfs(int node, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj, int[] vis){
        vis[node] = 1;
        for (int it: adj.get(node)) {
            if (vis[it] == 0) dfs(it, st, adj, vis);
        }
        st.push(node);
    }

    private void revDfs(int node, ArrayList<ArrayList<Integer>> transpose, int[] vis, ArrayList<Integer> components) {
        vis[node] = 1;
        components.add(node);
        for (int it: transpose.get(node)) {
            if (vis[it] == 0) revDfs(it, transpose, vis, components);
        }
    }
}
