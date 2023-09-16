package strvr.binarysearch;

//@link - https://www.codingninjas.com/studio/problems/1062679
//@strvr - https://takeuforward.org/data-structure/nth-root-of-a-number-using-binary-search/
public class NthRootOfM {
    public static int NthRoot(int n, int m) {
        // Write your code here.
        return binarySearch(n, m);
    }

    private static int binarySearch(int n, int m) {
        int left = 1;
        int right = m;

        while (left <= right) {
            int mid = left+(right-left)/2;
            int pow = (int) Math.pow(mid, n);
            if (pow == m) return mid;
            else if (pow > m) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return -1;
    }
}
