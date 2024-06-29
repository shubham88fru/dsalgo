package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/maximum-total-importance-of-roads/
public class MaximumTotalImportanceOfRoads {
    public long maximumImportance(int n, int[][] roads) {
        int[] indeg = new int[n];
        for (int[] road: roads) {
            int u = road[0];
            int v = road[1];

            indeg[u] += 1;
            indeg[v] += 1;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a1, a2) -> a2[0]-a1[0]);
        for (int i=0; i<indeg.length; i++) {
            maxHeap.add(new int[] {indeg[i], i});
        }

        long[] importances = new long[n];
        long importance = n;
        while (!maxHeap.isEmpty()) {
            importances[maxHeap.remove()[1]] = importance;
            importance -= 1;
        }
        long maxImportance = 0;
        for (int[] road: roads) {
            maxImportance += importances[road[0]] + importances[road[1]];
        }

        return maxImportance;
    }
}
