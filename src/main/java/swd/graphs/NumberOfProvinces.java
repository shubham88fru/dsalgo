package swd.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/number-of-provinces/description/
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        List<Integer> visited = new ArrayList<>();

        int n = isConnected.length;
        int disconnectedComponents = 0;

        //For each city (i.e. vertex in the graph), traverse dfs
        //and populated the visited array. This way, we'll know all
        //the cities connected to curr city. When one dfs returns, we have
        //one connected component.
        for (int city = 0; city < n; city += 1) {
            //If a vertex has already been visited in some previous
            //dfs, we should visit again, because this city would be
            //a part of a component that we have already accounted for.
            if (!visited.contains(city)) {
                //traverseDFSFromGivenVertex(isConnected, city, visited);
                traverseBFSFromGivenVertex(isConnected, city, visited);

                //Everytime we do a new dfs, means we're a new components, therefore
                //increment the count of disconnected component (provinces)
                disconnectedComponents += 1;
            }
        }

        //no. of provices is equal to no. of disconnected components
        //in the graph.
        return disconnectedComponents;
    }

    //1) DFS traversal of graph.
    private void traverseDFSFromGivenVertex(int[][] graph, int currVertex, List<Integer> visited) {
        if (visited.contains(currVertex)) return;

        visited.add(currVertex);
        int[] connections = graph[currVertex];
        for (int i = 0; i < graph.length; i += 1) {
            //In the given adjacency matrix, a city is connected to
            //curr city, only if value at the corresponding index is 1.
            //So, from curr city we can visit another only if the value is 1.
            int connected = connections[i];
            if (connected == 1) {
                traverseDFSFromGivenVertex(graph, i, visited);
            }
        }
    }

    //2) BFS traversal of graph.
    private void traverseBFSFromGivenVertex(int[][] graph, int currVertex, List<Integer> visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(currVertex);
        visited.add(currVertex);

        while (!queue.isEmpty()) {
            int curr = queue.removeFirst();
            int[] connections = graph[curr];
            for (int i = 0; i < graph.length; i += 1) {
                int connected = connections[i];
                if (connected == 1 && !visited.contains(i)) {
                    queue.addLast(i);
                    visited.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces numberOfProvinces = new NumberOfProvinces();
        int[][] isConnected = {{1, 0, 0},{0, 1, 0},{0, 0, 1}};
        numberOfProvinces.findCircleNum(isConnected);
    }
}
