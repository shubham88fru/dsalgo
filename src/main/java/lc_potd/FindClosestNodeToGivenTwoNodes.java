package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/find-closest-node-to-given-two-nodes/description/
//@check - https://www.youtube.com/watch?v=Vn-voe1XNtQ&ab_channel=codestorywithMIK
public class FindClosestNodeToGivenTwoNodes {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        /*
            An important point for this problem was that
            each node only has atmost one outgoing edge.
            It means that we can reach any node only in one way.
            Therefore, a simple BFS/DFS from any node will give
            to the shortest distance from the starting node to
            current node. We don't have to use dijkstra or anything
            like that.
        */

        return dfs(edges, node1, node2);
    }

    /* *
        Coded by me based on mik's explanation.
     */
    private int dfs(int[] edges, int node1, int node2) {
        int n = edges.length;

        int[] dist1 = new int[n];
        Arrays.fill(dist1, -1);
        dfs(edges, node1, -1, dist1);

        int[] dist2 = new int[n];
        Arrays.fill(dist2, -1);
        dfs(edges, node2, -1, dist2);

        int min = Integer.MAX_VALUE;
        int mini = -1;
        for (int i=0; i<n; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                if (min > Math.max(dist1[i], dist2[i])) {
                    min = Math.max(dist1[i], dist2[i]);
                    mini = i;
                }
            }
        }

        return mini;
    }

    private void dfs(int[] edges, int node, int prev, int[] dist) {
        if (dist[node] != -1) return;

        dist[node] = prev + 1;

        int ngbr = edges[node];
        if (ngbr >= 0) {
            dfs(edges, ngbr, dist[node], dist);
        }

    }
}
