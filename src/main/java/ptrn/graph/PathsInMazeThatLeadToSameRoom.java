package ptrn.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/paths-in-maze-that-lead-to-same-room/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5505921771634688
//@tag - TO_REVISIT
public class PathsInMazeThatLeadToSameRoom {

    /**
     * Blind copy from edctv. Seems correct, but not sure why this solves the problem.
     * Check edctv.
     */
    public static int numberOfPaths(int n, int[][] corridors) {
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        int cycles = 0;

        for (int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];

            neighbors.putIfAbsent(room1, new HashSet<>());
            neighbors.putIfAbsent(room2, new HashSet<>());
            neighbors.get(room1).add(room2);
            neighbors.get(room2).add(room1);

            cycles += intersectionLength(neighbors.get(room1), neighbors.get(room2));
        }

        return cycles;
    }

    private static int intersectionLength(Set<Integer> set1, Set<Integer> set2) {
        int count = 0;
        for (int element : set1) {
            if (set2.contains(element)) {
                count++;
            }
        }
        return count;
    }
}
