package lc_potd;

//@link - https://leetcode.com/problems/find-champion-ii/description/
public class FindChampionII {
    public int findChampion(int n, int[][] edges) {
        int[] indeg = new int[n];
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            indeg[v] += 1;
        }

        /*
            Winner will have an indegree of 0.
            If two or more nodes have an indegree of
            zero, then there's no winner.
        */
        int winner = -1;
        for (int i=0; i<n; i++) {
            if (indeg[i] == 0) {
                if (winner != -1) return -1;
                winner = i;
            }
        }

        return winner;
    }
}
