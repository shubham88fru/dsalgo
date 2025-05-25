package strvr.binarysearch;

//@link - https://leetcode.com/problems/median-of-two-sorted-arrays/description/
//@strvr part 1 - https://www.youtube.com/watch?v=C2rRzz-JDk8&ab_channel=takeUforward
//@strvr part 2 - https://www.youtube.com/watch?v=F9c7LpRZWVQ&ab_channel=takeUforward
//@mik part 1 - https://www.youtube.com/watch?v=6D9T2ZY8h5c&t=1212s&ab_channel=codestorywithMIK
//@mik part 2 - https://www.youtube.com/watch?v=7nABqJCEMuY&t=2091s&ab_channel=codestorywithMIK
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] a, int[] b) {
        // return binarySearchForMedian(a, b);
        // return brute(a, b);
        // return better(a, b);
        return reviseOptimal(a, b);
    }

    /**
     * UPDATE 05/25: Wrote code based on mik's explanation below.
     */
    //1) Optimal soln using bs.
    //Idea is to partition the elements into two groups
    //such that the group on the left consists of
    //elements lesser than the elements in the group on the right.
    //The partition is such that we have
    //(n1+n2+1)/2 on the left side of partition and rest on right.
    private double reviseOptimal(int[] a, int[] b) {
        int n1 = a.length;
        int n2 = b.length;

        if (n1 >= n2) return binarySearchSol(b, a);
        return binarySearchSol(a, b);
    }

    private double binarySearchSol(int[] a, int[] b) {
        int n = a.length;
        int leftTotal = (a.length+b.length+1)/2;
        int l = 0; //take no elements of first array on the left.
        int r = n; //take all elements of first array on the left.

        while (l <= r) {
            int aLeftCount = l + (r-l)/2;
            int bLeftCount = leftTotal - aLeftCount;

            int al = Integer.MIN_VALUE;
            int bl = Integer.MIN_VALUE;
            int ar = Integer.MAX_VALUE;
            int br = Integer.MAX_VALUE;

            if (aLeftCount > 0) al = a[aLeftCount-1];
            if (aLeftCount < a.length) ar = a[aLeftCount];
            if (bLeftCount > 0) bl = b[bLeftCount-1];
            if (bLeftCount < b.length) br = b[bLeftCount];

            if (al <= br && bl <= ar) {
                if ((a.length + b.length)%2 != 0) return Math.max(al, bl);
                return (Math.max(al, bl) + Math.min(ar, br))/2.0;
            }

            if (al > br) {
                r = aLeftCount - 1;
            } else {
                l = aLeftCount + 1;
            }
        }

        return -1.0;
    }

    /**
     There is no way, I'll be able to write the optimal
     solution in an interview without having revised it before.
     Mik's explanation was better. So, if interviewing with some company
     that asks this question frequently, rewatch the explanation and
     try to solve then.

     Below is strvr's soln, but better stick with Mik's explanation and solution.
     */

    //1) Optimal approach.
    private double binarySearchForMedian(int[] a, int[] b) {
        int n1 = a.length;
        int n2 = b.length;

        if (n1>n2) return binarySearchForMedian(b, a);
        int low = 0;
        int high = n1;
        int left = (n1+n2+1)/2;
        int n = (n1+n2);
        while (low <= high) {
            int mid1 = (low+high) >> 1;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int r2 = Integer.MAX_VALUE;

            if (mid1<n1) r1 = a[mid1];
            if (mid2<n2) r2 = b[mid2];
            if (mid1 -1 >= 0) l1 = a[mid1-1];
            if (mid2-1 >= 0) l2 = b[mid2-1];
            if (l1 <= r2 && l2 <= r1) {
                if (n%2 == 1) return Math.max(l1, l2);
                return (double)(Math.max(l1, l2) + Math.min(r1, r2))/2.0;
            } else if (l1 > r2) high = mid1-1;
            else low = mid1+1;
        }
        return 0.0;
    }

    //2) Better. We don't need to merge the entire arrays into the third.
    //Since we are just looking for the mid elements, we'll just try to keep track
    //of mid and (if needed) mid+1 element. This won't save us any time complexity but
    //will save us space complexity.
    private double better(int[] a, int[] b) {
        int alen = a.length;
        int blen = b.length;
        int n = alen + blen;

        int mid = -1;
        int smid = -1;

        int midi = -1;
        int smidi = -1;
        if (n%2!=0) midi = n/2;
        else {
            midi = n/2-1;
            smidi = n/2;
        }

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < alen && j < blen) {
            if (a[i] < b[j]) {
                if (k == midi) {
                    mid = a[i];
                } else if (k == smidi) {
                    smid = a[i];
                }
                i += 1;
            } else {
                if (k == midi) {
                    mid = b[j];
                } else if (k == smidi) {
                    smid = b[j];
                }
                j += 1;
            }


            k += 1;
        }

        while (i < alen) {
            if (k == midi) {
                mid = a[i];
            } else if (k == smidi) {
                smid = a[i];
            }
            i += 1;
            k += 1;


        }

        while (j < blen) {
            if (k == midi) {
                mid = b[j];
            } else if (k == smidi) {
                smid = b[j];
            }

            j += 1;
            k += 1;
        }


        if ((n)%2 != 0) return (double) mid;
        return (double) (mid + smid)/2.0;
    }

    //3) Brute force. Merge two sorted arrays in third array (O(N) time and O(N) space) and then
    //in the merged array find the median depending the on the length.
    private double brute(int[] a, int[] b) {
        int alen = a.length;
        int blen = b.length;
        int n = alen + blen;

        int[] merged = new int [n];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < alen && j < blen) {
            if (a[i] < b[j]) {
                merged[k] = a[i];
                i += 1;
            } else {
                merged[k] = b[j];
                j += 1;
            }
            k += 1;
        }

        while (i < alen) {
            merged[k] = a[i];
            i += 1;
            k += 1;
        }

        while (j < blen) {
            merged[k] = b[j];
            j += 1;
            k += 1;
        }

        if ((n)%2 != 0) return (double) merged[(n)/2];
        return (double) (merged[n/2-1] + merged[n/2])/2.0;
    }
}
