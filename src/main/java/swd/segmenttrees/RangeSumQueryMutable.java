package swd.segmenttrees;

/*
   Segment trees
   -------------
   - Segment trees are used to solve problems that involve
     range queries. Eg: Sum in a range in array, Max in a given range in an array.
   - Such problems can also be solved using the concept of prefix sum, prefix product etc
     but they O(N) algo, while with segments tree, such problems can be solved in O(log(N)).
   - Segment tree is represented in form of an array and is constructed from the input array.
     Size of segment tree is input array size * 4.
     Each index in segment tree represents the range query (sum, product etc.) for a half of the
     array. Note: that segment tree array doesn't consist of range queries for all possibles ranges
     of array, it only consists of range queries for every half (recursively). But this can
     be used to find range queries for any range in O(log(N)).
 */

//@link - https://leetcode.com/problems/range-sum-query-mutable/description/
public class RangeSumQueryMutable {
    int[] segmentTree;
    int[] nums;
    int numsLength;

    public RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        this.numsLength = nums.length;
        //can be shown that for an array of size `size`,
        //the size of segment tree is nums.length*4
        segmentTree = new int[numsLength*4];

        constructSegmentTree(0, numsLength-1, 0);
    }

    public void update(int index, int val) {
        //update given index of nums array.
        //so that when we traverse the segment tree
        //for update, it can be updated with the new value present in the array.
        this.nums[index] = val;

        //update the segment tree.
        updateSegmentTree(0, this.numsLength-1, index, 0);
    }

    public int sumRange(int left, int right) {
        //find the sum in the range [left, right]
        return getRangeSum(left, right, 0, this.numsLength-1, 0);
    }

    //Updates the segement tree, since the underlying array has been updated.
    //Note that the `index` which is updated will always lie either in left half
    //or the right half and so, we don't need to traverse the entire segment tree whenever
    //an index in the array is updated. So, update will also be O(log(N)) operation.
    private void updateSegmentTree(int arrStart, int arrEnd, int index, int segTreeIndex) {
        if (arrStart > arrEnd) return;

        //when reached a leaf node, no further sub-division is possible
        //so, update the segment tree with the value in the array.
        if (arrStart == arrEnd) {
            this.segmentTree[segTreeIndex] = this.nums[arrStart];
            return;
        }

        //otherwise, move to that half in the segment tree
        //which will have answers for the range in which index lies.
        //If index is less than mid, it will lie in the range [arrStart, mid]
        //and so, update at position index means, segment tree values in the
        //range [arrStart, mid] will only be updated. So, move to that half
        //and ignore the rest. Similary, if index liest in the half [mid+1, arrEnd]
        int mid = arrStart + (arrEnd-arrStart)/2;
        int segTreeLeftChildIndex = (2 * segTreeIndex) + 1;
        int segTreeRightChildIndex = (2 * segTreeIndex) + 2;

        if (index <= mid) {
            updateSegmentTree(arrStart, mid, index, segTreeLeftChildIndex);
        } else {
            updateSegmentTree(mid+1, arrEnd, index, segTreeRightChildIndex);
        }

        //once we've updated the left and right child, update the parent that depends on the
        //left and right.
        this.segmentTree[segTreeIndex] = this.segmentTree[segTreeLeftChildIndex] + this.segmentTree[segTreeRightChildIndex];
    }

    //Gets the range query. For this question - the sum query.
    //For a range query (in given range [left, right]), there could be 3 possibilities:
    //1) That a range of the array doesn't overlap at all (no overlap) with given range.
    //2) That a range of the array completely overlaps with the given range.
    //3) That a range of the array partially overlaps with the given range.
    private int getRangeSum(int rangeLeft, int rangeRight, int arrLeft, int arrRight, int segTreeIndex) {

        //Case 1: No overlap.
        //The current range which we are processing i.e. [arrLeft, arrRight]
        //doesn't overlap at all with the range we are interested int i.e. [rangeLeft, rangeRight].
        //So, the current range [arrLeft, arrRight] doesn't contribute to the final answer at
        //all, so returning 0 (to indicate that curr range doesn't contribute any sum)
        if (rangeLeft > arrRight || rangeRight < arrLeft) {
            return 0;
        }

        //Case 2: Full overlap.
        //The current range which we are processing i.e. [arrLeft, arrRight]
        //lies completely in the interested range i.e. [rangeLeft, rangeRight].
        //Therefore, the entire current range contributes to the final answer.
        //And since the answer for this range is stored at index `segTreeIndex`
        //in the segement tree, we return it.
        if (arrLeft >= rangeLeft && rangeRight >= arrRight) {
            return segmentTree[segTreeIndex];
        }

        //Case 3: Partial overlap.
        //The current range which we are processing i.e. [arrLeft, arrRight]
        //lies partially in the interested range i.e. [rangeLeft, rangeRight].
        //So, we'll break down the curr range further in left and right halves recursively
        //and get the exact contributions of these halves to the final answer.
        int mid = arrLeft + (arrRight-arrLeft)/2;
        int segTreeLeftChildIndex = (2 * segTreeIndex) + 1;
        int segTreeRightChildIndex = (2 * segTreeIndex) + 2;

        //left halve's answer.
        int leftRangeSum = getRangeSum(rangeLeft, rangeRight, arrLeft, mid, segTreeLeftChildIndex);

        //right halve's answer.
        int rightRangeSum = getRangeSum(rangeLeft, rangeRight, mid+1, arrRight, segTreeRightChildIndex);

        //for partial overlap, return the contribution from left and right half.
        return (leftRangeSum + rightRangeSum);
    }

    //Constructs the segement tree.
    private void constructSegmentTree(int arrStart, int arrEnd, int segTreeIndex) {
        if (arrStart > arrEnd) return;

        //No further half is possible,
        //so store the nums[index] as is at
        //the segement tree index in segement tree.
        if (arrStart == arrEnd) { //means a leaf node.
            this.segmentTree[segTreeIndex] = this.nums[arrStart];
            return;
        }

        //Divide the array in two halves - left and right.
        int mid = arrStart + (arrEnd-arrStart)/2;

        //The left halve's answer in the segement tree lies at `(2 * segTreeIndex) + 1`
        //While the right halve's answer in the segment tree liest at `(2 * segTreeIndex) + 2`
        int segTreeLeftChildIndex = (2 * segTreeIndex) + 1;
        int segTreeRightChildIndex = (2 * segTreeIndex) + 2;

        //left half.
        //The range query for the half [arrStart, mid] will be
        //stored at index `segTreeLeftChildIndex` in the segement tree.
        constructSegmentTree(arrStart, mid, segTreeLeftChildIndex);

        //right half.
        //The range query for the half [mid+1, arrEnd] will be
        //stored at index `segTreeRightChildIndex` in the segment tree.
        constructSegmentTree(mid+1, arrEnd, segTreeRightChildIndex);

        //Finally, the range query for the range [arrStart, arrEnd] will be
        //stored at index `segTreeIndex` in the segment tree, and will simply
        //be the resultant of its corresponding left half (`segTreeLeftChildIndex`) and
        //corresponding right half (`segTreeRightChildIndex`)
        this.segmentTree[segTreeIndex] = this.segmentTree[segTreeLeftChildIndex] + this.segmentTree[segTreeRightChildIndex];
    }
}

/**
 * Your RangeSumQueryMutable object will be instantiated and called as such:
 * RangeSumQueryMutable obj = new RangeSumQueryMutable(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */