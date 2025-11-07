package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/power-grid-maintenance/description/?
//@check - https://www.youtube.com/watch?v=ylJ2lDHUBZA&t=1117s
public class PowerGridMaintenance {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        return pass1(c, connections, queries);
    }

    /**
         This was my intuition too,
         but this code is based on mik's
         explanation.

         - Brute force is to do dfs/bfs for each query.
         - The one below is okayish approach.
         - Optimal is using DSU and heap.
     */
    private int[] pass1(int c, int[][] connections, int[][] queries) {
        List<List<Integer>> graph = getGraph(c, connections);

        int group = 1;
        int[] groups = new int[c+1];
        Map<Integer, TreeSet<Integer>> mp = new HashMap<>();
        for (int i=1; i<=c; i++) {
            if (groups[i] == 0) {
                Set<Integer> visited = new HashSet<>();
                mp.put(group, new TreeSet<>());
                dfs(graph, i, groups, group, mp, visited);
                group += 1;
            }
        }

        int i = 0;
        List<Integer> ans = new ArrayList<>();

        Set<Integer> offline = new HashSet<>();
        for (int[] query: queries) {
            int action = query[0];
            int station = query[1];

            int grp = groups[station];
            if (action == 2) {
                offline.add(station);
                mp.get(grp).remove(station);
            } else {
                if (!offline.contains(station)) ans.add(station);
                else {
                    TreeSet<Integer> stations = mp.get(grp);
                    if (stations.size() == 0) ans.add(-1);
                    else ans.add(stations.first());
                }
            }
        }

        return ans.stream().mapToInt(ii->ii).toArray();

    }

    private void dfs(List<List<Integer>> graph, int i, int[] groups, int group, Map<Integer, TreeSet<Integer>> mp, Set<Integer> visited) {

        groups[i] = group;
        mp.get(group).add(i);
        visited.add(i);

        List<Integer> children = graph.get(i);
        for (int child: children) {
            if (!visited.contains(child)) {
                dfs(graph, child, groups, group, mp, visited);
            }
        }
    }

    private List<List<Integer>> getGraph(int c, int[][] connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i=0; i<=c; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection: connections) {
            int u = connection[0];
            int v = connection[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
