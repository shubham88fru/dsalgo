package ptrn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/loud-and-rich/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4844392155447296
//        https://www.youtube.com/watch?v=MG00PdP5DcQ&ab_channel=LetsCode
public class LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> graph = getGraph(richer, quiet.length);
        return sol(richer, quiet, graph);
    }

    private int[] sol(int[][] richer, int[] quiet, List<List<Integer>> graph) {
        int n = quiet.length;
        int[] indeg = new int[n];

        for (int[] data: richer) {
            int u = data[0];
            int v = data[1];

            indeg[v] += 1;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<indeg.length; i++) {
            if (indeg[i] == 0) q.addLast(i);
        }

        /**
         * For this problem, I was able to correctly identify
         * it as a graph based problem requiring topo sort.
         * However, I wasn't very sure what to do after finding
         * the topo sort. So the marked part below was kind
         * of tricky to me and copied from @check.
         *
         * There a good dfs solutions also to this problem on YT.
         * checkout if this is a frequent for some company.
         */
        int[] ans = new int[n];
        for (int i=0; i<n; i++) ans[i] = i;
        while (!q.isEmpty()) {
            int item = q.removeFirst();

            List<Integer> children = graph.get(item);
            for (int child: children) {
                ///////////////////////////////////////////
                if (quiet[ans[item]] < quiet[ans[child]]) {
                    ans[child] = ans[item];
                }
                ///////////////////////////////////////////

                indeg[child] -= 1;
                if (indeg[child] == 0) {
                    q.addLast(child);
                }
            }
        }

        return ans;
    }

    private List<List<Integer>> getGraph(int[][] richer, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] data: richer) {
            int u = data[0];
            int v = data[1];

            graph.get(u).add(v);
        }

        return graph;
    }
}
