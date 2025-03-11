package ptrn.graph;

import java.util.*;

//@link - https://leetcode.com/problems/cheapest-flights-within-k-stops/
//@check - https://www.youtube.com/watch?v=VmUpydhNmuw&ab_channel=codestorywithMIK
public class CheapestFlightWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // return pass1(n, flights, src, dst, k);
        // return pass3(n, flights, src, dst, k);
        return mikssol(n, flights, src, dst, k);
    }

    /*
        Plain DFS - gave TLE
        Plain BFS - gave MLE
        Dijsktra - didn't work.

        I don't know how on earth will I ever not think dijkstra for this.
    */
    private int mikssol(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = getGraph(n, flights);

        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {src, 0});

        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        int stops = 0;

        while (!q.isEmpty() && stops <= k) {
            int sz = q.size();

            while (sz > 0) {
                int[] curr = q.removeFirst();
                List<int[]> ngs = graph.get(curr[0]);
                for (int[] ng: ngs) {
                    if (curr[1] + ng[1] < costs[ng[0]]) {
                        costs[ng[0]] = curr[1] + ng[1];
                        q.addLast(new int[]{ ng[0], costs[ng[0]]});
                    }
                }
                sz -= 1;
            }
            stops += 1;
        }

        return costs[dst] == Integer.MAX_VALUE ? -1: costs[dst];
    }

    /*
        Plain BFs, gave MLE.
     */
    private int pass3(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = getGraph(n, flights);

        return bfs(graph, src, dst, k);

        // return -1;
    }


    private int bfs(List<List<int[]>> graph, int src, int dst, int k) {
        Deque<int[]> q = new ArrayDeque<>();

        int minCost = Integer.MAX_VALUE;
        q.addLast(new int[] {src, 0, 0});
        while (!q.isEmpty()) {
            int[] curr = q.removeFirst();

            if (curr[0] == dst) {
                minCost = Math.min(minCost, curr[1]);
                continue;
            }

            if (curr[2] > k) continue;
            List<int[]> ngs = graph.get(curr[0]);
            for (int[] ng: ngs) {
                if (curr[2] +1 <= k) {
                    q.add(new int[] {ng[0], curr[1]+ng[1], curr[2]+1});

                }
            }
        }

        System.out.println(minCost);

        return minCost;
    }


    /*
        Plain DFS gives TLE.
    */
    private int pass1(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> graph = getGraph(n, flights);

        int[] visited = new int[n];
        int ans = (int)dfs(graph, visited, n, src, dst, k);
        return ans == Integer.MAX_VALUE ? -1: ans;
    }

    private long dfs(List<List<int[]>> graph, int[] visited, int n, int curr, int dst, int k) {
        if (curr == dst) return 0l;

        if (k < 0) return Integer.MAX_VALUE;
        if (visited[curr] != 0) return Integer.MAX_VALUE;


        visited[curr] = -1;
        List<int[]> ngs = graph.get(curr);

        long cost = Integer.MAX_VALUE;
        for (int[] ng: ngs) {
            long cst = ng[1] + dfs(graph, visited, n, ng[0], dst, k-1);
            cost = Math.min(cost, cst);
        }
        visited[curr] = 0;

        return cost;
    }

    private List<List<int[]>> getGraph(int n, int[][] flights) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight: flights) {
            int src = flight[0];
            int dst = flight[1];
            int wt = flight[2];

            graph.get(src).add(new int[]{dst, wt});
        }

        return graph;
    }
}
