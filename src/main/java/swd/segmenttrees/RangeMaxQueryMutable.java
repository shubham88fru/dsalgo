package swd.segmenttrees;

import java.util.Arrays;

//@link - Not present anywhere
public class RangeMaxQueryMutable {
    private final int[] nums;
    private final int[] segmentTree;
    private final int size;

    private static final int MIN_VALUE = Integer.MIN_VALUE;
    public RangeMaxQueryMutable(int[] nums) {
        this.nums = nums;
        this.size = nums.length;

        this.segmentTree = new int[this.size*4];
        Arrays.fill(this.segmentTree, MIN_VALUE);

        constructSegmentTree(0, this.size-1, 0);
    }

    private void constructSegmentTree(int arrStart, int arrEnd, int segTreeIndex) {

        if (arrStart > arrEnd) return;

        if (arrStart == arrEnd) {
            this.segmentTree[segTreeIndex] = this.nums[arrStart];
            return;
        }

        int mid = arrStart + (arrEnd-arrStart)/2;
        int leftChildIndex = (2*segTreeIndex) + 1;
        int rightChildIndex = (2*segTreeIndex) + 2;
        constructSegmentTree(arrStart, mid, leftChildIndex);
        constructSegmentTree(mid+1, arrEnd, rightChildIndex);

        this.segmentTree[segTreeIndex]
                = Math.max(this.segmentTree[leftChildIndex], this.segmentTree[rightChildIndex]);

    }

    private int maxQuery(int left, int right) {
        return getMaxInRange(left, right, 0, this.size-1, 0);
    }

    private int getMaxInRange(int rangeLeft, int rangeRight, int arrStart, int arrEnd, int segTreeIndex) {

        //no overlap
        if (arrStart > rangeRight || arrEnd < rangeLeft) return MIN_VALUE;

        //complete overlap
        if (arrStart >= rangeLeft && arrEnd <= rangeRight) {
            return this.segmentTree[segTreeIndex];
        }

        //partial overlap
        int mid = arrStart + (arrEnd - arrStart) / 2;
        int leftChildIndex = (2*segTreeIndex) + 1;
        int rightChildIndex = (2*segTreeIndex) + 2;

        int maxInLeft = getMaxInRange(rangeLeft, rangeRight, arrStart, mid, leftChildIndex);
        int maxInRight = getMaxInRange(rangeLeft, rangeRight, mid+1, arrEnd, rightChildIndex);

        return Math.max(maxInLeft, maxInRight);
    }

    private void update(int index, int val) {
        this.nums[index] = val;
        updateSegmentTree(0, this.size-1, index, 0);
    }

    private void updateSegmentTree(int arrStart, int arrEnd, int index, int segTreeIndex) {

        if (arrStart > arrEnd) return;

        if(arrStart == arrEnd) {
            this.segmentTree[segTreeIndex] = this.nums[arrStart];
            return;
        }

        int mid = arrStart + (arrEnd - arrStart) / 2;
        int leftChildIndex = (2*segTreeIndex) + 1;
        int rightChildIndex = (2*segTreeIndex) + 2;

        if (index <= mid) {
            updateSegmentTree(arrStart, mid, index, leftChildIndex);
        } else {
            updateSegmentTree(mid+1, arrEnd, index, rightChildIndex);
        }

        this.segmentTree[segTreeIndex] =
                Math.max(this.segmentTree[leftChildIndex], this.segmentTree[rightChildIndex]);
    }
}
