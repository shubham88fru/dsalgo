package strvr.binarysearch;

//@link - https://leetcode.com/problems/median-of-two-sorted-arrays/description/
//@strvr part 1 - https://www.youtube.com/watch?v=C2rRzz-JDk8&ab_channel=takeUforward
//@strvr part 2 - https://www.youtube.com/watch?v=F9c7LpRZWVQ&ab_channel=takeUforward

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] a, int[] b) {
        return binarySearchForMedian(a, b);
    }

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

    //3) Brute force. Merge two sorted arrays in third array (O(N) time and O(N) space) and then
    //in the merged array find the median depending the on the length.
}
