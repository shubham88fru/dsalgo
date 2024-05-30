package lc_potd;

//@link - https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/
//@check - https://www.youtube.com/watch?v=IAcO4Wyr2ak&ab_channel=AryanMittal
public class CountTripletsThatCanFormArraysOfEqualXOR {
    public int countTriplets(int[] arr) {
        // return brute(arr);
        return prefXor(arr);
    }

    //1) Optimized soln using prefix xor.
    //Check the linked video for more optimizations.
    private int prefXor(int[] arr) {
        int n = arr.length;

        //prefix xor array.
        int[] pxor = new int[n+1]; //note size n+1, not n.
        pxor[0] = 0;
        for (int i=0; i<n; i++) {
            pxor[i+1] = pxor[i] ^ arr[i];
        }

        int count = 0;
        for (int i=0; i<n; i++) {
            for (int k=i+1; k<=n; k++) {
                /**
                 If pxor till index k is same
                 as pxor till index i, means the
                 xor of elements from i+1 to k is zero.
                 And so, we'll have k-(i+1) pairs of array
                 (in the range i+1 to k) such that xor of each
                 array will be equal.
                 */
                if (pxor[k] == pxor[i]) {
                    count += (k-(i+1));
                }
            }
        }

        return count;

    }


    //2) Brute force.
    //Surprisingly this passes all tests.
    private int brute(int[] arr) {
        int count = 0;
        for (int i=0; i<=arr.length-2; i++) {
            long a = 0;
            for (int j=i+1; j<=arr.length-1; j++) {
                a ^= arr[j-1];
                long b = 0;
                for (int k=j; k<=arr.length-1; k++) {
                    b ^= arr[k];
                    if (a == b) {
                        count += 1;
                    }
                }
            }
        }

        return count;
    }
}
