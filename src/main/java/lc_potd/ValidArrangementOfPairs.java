package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/valid-arrangement-of-pairs/description/
//@check - https://www.youtube.com/watch?v=ElXqjhZoUC0&ab_channel=codestorywithMIK
public class ValidArrangementOfPairs {
    public int[][] validArrangement(int[][] pairs) {
        return eulerPath(pairs);
    }

    /*
        This question is based on Euler path concept
        and Hierholzer's algorithm. Mik has a playlist
        on euler path concept. Go through it if this
        topic is important for some company.

        Following is completely based on mik's explanation.
     */
    private int[][] eulerPath(int[][] pairs) {
        Map<Integer, List<Integer>> graph = getGraph(pairs);
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        for (int[] edge: pairs) {
            int u = edge[0];
            int v = edge[1];
            if (!outdegree.containsKey(u)) outdegree.put(u, 0);
            if (!outdegree.containsKey(v)) outdegree.put(v, 0);
            outdegree.put(u, outdegree.get(u)+1);

            if (!indegree.containsKey(v)) indegree.put(v, 0);
            if (!indegree.containsKey(u)) indegree.put(u, 0);
            indegree.put(v, indegree.get(v)+1);
        }

        //this is coz how euler path works.
        int startNode = pairs[0][0];
        for (int node: graph.keySet()) {
            if (outdegree.get(node) - indegree.get(node) == 1) {
                startNode = node;
                break;
            }
        }
        List<Integer> eulerPath = new ArrayList<>();

        //dfs using stack.
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(startNode);

        while (!stack.isEmpty()) {
            int node = stack.peekFirst();
            List<Integer> neighbours = graph.get(node);
            if (neighbours.size() > 0) {
                stack.addFirst(neighbours.get(neighbours.size()-1));
                neighbours.remove(neighbours.size()-1);
            } else {
                eulerPath.add(stack.removeFirst());
            }
        }

        Collections.reverse(eulerPath);
        List<int[]> result = new ArrayList<>();
        for (int i=0; i<eulerPath.size()-1; i++) {
            result.add(new int[] {eulerPath.get(i), eulerPath.get(i+1)});
        }

        return result.stream().toArray(int[][]::new);

    }

    private Map<Integer, List<Integer>> getGraph(int[][] pairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: pairs) {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u)) graph.put(u, new ArrayList<>());
            if (!graph.containsKey(v)) graph.put(v, new ArrayList<>());
            graph.get(u).add(v);
        }

        return graph;
    }
}
