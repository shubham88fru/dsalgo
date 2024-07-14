package ptrn.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@link - https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6285580901810176
//@strvr - https://www.youtube.com/watch?v=OwMNX8SPavM&ab_channel=takeUforward
/**
 *
 * Below soln is direct copy-pasta from edctv. Barely understood.
 * Check strvr's DSU series and some problems maybe to understand the
 * DSU topic better.
 *
 */

/**
 * To recap, the solution to this problem can be divided into the following three parts:
     1. Iterate over all the stones, and use the union() function to check if they share
 *      a row or column with other stones. Merge the stones into one group if they share a row or column.

 *   2. Use the find() function to get the number of groups to which the stones belong.

 *   3. Return the difference between the number of stones and the number of groups made.
 */
public class MostStonesRemovedWithSameRowOrColumn {
    public int removeStones(int[][] stones) {
        int offset = 100000;
        UnionFind2 stone = new UnionFind2();

        for (int[] s : stones) {
            int x = s[0];
            int y = s[1];
            stone.union(x, y + offset);
        }

        Set<Integer> groups = new HashSet<>();
        Map<Integer, Integer> parents = stone.getParents();
        for (Map.Entry<Integer, Integer> entry : parents.entrySet()) {
            groups.add(stone.find(entry.getKey()));
        }

        return stones.length - groups.size();
    }
}

class UnionFind2 {
    private Map<Integer, Integer> parents;
    private Map<Integer, Integer> ranks;

    public UnionFind2() {
        parents = new HashMap<>();
        ranks = new HashMap<>();
    }

    // Function to find which group a particular element belongs to.
    public int find(int coordinate) {
        if (coordinate != parents.get(coordinate)) {
            parents.put(coordinate, find(parents.get(coordinate)));
        }
        return parents.get(coordinate);
    }

    // Function to join two coordinates into a single one.
    public void union(int x, int y) {
        // Set the parent of each coordinate to itself
        // if not already present in the map
        parents.putIfAbsent(x, x);
        parents.putIfAbsent(y, y);

        // Set the ranks of each coordinate to 0
        // if not already present in the map
        ranks.putIfAbsent(x, 0);
        ranks.putIfAbsent(y, 0);

        // Compare the ranks of the two coordinates
        // to decide which should be the parent
        if (ranks.get(x) > ranks.get(y)) {
            parents.put(find(y), find(x));
        } else if (ranks.get(y) > ranks.get(x)) {
            parents.put(find(x), find(y));
        } else {
            // If the ranks are equal, choose one coordinate
            // as the parent and increment its rank
            parents.put(find(x), find(y));
            ranks.put(y, ranks.get(y) + 1);
        }
    }

    // Getter function to access the parents member
    public Map<Integer, Integer> getParents() {
        return parents;
    }
}
