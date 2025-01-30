package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/description/
//@check - https://www.youtube.com/watch?v=Iku-NBN2jRE&ab_channel=codestorywithMIK
public class DivideNodesIntoMaximumNumberOfGroups {
    /*
        I had a slight hunch for this problem in the sense
        that we can run bfs from each node to solve.
        But missed the details like checking if graph is bipartite or not,
        summing the max from each disconnected component, etc.

        Following is the soln coded by me, but explanation by mik.
    */
    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            int u = edge[0] - 1; //-1 to convert 0 based indexing to 1 based indexing.
            int v = edge[1] - 1;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }


        //Bipartite check.
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        for (int node=0; node < n; node+=1) {
            if (colors[node] == -1) {
                //If graph isn't bipartite, no answer is possible. Why?
                if (!isBipartite(graph, node, colors, 0)) return -1;
            }
        }

        //For each node, run a BFS and find max possible groupings.
        //Select max from all possible maxes.
        int[] maxGroups = new int[n]; //max groups possible when running bfs from a given node.
        for (int node=0; node<n; node += 1) {
            maxGroups[node] = bfs(graph, node, n);
        }



        int maxGroupInEachComp = 0;
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        for (int node=0; node<n; node += 1) {
            if (visited[node] == -1) {
                maxGroupInEachComp += dfs(graph, node, visited, maxGroups);
            }
        }

        return maxGroupInEachComp;
    }

    private int dfs(List<List<Integer>> graph, int node, int[] visited, int[] maxGroups) {

        int max = maxGroups[node];
        visited[node] = 0;

        for (int ngbr: graph.get(node)) {
            if (visited[ngbr] != 0) {
                visited[ngbr] = 0;
                max = Math.max(max, dfs(graph, ngbr, visited, maxGroups));
            }
        }

        return max;
    }

    private int bfs(List<List<Integer>> graph, int node, int n) {
        int[] visited = new int[n];
        Arrays.fill(visited, -1);

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(node);
        visited[node] = 0;

        int level = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz > 0) {
                int curr = q.removeFirst();
                for (int ngbr: graph.get(curr)) {
                    if (visited[ngbr] == 0) continue;
                    q.addLast(ngbr);
                    visited[ngbr] = 0;
                }

                sz -= 1;
            }

            level += 1; //one extra will be incremented after last level.
        }

        return level-1;

    }

    private boolean isBipartite(List<List<Integer>> graph, int node, int[] colors, int color) {
        colors[node] = color;

        for (int ngbr: graph.get(node)) {
            if (colors[ngbr] == colors[node]) {
                return false;
            }

            if (colors[ngbr] == -1) {
                if (!isBipartite(graph, ngbr, colors, 1-color)) return false;
            }
        }

        return true;
    }
}
