package ptrn.toposort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * ----------------
 * Topological Sort
 * ----------------
 * Topological sort of a graph can be found using kahn's algorithm. Kahn's algo
 * iterates in a topological sort manner.
 * A topological sort of graph is a representation of vertices of the graph
 * in an array, where the array contains all the vertices of the graph and for
 * every edge (u,v) i.e. (u --> v) in the graph, `u` appears before `v` in the array.
 *
 * Note: Topological sort can't be determined for a graph that has cycles.
 * */

//@link - https://leetcode.com/problems/course-schedule/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6335917595033600
public class CourseScheduleI {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int m = prerequisites.length;

        //Construct graph (adjacency list representation) from the
        //prerequisites array.
        //NOTE: That usually for the problems to be solved using
        //the topo sort, an array of edges is given from which
        //we have to create the adjacency list.
        List<List<Integer>> adj = getGraph(numCourses, prerequisites);

        /*** Kahn's algorithm ***/
        //Step 1: Find indegree array
        int[] inDegrees = new int[numCourses];
        for (int vertex=0; vertex<numCourses; vertex++) {
            List<Integer> neighbours = adj.get(vertex);
            for (int neighbour: neighbours) {
                inDegrees[neighbour] += 1;
            }
        }

        //Step 2: Add all vertices with 0 indegree to queue.
        Deque<Integer> queue = new ArrayDeque<>();
        for (int vertex=0; vertex<numCourses; vertex++) {
            if (inDegrees[vertex] == 0) queue.addLast(vertex);
        }

        //Step 3: Run bfs. If after bfs, we're not able to
        //visit all vertices, then we have a cycle in the graph.
        //If graph has cycles, means topological sort not possible.
        //i.e. wrt question, not possible to schedule courses.
        boolean[] visited = new boolean[numCourses];
        int totalVisitedVertices = bfsKahn(adj, queue, inDegrees, visited);
        if (totalVisitedVertices != numCourses) {
            return false;
        }

        //else if cycle not detected in graph means,
        //topological sort is certainly possible i.e.
        //in context of this question, scheduling the
        //course is possible.
        return true;
    }

    //BFS for kahn's algorithm
    private int bfsKahn(List<List<Integer>> adj, Deque<Integer> queue, int[] inDegrees, boolean[] visited) {
        int visitedVertices = 0;

        while (!queue.isEmpty()) {
            int curr = queue.removeFirst();
            if (visited[curr]) continue;

            visited[curr] = true;
            visitedVertices += 1;

            List<Integer> neighbours = adj.get(curr);
            for (int neighbour: neighbours) {

                //Decrement the indegree of each neighbour,
                //and if indegree becomes 0, add to queue.
                inDegrees[neighbour] -= 1;
                if (inDegrees[neighbour] == 0) {
                    queue.addLast(neighbour);
                }
            }
        }

        return visitedVertices;
    }

    private List<List<Integer>> getGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: prerequisites) {
            int course = edge[0]; //v
            int prereq = edge[1]; //u
            graph.get(prereq).add(course); //[u --> v]
        }

        return graph;
    }
}
