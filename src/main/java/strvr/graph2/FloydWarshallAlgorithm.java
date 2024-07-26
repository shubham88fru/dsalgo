package strvr.graph2;

/*
    Floyd Warshall algorithm (to find the shortest path when weights can be -ve (works for +ve weights also))
    -----------------------------------------------------------------------------------------------------
    --> Intermediary (k), source (i) and destination (j).
    --> Can be used to detect negative cycles as well.
            We can achieve this by running the floyd warshall on the graph.
            Note that when initializing the costs, we say that the cost of reaching
            a node from itself is 0. So, if there is a -ve cycle in the graph, after running
            the floyd warshall algorithm, the cost of reaching a vertex (in the -ve cycle) from itself
            will be -ve (because it is lesser than 0 it will be updated during floyd warshall algo). When
            that happens we can detect the cycle. Therefore, at end of floyd warshall, we'll simply iterate
            over the costs matrix once more and see if for any node cost of i to i is -ve, if so, then we would
            have detected a -ve cycle.
    --> Works for undirected graphs as well. If we have to apply
        Floyd Warshall on an undirected graph, all we need to do is sort of convert
        the undirected graph to a directed graph where edge a<--->b with weight W will
        become two edges a-->b and a<--b each with weight W.
    --> The algorithm finds the shortest distance from each vertex to each vertex
        considering each vertex as an intermediary vertex. --> hence this algo is also called a multi source shortest path algorithm.
    --> Finds shortest distance between every pair of vertices in a graph.
    --> Input to the algorithm must be a graph represented by adjacency matrix
        where each cell represents the weight/cost of an edge.
    --> Floyd warshall algo is a dynamic programming algo.
    --> Time complexity of floyd warshall is N^3 which is even worse than dijkstra.
 */
//@link - https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
//@strvr - https://takeuforward.org/data-structure/floyd-warshall-algorithm-g-42/
public class FloydWarshallAlgorithm {
    public void shortest_distance(int[][] matrix) {

        int n = matrix.length;
        //-1 indicates no edge/path.
        //Replace -1 with max value
        //so that it doesn't cause issues
        //when we do Math.min.
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        //************Floyd Warshall algorithm**********
        //intermediate - k
        //source - i
        //destination - j
        for (int intermediate = 0; intermediate < n; intermediate++) {
            for (int source = 0; source < n; source++) {
                for (int dest = 0; dest < n; dest++) {
                    if (matrix[source][intermediate] != Integer.MAX_VALUE && matrix[intermediate][dest] != Integer.MAX_VALUE) {
                        matrix[source][dest] = Math.min(
                                matrix[source][dest],
                                matrix[source][intermediate] + matrix[intermediate][dest]
                        );
                    }
                }
            }
        }
        //************Floyd Warshall algorithm**********

        //Switch back from Integer.MAX_VALUE
        //to -1.
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE) {
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
