package swd.binarysearch;

//@link - https://leetcode.com/problems/sqrtx/description/
public class SquareRoot {
    public int mySqrt(int x) {
        return binarySearch(x);
    }

    private int binarySearch(int x) {
        int start = 1;
        int end = x;

        int root = 0;
        while (start <= end) {
            int mid = (start + (end-start)/2);

            //if mid^2 less than equal x means it could be a
            //posssible root. Hence, record and continue search to its
            //right for a more accurate root.
            //Note: applied log to the inequality (mid^2 <= x --> log(mid^2) <= log(x) --> 2*log(mid) <= log(x))
            //so as to avoid overflow when squaring mid.
            if (2*Math.log(mid) <= Math.log(x)) {
                root = mid;
                start = mid + 1;
            } else { //else if mid^2 greater than no. means we can only have to left of mid.
                end = mid - 1;
            }
        }

        //return closest root possible.
        return root;
    }
}
