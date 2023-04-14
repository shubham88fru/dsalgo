package swd.graphs;

/*
    Floyd Warshall algorithm (to find shortest path when weights can be -ve (works for +ve weights also))
    -----------------------------------------------------------------------------------------------------
    --> Intermediary (k), source (i) and destination (j).
    --> The algorithm finds the shortest distance from each vertex to each vertex
        considering each vertex as an intermediary vertex.
    --> Finds shortest distance between every pair of vertices in a graph.
    --> Input to the algorithm must be a graph represented by adjacency matrix
        where each cell represents the weight/cost of an edge.
 */
//@link - https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
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

        //************Flyod Warshall algorithm**********
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
        //************Flyod Warshall algorithm**********

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
