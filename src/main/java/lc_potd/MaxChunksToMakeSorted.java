package lc_potd;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/max-chunks-to-make-sorted/
//@check - https://www.youtube.com/watch?v=OwEggxOnszA&t=278s&ab_channel=codestorywithMIK
public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        // return pass1(arr);
        // return pass2(arr);
        return pass3(arr);
    }

    /*
        Following is my solution. Mik might have a
        similar solution (didn't watch his soln) or multiple approaches.
        @check if this is recurring problem for some
        company.
    */
    private int pass3(int[] arr) {
        int n = arr.length;
        int chunks = 0;
        int maxSeen = arr[0];
        int i = 0;

        while (i < n) {
            if (arr[i] > maxSeen) {
                maxSeen = arr[i];
                i += 1;
            } else {

                if (i == maxSeen) { //maxSeen is at right place, end of current chunk.
                    chunks += 1;
                    i += 1;
                    if (i < n) maxSeen = arr[i]; //reset maxSeen to first element.
                } else {
                    //If current element is smaller than the
                    //max seen so far, there is no way the current
                    //element can be part of a new chunk, otherwise,
                    //after sorting, things won't align.
                    i += 1;
                }
            }
        }

        return chunks;
    }

    //My pass 2. Doesn't work.
    private int pass2(int[] arr) {
        int n = arr.length;
        int i=0;

        int chunks = 0;
        int j = 0;

        while (i < n) {
            if (arr[i] != i) {
                if (arr[i] <= j) {
                    i += 1;
                    continue;
                }
                chunks += 1;
                while (arr[i] != i) {
                    int temp = arr[arr[i]];
                    j = Math.max(j, arr[i]);

                    arr[arr[i]] = arr[i];
                    arr[i] = temp;
                }
                i = j+1;
            } else {
                chunks += 1;
                i += 1;
            }

        }

        return chunks;
    }

    //My pass 1. Doesn't work.
    private int pass1(int[] arr) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        for (int i=0; i<arr.length; i++) heap.add(new int[] {arr[i], i});

        int chunks = 1;
        int prevIdx = heap.remove()[1];
        while (!heap.isEmpty()) {
            int[] rem = heap.remove();
            if (rem[1] > prevIdx) chunks += 1;
            prevIdx = rem[1];
        }

        return chunks;
    }
}
