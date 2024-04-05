package ptrn.graph;

import java.util.*;

//@link - https://leetcode.com/problems/bus-routes/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6672338780946432
//@tag - TO_REVISIT
public class BusRoutes {

    //blind copy paste from edctv.
    //No idea how and why this soln works.
    public int numBusesToDestination(int[][] routes, int src, int dest) {
        // Create adjacency list for graph.
        Map<Integer, List<Integer>> adjList = new HashMap< >();
        for (int i = 0; i < routes.length; i++) {
            for (int station: routes[i]) {
                if (!adjList.containsKey(station)) {
                    adjList.put(station, new ArrayList< >());
                }
                adjList.get(station).add(i);
            }
        }

        // Create a queue with initial source and bus count of 0.
        Deque< int[] > queue = new ArrayDeque < > ();
        queue.add(new int[] {src, 0});

        // Create a set to contain visited routes of bus.
        Set < Integer > visitedBuses = new HashSet < > ();

        // Iterate till queue is empty.
        while (!queue.isEmpty()) {
            // Pop station and and number of buses taken and store in variables.
            int[] current = queue.peek();
            int station = current[0];
            int busesTaken = current[1];
            queue.poll();


            // If we have reached the destination station, return number of buses taken.
            if (station == dest) {
                return busesTaken;
            }

            // If station is in graph, then iterate over the stations in graph
            // and if it is not already visited, enqueue all the stations in that bus
            // route along with an incremented bus count and mark the bus visited.
            if (adjList.containsKey(station)) {
                for (int bus: adjList.get(station)) {
                    if (!visitedBuses.contains(bus)) {
                        for (int s: routes[bus]) {
                            queue.add(new int[] {
                                    s,
                                    busesTaken + 1
                            });
                        }
                        visitedBuses.add(bus);
                    }
                }
            }
        }

        // If the route is not found, return -1.
        return -1;
    }
}
