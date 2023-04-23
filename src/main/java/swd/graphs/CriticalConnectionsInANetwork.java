package swd.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Critical Edge
    -------------
    --> A critical edge in a graph is an edge which if removed,
        will make the graph disconnected.
    --> Tarjan's algorithm can be used to detect critical edges in a graph.

    Tarjan's algorithm
    ------------------
    --> Finds critical edges in O(V+E) against the brute force approach
        of O(E*(V+E)).
    --> time variable, discovery time array, low time array.
        discovery time is the time when a particular vertex is visited in dfs,
        while low time is the least time in which a vertex could have been visited.
    --> basically a dfs algo.
    --> if neighbouring vertex is already discovered and is a direct parent
        of the curr vertex, then don't need to do anything. Simply return.
        otherwise, if neighbour already discovered but it not the parent of curr
        vertex then update low time of curr vertex to min of low time of curr vertex
        and discovery time of neighbouring vertex.
    --> on returning, update low time of curr vertex with min of low time of curr vertex
        and low time of neighbouring vertex. Then check if discovery time of curr vertex
        is less than low time of neighbour vertex. If so, then the edge between curr vertex
        and neighbouring vertex is a critical edge. otherwise, it isn't.

 */
//@link - https://leetcode.com/problems/critical-connections-in-a-network/description/
public class CriticalConnectionsInANetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = getGraph(n, connections);
        return findCriticalEdges(n, graph);
    }

    private List<List<Integer>> findCriticalEdges(int n, List<List<Integer>> graph) {
        int[] discTime = new int[n];
        int[] lowTime = new int[n];
        int[] time = {0};

        Arrays.fill(discTime, -1);
        Arrays.fill(lowTime, -1);

        List<List<Integer>> ans = new ArrayList<>();
        tarjansAlgo(0, time, discTime, lowTime, graph, -1, ans);

        return ans;
    }

    //Tarjan's algorithm
    private void tarjansAlgo(int currVertex, int[] time, int[] discTime, int[] lowTime, List<List<Integer>> graph, int currParent, List<List<Integer>> ans) {
        discTime[currVertex] = time[0];
        lowTime[currVertex] = time[0];
        time[0] += 1;

        //dfs
        for (int currNeighbour: graph.get(currVertex)) {

            //if current neighbour is parent, don't need to do anything.
            if (currNeighbour == currParent) continue;

            //else if not parent and not discovered
            if (discTime[currNeighbour] != -1) {
                lowTime[currVertex] = Math.min(lowTime[currVertex], discTime[currNeighbour]);
                continue;
            }

            //else if current neighbour not visited.
            tarjansAlgo(currNeighbour, time, discTime, lowTime, graph, currVertex, ans);

            //on returning, update low time of current vertex and check for critical edge.
            lowTime[currVertex] = Math.min(lowTime[currVertex], lowTime[currNeighbour]);

            //if true, means a critical edge.
            if (discTime[currVertex] < lowTime[currNeighbour]) {
                ans.add(Arrays.asList(currVertex, currNeighbour));
            }
        }
    }

    //return graph from the edges array.
    private List<List<Integer>> getGraph(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge: connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            //coz undirectional graph.
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }
}
