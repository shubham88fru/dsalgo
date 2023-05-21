package swd.segmenttrees;

import java.util.Arrays;

//@link - https://practice.geeksforgeeks.org/problems/range-minimum-query/1
public class RangeMinQuery {
    private static int[] nums;
    private static int[] segmentTree;
    private static int size;

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public static int[] constructST(int[] arr, int n) {
        nums = arr;
        size = arr.length;

        segmentTree = new int[size*4];
        Arrays.fill(segmentTree, MAX_VALUE);

        constructSegmentTree(0, size-1, 0);

        return segmentTree;
    }


    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int[] st, int n, int l, int r) {
        return minQuery(l, r);
    }


    private static void constructSegmentTree(int arrStart, int arrEnd, int segTreeIndex) {

        if (arrStart > arrEnd) return;

        if (arrStart == arrEnd) {
            segmentTree[segTreeIndex] = nums[arrStart];
            return;
        }

        int mid = arrStart + (arrEnd-arrStart)/2;
        int leftChildIndex = (2*segTreeIndex) + 1;
        int rightChildIndex = (2*segTreeIndex) + 2;
        constructSegmentTree(arrStart, mid, leftChildIndex);
        constructSegmentTree(mid+1, arrEnd, rightChildIndex);

        segmentTree[segTreeIndex]
                = Math.min(segmentTree[leftChildIndex], segmentTree[rightChildIndex]);

    }

    private static int minQuery(int left, int right) {
        return getMinInRange(left, right, 0, size-1, 0);
    }

    private static int getMinInRange(int rangeLeft, int rangeRight, int arrStart, int arrEnd, int segTreeIndex) {

        //no overlap
        if (arrStart > rangeRight || arrEnd < rangeLeft) return MAX_VALUE;

        //complete overlap
        if (arrStart >= rangeLeft && arrEnd <= rangeRight) {
            return segmentTree[segTreeIndex];
        }

        //partial overlap
        int mid = arrStart + (arrEnd - arrStart) / 2;
        int leftChildIndex = (2*segTreeIndex) + 1;
        int rightChildIndex = (2*segTreeIndex) + 2;

        int minInLeft = getMinInRange(rangeLeft, rangeRight, arrStart, mid, leftChildIndex);
        int minInRight = getMinInRange(rangeLeft, rangeRight, mid+1, arrEnd, rightChildIndex);

        return Math.min(minInLeft, minInRight);
    }
}
