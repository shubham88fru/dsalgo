package ptrn.kwaymerge;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5482436659249152
public class KthSmallestNumberInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        //return brute(matrix, k);
        return optimal(matrix, k);
    }

    private int optimal(int[][] matrix, int k) {
        PriorityQueue<Wrapper> minHeap =
                new PriorityQueue<>((w1, w2) -> matrix[w1.row][w1.col] - matrix[w2.row][w2.col]);

        //Put first element of all the lists to
        //the min heap. Idea is to find the num
        //and list which has the smallest element to
        //start with.
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new Wrapper(i, 0, matrix[i][0]));
        }

        int kthSmallest = matrix[0][0];

        //ATQ, k will be lesser than total num
        //of elements, so we loop till we find
        //the kth smallest.
        while (k >= 1) {
            Wrapper wrapped = minHeap.remove();
            int row = wrapped.row;
            int col = wrapped.col;
            int num = wrapped.num;

            kthSmallest = num;
            k -= 1;

            //If the current row has any elements that
            //can be pushed, push it to the min heap.
            if (col+1 < matrix.length) {
                minHeap.add(new Wrapper(row, col+1, matrix[row][col+1]));
            }

        }

        return kthSmallest;
    }


    //Each row of the matrix is sorted and so, the
    //matrix itself can be seen as a list of m sorted rows.
    //In this approach, we keep merging the rows into a single
    //big sorted list and then return the kth element from the start.
    private int brute(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = 1;
        int[] sorted = matrix[0];
        while (j < m) {
            sorted = mergeSortedArrays(sorted, matrix[j], new int[sorted.length+m]);
            j += 1;
        }

        return sorted[k-1];
    }

    private int[] mergeSortedArrays(int[] a1, int[] a2, int[] sorted) {
        int m = a1.length;
        int n = a2.length;
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            if (a1[p1] <= a2[p2]) {
                sorted[p] = a1[p1];
                p1 += 1;
            } else {
                sorted[p] = a2[p2];
                p2 += 1;
            }
            p += 1;
        }

        while (p1 < m) {
            sorted[p] = a1[p1];
            p1 += 1;
            p += 1;
        }

        while (p2 < n) {
            sorted[p] = a2[p2];
            p2 += 1;
            p += 1;
        }

        return sorted;
    }
}

class Wrapper {
    int row;
    int col;
    int num;

    public Wrapper(int row, int col, int num) {
        this.row = row;
        this.num = num;
        this.col = col;
    }
}
