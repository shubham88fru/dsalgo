package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/find-eventual-safe-states/
//@check - https://www.youtube.com/watch?v=k8LBJqGLLQE&t=0s&ab_channel=codestorywithMIK
public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        /*
            Not sure why it works, but the idea to solve
            the problem is that all nodes that are not
            part of a cycle, will be safe nodes.
            Atleast this is what I could figure out from
            mik's explanation. If this is a commonly asked
            problem for some company, maybe other video
            explanation for this problem as well.

            So, assuming the above to be true, the problem
            basically boils down to applying the already
            studied algorithms of finding cycles in directed
            graphs and returning the nodes that are part
            of the cycle.
        */
        return mikdfs(graph);
    }

    /*
    * Following is the DFS solution to this problem.
    * Mik also showed how to do this using BFS (kahn's algo)
    * but it was very unintuitive, so I didn't try it.
    * If this problem is a commonly asked problem for
    * some company, then @check.
    *
    * NOTE: Note how both the cycle detection algorithms
    * also let us get the exact nodes that are part of the
    * cycle.
    * */
    private List<Integer> mikdfs(int[][] graph) {
        int n = graph.length;

        int[] visited = new int[n];
        int[] recursion = new int[n];

        for (int i=0; i<n; i++) {
            if (visited[i] != -1) {
                detectCycleDFS(graph, visited, recursion, i);
            }
        }

        List<Integer> ans = new ArrayList<>();

        //Extra part specific to question.
        for (int i=0; i<n; i++) {
            //All the nodes the that are part of the cycle
            //will have their recursion flag unmarked. And so,
            //we ignore them and choose the normal nodes.
            if (recursion[i] == 0) ans.add(i);
        }

        return ans;
    }

    /*
        Algorithm to detect cycle in a directed graph
        using DFS.
     */
    private boolean detectCycleDFS(int[][] graph, int[] visited, int[] recursion, int i) {

        visited[i] = -1;
        recursion[i] = -1;

        int[] neighbours = graph[i];
        for (int neighbour: neighbours) {
            if (visited[neighbour] != -1 && detectCycleDFS(graph, visited, recursion, neighbour)) {
                return true;
            } else if (recursion[neighbour] == -1) return true;
        }

        recursion[i] = 0;
        return false;
    }
}
