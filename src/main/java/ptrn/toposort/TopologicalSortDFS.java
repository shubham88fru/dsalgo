package ptrn.toposort;

import java.util.ArrayList;
import java.util.Stack;

//@link - https://practice.geeksforgeeks.org/problems/topological-sort/1
//@strvr -https://takeuforward.org/data-structure/topological-sort-bfs/
public class TopologicalSortDFS {
    int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<Integer>();
        for (int i=0; i<V; i++) {
            if (vis[i] == 0) dfs(i, vis, st, adj);
        }

        int[] ans = new int[V];
        int i = 0;

        //after dfs, take out the content of the stack
        //it will be a valid topo order - why? No idea.
        while (!st.isEmpty()) {
            ans[i++] = st.peek();
            st.pop();
        }
        return ans;
    }

    private void dfs(int node, int[] vis, Stack<Integer> st,
                     ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        for (int it: adj.get(node)) {
            if (vis[it] == 0) dfs(it, vis, st, adj);
        }
        //before returning push it to stack. why? No idea.
        st.push(node);
    }
}
