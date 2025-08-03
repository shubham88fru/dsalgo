package lc_potd;

//@link - https://leetcode.com/problems/maximum-fruits-harvested-after-at-most-k-steps/description/
//@check - https://www.youtube.com/watch?v=9biz4kxyVh8&ab_channel=codestorywithMIK
public class MaximumFruitsHarvestedAfterAtmostKSteps {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        return mikssol(fruits, startPos, k);
    }

    /**
     * Not completely my code.
     * My code was failing for edge cases,
     * so took bits from mik. need to check.
     */
    private int mikssol(int[][] fruits, int startPos, int k) {
        int n = fruits.length;

        int[] ps = new int[n];
        ps[0] = fruits[0][1];
        for (int i=1; i<n; i++) {
            ps[i] = (fruits[i][1] + ps[i-1]);
        }

        int max = 0;
        for (int d=0; d<=k/2; d++) {
            //case 1: move left;
            int i = startPos - d;
            int j = startPos + (k - 2*d);
            int lmove1 = firstLargerOrEqual(fruits, i ,startPos);
            int rmove1 = lastSmallerOrEqual(fruits, j, startPos)-1;

            if (lmove1 <= rmove1) {
                int total = ps[rmove1] - (lmove1 > 0 ? ps[lmove1 - 1] : 0);
                max = Math.max(max, total);
            }

            //case 2: move right;
            j = startPos + d;
            i = startPos - (k - 2*d);
            int lmove2 = firstLargerOrEqual(fruits, i ,startPos);
            int rmove2 = lastSmallerOrEqual(fruits, j, startPos)-1;

            if (lmove2 <= rmove2) {
                int total = ps[rmove2] - (lmove2 > 0 ? ps[lmove2 - 1] : 0);
                max = Math.max(max, total);
            }
        }

        return max;
    }

    private int firstLargerOrEqual(int[][] arr, int target, int p) {
        // int n = fruits.length;

        // int i = 0;
        // int l = 0;
        // int r = n-1;
        // while (l <= r) {
        //     int mi = l + (r-l)/2;
        //     if (fruits[mi][0] >= d) {
        //         i = mi;
        //         r = mi - 1;
        //     } else {
        //         l = mi + 1;
        //     }
        // }

        // return l;

        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int lastSmallerOrEqual(int[][] arr, int target, int p) {
        // int n = fruits.length;

        // int i = 0;

        // int l = 0;
        // int r = n-1;

        // while (l <= r) {
        //     int mi = l + (r-l)/2;
        //     if (fruits[mi][0] <= d) {
        //         i = mi;
        //         l = mi + 1;
        //     } else {
        //         r = mi - 1;
        //     }
        // }

        // return l;

        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid][0] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
