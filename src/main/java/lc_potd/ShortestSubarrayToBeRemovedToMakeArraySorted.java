package lc_potd;

//@link - https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
//@check - https://www.youtube.com/watch?v=yOZSUfL-DxQ&ab_channel=codestorywithMIK
public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    public int findLengthOfShortestSubarray(int[] arr) {
        return mikssol(arr);
    }

    /*
        Coded by me, but completely based on mik's explanation.
     */
    private int mikssol(int[] arr) {
        int j = 0;

        //j pointer from back, till elements
        //are in decreasing order.
        for (j=arr.length-1; j>=1; j--) {
            if (arr[j] < arr[j-1]) break;
        }

        //if array is already sorted,
        //don't need to remove any subarray.
        if (j==0) return 0;

        //two pointer approach.
        int i = 0;
        int minLen = j; //imp.
        while ((i < arr.length)) {
            if (i !=0 && arr[i] < arr[i-1]) break;

            if (j >= arr.length) {
                minLen = Math.min(minLen, j-i-1);
                i += 1;
            } else if (arr[i] <= arr[j]) {
                minLen = Math.min(minLen, j-i-1);
                i += 1;
            } else {
                j += 1;
            }
        }

        return minLen;
    }
}
