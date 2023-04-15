package swd.graphs;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
public class CityWithSmallestNumOfNeighboursAtThresholdDist {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] costs = getCosts(edges, n);

        //Floyd warshall algo to get the min cost
        //of visiting each node from every other node.
        for (int k=0; k<n; k++) { //for every intermdiate node.
            for (int i=0; i<n; i++) { //for every source node.
                for (int j=0; j<n; j++) {//for every destination node.
                    if (costs[i][k] != Integer.MAX_VALUE && costs[k][j] != Integer.MAX_VALUE) {
                        costs[i][j] = Math.min(costs[i][j],
                                costs[i][k] + costs[k][j]
                        );
                    }
                }
            }
        }

        //Find the city with highest no. and least
        //no. of cities within threshold distance.
        List<List<Integer>> connectedCities = new ArrayList<>();
        for (int i=0; i<n; i++) {
            connectedCities.add(new ArrayList<>());
        }

        int minCitiesIndex = Integer.MAX_VALUE;
        int minSize = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                //i and j are not same city,
                //are connected but with a distance
                //lesser than threshold.
                if (i != j && costs[i][j] != Integer.MAX_VALUE && costs[i][j] <= distanceThreshold) {
                    connectedCities.get(i).add(j);
                }
            }
            int currSize = connectedCities.get(i).size();
            //if curr city has lesser no. of under threshold
            //distance connected cities, update the answer.
            if (currSize <= minSize) {
                minSize = currSize;
                minCitiesIndex = i;
            }
        }

        return minCitiesIndex;
    }

    //Costs array.
    //Gives cost of visiting every other node from each node.
    private int[][] getCosts(int[][] edges, int n) {
        int[][] costs = new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) costs[i][j] = 0;
                else costs[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            costs[u][v] = cost;
            costs[v][u] = cost;
        }

        return costs;
    }
}
