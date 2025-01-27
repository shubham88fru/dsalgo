package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/course-schedule-iv/description/
//@check - https://www.youtube.com/watch?v=ScJNPlYz1J4&ab_channel=codestorywithMIK
public class CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // return subOptimal(numCourses, prerequisites, queries);
        return optimal(numCourses, prerequisites, queries);

    }

    //1) Using topo sort. This approach came to my mind initally but
    //couldn't go ahead because I wasn't sure how topo sorted list
    //will give me answer for queries involving two un-related nodes.
    //The trick is, when running toposort, for each node to be visited,
    //record the node where we are comming from. This is kinda unintuitive
    //but works.
    //Following is coded based on mik's explanation.
    private List<Boolean> optimal(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> graph = getGraph(numCourses, prerequisites);
        int[] indegrees = new int[numCourses];
        for (int[] prereq: prerequisites) {
            int u = prereq[0];
            int v = prereq[1];

            indegrees[v] += 1;
        }

        //for each node stores the its prereq and transitive prereqs.
        Map<Integer, Set<Integer>> prereqs = new HashMap<>();

        Deque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<numCourses; i++) {
            if (indegrees[i] == 0) q.addLast(i);
        }

        int[] visited = new int[numCourses];
        while (!q.isEmpty()) {
            int node = q.removeFirst();
            visited[node] = -1;

            List<Integer> neighbours = graph.get(node);
            for (int neighbour: neighbours) {

                if (visited[neighbour] != -1) {
                    //store all the prereq and transitive prereqs.
                    if (!prereqs.containsKey(neighbour)) prereqs.put(neighbour, new HashSet<>());
                    prereqs.get(neighbour).add(node);
                    prereqs.get(neighbour).addAll(prereqs.getOrDefault(node, new HashSet<>()));

                    indegrees[neighbour] -= 1;
                    if (indegrees[neighbour] == 0) {
                        q.addLast(neighbour);
                    }
                }

            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query: queries) {
            int source = query[0];
            int dest = query[1];

            if (!prereqs.containsKey(dest)) ans.add(false);
            else if (prereqs.get(dest).contains(source)) ans.add(true);
            else ans.add(false);
        }

        return ans;
    }

    //2) Uses precomputing to store if we can reach a dest from a source.
    private List<Boolean> subOptimal(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> graph = getGraph(numCourses, prerequisites);
        int[][] canReach = new int[numCourses][numCourses];
        for (int i=0; i<numCourses; i++) {
            bfs(graph, i, canReach, numCourses);
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query: queries) {
            int source = query[0];
            int dest = query[1];

            if (canReach[source][dest] == -1) ans.add(true);
            else ans.add(false);
        }

        return ans;
    }


    private void bfs(List<List<Integer>> graph, int source, int[][] canReach, int numCourses) {
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(source);


        while (!q.isEmpty()) {
            int node = q.removeFirst();
            canReach[source][node] = -1;
            canReach[node][source] = -2;

            List<Integer> neighbours = graph.get(node);
            for (int neighbour: neighbours) {
                if (canReach[source][neighbour] != -1 && canReach[source][neighbour] != -2) {
                    q.addLast(neighbour);
                }
            }
        }
    }

    //3) Simple DFS also works for some reason.
    //For pair of query, run DFS from the source and check
    //if we can visit the dest, if yes, return immediately.
    //I think since we return the moement we find the dest, thats
    //why this approach works.

    private List<List<Integer>> getGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }

        return graph;
    }
}
