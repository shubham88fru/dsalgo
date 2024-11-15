package lc_potd;

//@link - https://leetcode.com/problems/minimized-maximum-of-products-distributed-to-any-store/description/
//@check - https://www.youtube.com/watch?v=VaUhSxN1-S0&ab_channel=codestorywithMIK
public class MinimizedMaximumOfProductsDistributedToAnyStore {
    public int minimizedMaximum(int n, int[] quantities) {
        return usingMiksApproach(n, quantities);
    }

    /*
        My first intuition was to solve this problem
        using heap (see submission history.), but that didn't work.

        Based on Mik's explanation, this problem falls in the topic
        of - "Binary search on answer". As you'll see in the solution
        below, what we do is pick an answer (logically and educated) and
        see if that answer satisfies the need, if so, we try to look if a smaller
        answer is possible. We use binary search to pick an educated answer.

        Following is the solution coded by me, but completely inspired by
        mik's explanation.
    */
    private int usingMiksApproach(int n, int[] quantities) {
        int max = Integer.MIN_VALUE;
        for (int qty: quantities) {
            max = Math.max(max, qty); //max possible ans (r for binary search) will be max of the array.
        }

        return getAnsByBinarySearch(n, max, quantities);
    }

    /*
        Performs binary search on answer.
        i.e. Finds an answer, and then tries to
        find a smaller answer (because the aim is to minimize)
    */
    private int getAnsByBinarySearch(int n, int max, int[] quantities) {
        int l = 1; //min possible answer.
        int r = max; //max possible answer.

        int min = -1;
        while (l <= r) {
            int ans = l + (r-l)/2;
            if (possibleToDistributeAll(ans, n, quantities)) {
                min = ans;
                r = ans - 1;
            } else {
                l = ans + 1;
            }
        }

        return min;
    }

    /*
        For a given choice of ans, if we can't fit
        all products on the remaining stores, then it's not a
        valid choice, and we should look for a higher value of ans.
     */
    private boolean possibleToDistributeAll(int ans, int n, int[] quantities) {
        for (int qty: quantities) {
            int req = (int)Math.ceil(qty/(ans*1.0));
            if (req > n) return false;
            n -= req; //remaining stores that can receive product.
        }

        return true;
    }
}
