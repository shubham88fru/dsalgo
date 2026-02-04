package ptrn.customds;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/snapshot-array/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4785411919708160

/**
 * My and edctv soln. Gives Memory limit exceeded for some cases.
 * So did slight optimization as per - https://leetcode.com/problems/snapshot-array/solutions/3624495/simple-java-solution-using-hashmap
 */
class SnapshotArray {
    int len;
    Map<Integer, Map<Integer, Integer>> snapshots = new HashMap<>();
    int snapId;

    public SnapshotArray(int length) {
        snapshots.put(0, new HashMap<>());
        len = length;
        this.snapId = 0;
    }

    public void set(int index, int val) {
        snapshots.get(snapId).put(index, val);
    }

    public int snap() {
        //snapshots.put(snapid + 1, new HashMap<Integer, Integer>(snapshots.get(snapId)));

        /**
         * Optimization.
         * Put empty map, don't copy the previous values to the next snapshot.
         */
        snapshots.put(snapId + 1, new HashMap<>());
        return snapId++;
    }

    public int get(int index, int snap_id) {
        /**
         * Optimization.
         * When searching for a value in a snapshot,
         * start with that snapshot, if the index is not
         * found in current snapshot, continue searching in
         * the previous snapshots untill its found.
         */
        for (int snap=snap_id; snap>=0; snap--) {
            if (snapshots.get(snap).containsKey(index))
                return snapshots.get(snap).get(index);
        }

        return 0;
    }
}
