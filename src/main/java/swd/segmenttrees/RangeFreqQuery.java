package swd.segmenttrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RangeFreqQuery {
    //Segment tree is represented as a list
    //of map for this question unlike an array
    //for other segment tree question.

    List<Map<Integer, Integer>> segmentTree;
    int size;
    int[] arr;

    //Initialization
    public RangeFreqQuery(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
        this.segmentTree = new ArrayList<>();

        //each index in segment tree is initialized
        //with an emtpy map.
        for (int i=0; i<4*this.size; i++) {
            this.segmentTree.add(new HashMap<>());
        }

        //construct the segment tree.
        constructSegmentTree(0, this.size-1, 0);
    }

    private void constructSegmentTree(int arrStart, int arrEnd, int segTreeIndex) {

        //Each index of segment tree will
        //be a map consisting of no. of frequencies
        //of all the elements in the range [arrStart, arrEnd]
        //on a leaf node, we're encoutering a new element, so its freq is one.
        if (arrStart == arrEnd) {
            this.segmentTree.get(segTreeIndex).put(this.arr[arrStart], 1);
            return;
        }

        int mid = (arrStart + (arrEnd-arrStart)/2);
        int leftChildIndex = 2*segTreeIndex + 1;
        int rightChildIndex = 2*segTreeIndex + 2;

        constructSegmentTree(arrStart, mid, leftChildIndex);
        constructSegmentTree(mid+1, arrEnd, rightChildIndex);

        //merge the left and right maps into one map.
        //can be done more efficiently using Stream api probably.
        //eg: left -> [13=1, 23=3, 0=1]
        //right -> [1=2, 13=2, 2=1]
        //merged -> [13=3, 23=2, 0=1, 1=2, 2=1]
        this.segmentTree.set(segTreeIndex, mergeMaps(
                this.segmentTree.get(leftChildIndex),
                this.segmentTree.get(rightChildIndex)
        ));

    }

    //Merges two given maps, by adding the frequencies if same key appears in both maps.
    private Map<Integer, Integer> mergeMaps(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {

        Map<Integer, Integer> map3 = new HashMap<>(map1);

        for (Map.Entry<Integer, Integer> entry: map2.entrySet()) {
            map3.put(entry.getKey(),
                    map3.getOrDefault(entry.getKey(), 0) + entry.getValue()
            );
        }

        return map3;
    }

    public int query(int left, int right, int value) {
        return queryForRange(0, this.size-1, left, right, 0, value);
    }

    private int queryForRange(int arrStart, int arrEnd, int rangeStart, int rangeEnd, int segTreeIndex, int value) {

        //no overlap - doesn't contribute so return 0
        if (arrEnd < rangeStart || arrStart > rangeEnd) {
            return 0;
        }

        //full overlap - so return the frequency at segTreeIndex.
        if (arrStart >= rangeStart && arrEnd <= rangeEnd) {
            return this.segmentTree.get(segTreeIndex).getOrDefault(value, 0);
        }

        //partial overlap
        int mid = (arrStart + (arrEnd - arrStart)/2);
        int leftChildIndex = 2*segTreeIndex + 1;
        int rightChildIndex = 2*segTreeIndex + 2;

        int leftQuery = queryForRange(arrStart, mid, rangeStart, rangeEnd, leftChildIndex, value);
        int rightQuery = queryForRange(mid+1, arrEnd, rangeStart, rangeEnd, rightChildIndex, value);

        //add frequency found in left and right.
        return (leftQuery + rightQuery);
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
