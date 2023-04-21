package swd.graphs;

import java.util.*;

//@Link - https://leetcode.com/problems/min-cost-to-connect-all-points/description/
public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        Map<String, List<PointWrapper>> adj = getNeighboursWithCost(points);

        return primsAlgoForMinSpanTree(points[0], adj);
    }

    //Retruns the graph in form of a map, where each key in the map
    //`xi_yi` represents a point on the cartesian plane.
    //And the value is a list of objects containing their coordinates and
    //cost from the source i.e. `xi_yi`
    private Map<String, List<PointWrapper>> getNeighboursWithCost(int[][] points) {
        Map<String, List<PointWrapper>> neighboursWithCost = new HashMap<>();

        //Considering all points as source and all as destination
        for (int i=0; i<points.length; i++) {
            int[] src = points[i];
            int xi = src[0];
            int yi = src[1];

            String key = xi + "_" + yi;
            ArrayList<PointWrapper> neighbourWithCost = new ArrayList<>();
            neighboursWithCost.put(key, neighbourWithCost);
            for (int j=0; j<points.length; j++) {
                if (i==j) continue;
                int[] dest = points[j];
                int xj = dest[0];
                int yj = dest[1];
                int cost = Math.abs(xi-xj) + Math.abs(yi-yj);

                neighboursWithCost
                        .getOrDefault(key, new ArrayList<>())
                        .add(new PointWrapper(xj, yj, cost));
            }
        }

        return neighboursWithCost;
    }

    private int primsAlgoForMinSpanTree(int[] start, Map<String, List<PointWrapper>> adj) {
        //After completion of prims algo, costs array will
        //consist of min costs of reaching each vertex from its
        //previous node in the min spanning tree.
        Map<String, Integer> costs = new HashMap<>();
        int requiredEdges = adj.size();

        //Priority queue (instead of a usual queue) is used in implementing
        //prims. Here, the priority queue will sort the added values in
        //natural order of the cost.
        PriorityQueue<VertexCost2> pq
                = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new VertexCost2(start[0]+"_"+start[1], 0)); //cost of start index to itself is zero.

        int sum = 0;

        //Since we have considered every point in the points array as a source
        //vertex and every point as dest vertex as well, we have too many edges,
        //but since we need to connect only `n` points, as soon we have `n-1` edges
        //we can stop.
        while (!pq.isEmpty() && requiredEdges > 0) {
            VertexCost2 vertexAndCost = pq.remove();
            String currVertex = vertexAndCost.vertex;
            int currCost = vertexAndCost.cost;

            //Means already visited, hence just skip it.
            if (costs.containsKey(currVertex)) continue;

            costs.put(currVertex,  currCost);
            sum += currCost;
            List<PointWrapper> neighboursWithCost
                    = adj.get(currVertex);

            for (PointWrapper neighbourWithCost: neighboursWithCost) {
                //IMP: This is basically the only diff between dijkstra and
                //prims. In dijkstra, we add current cost and current edge cost
                //but in prims we only consider the current edge cost because we
                //dont bother about the cost from the source. All we need it the
                //cost of the edge (i.e. from the previous vertex)
                pq.add(new VertexCost2(neighbourWithCost.key, (neighbourWithCost.cost)));
            }

            requiredEdges -= 1;
        }

        return sum;
    }
}

class PointWrapper {
    int x;
    int y;
    int cost;
    String key;

    public PointWrapper(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.key = this.x+"_"+this.y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "," + cost + "]";
    }
}

class VertexCost2 {
    String vertex;
    int cost;

    public VertexCost2(String vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }
}