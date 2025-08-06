package lc_potd;

//@link - https://leetcode.com/problems/fruits-into-baskets-iii/description/
//@check - https://www.youtube.com/watch?v=OC3zzMwCdSk&ab_channel=codestorywithMIK
public class FruitsInBasketIII {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        return mikssol(fruits, baskets);
    }

    private int mikssol(int[] fruits, int[] baskets) {
        int n = baskets.length;
        int[] segTree = new int[4*n];

        buildSegTree(0, 0, n-1, baskets, segTree);

        int unplaced = 0;
        for (int fruit: fruits) {
            if (!querySegTree(segTree, fruit, 0, 0, n-1)) {
                unplaced += 1;
            }
        }

        return unplaced;
    }

    private boolean querySegTree(int[] segTree, int fruit, int si, int l, int r) {
        if (segTree[si] < fruit) return false;

        if (l == r) {
            segTree[si] = -1;
            return true;
        }

        int siLeftChild = 2*si + 1;
        int siRightChild = 2*si + 2;
        int mid = l + (r-l)/2;

        boolean placed = false;
        if (segTree[siLeftChild] >= fruit) {
            placed = querySegTree(segTree, fruit, siLeftChild, l, mid);
        } else {
            placed = querySegTree(segTree, fruit, siRightChild, mid+1, r);
        }

        //!! not sure why this needs to be done.
        segTree[si] = Math.max(segTree[siLeftChild], segTree[siRightChild]);

        return placed;
    }

    private void buildSegTree(int si, int l, int r, int[] baskets, int[] segTree) {
        if (l == r) {
            segTree[si] = baskets[l];
            return;
        }

        int mid = l + (r-l)/2;

        int siLeftChild = 2*si + 1;
        int siRightChild = 2*si + 2;

        buildSegTree(siLeftChild, l, mid, baskets, segTree);
        buildSegTree(siRightChild, mid+1, r, baskets, segTree);

        segTree[si] = Math.max(segTree[siLeftChild], segTree[siRightChild]);
    }
}
