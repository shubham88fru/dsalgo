package ptrn.customds;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//@link - https://leetcode.com/problems/snapshot-array/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4785411919708160

/**
 * My and edctv soln. Gives Memory limit exceeded for some cases.
 * There are just so many ways to do this problem.
 * The optimal seems to use map of index v/s treemap of snap id v/s value.
 */

/**
 * Optimal
 * Took hint from lc's editorial.
 * Check editorial for TC and SC.
 * TC: O(nlog(n) + k); where n is number of max num of calls, and k is length of array.
 * SC: O(n + k)
 */
class SnapshotArray2 {
    int sid = 0;
    Map<Integer, TreeMap<Integer, Integer>> snapshots = new HashMap<>();

    public SnapshotArray2(int length) {
        for (int i=0; i<length; i++) {
            snapshots.put(i, new TreeMap<>());
            snapshots.get(i).put(0, 0); //default value of array is zero.
        }
    }

    public void set(int index, int val) {
        snapshots.get(index).put(sid, val);
    }

    public int snap() {
        return sid++;
    }

    public int get(int index, int snap_id) {
        /**
         Binary search on the treemap to get the
         recent snap.
         Note get can't simply look for snap_id because
         there might a case when lets say sid was 3, then
         a snap is called, making sid 4. Now get is called
         with snap_id=4. This is basically asking for
         sid 3. So we just get the closest sid to 4 which is
         3.
         */
        return snapshots.get(index).floorEntry(snap_id).getValue();
    }
}

/**
 * Suboptimal
 */
class SnapshotArray {
    int len;

    /*
        Snapid v/s map of index and values.
        Note, this is better than storing entire
        arrays on each snap.
     */
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
