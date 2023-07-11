package strvr.arrays2;

//@link - https://takeuforward.org/data-structure/count-inversions-in-an-array/
//@strvr - https://www.codingninjas.com/studio/problems/number-of-inversions_6840276
public class NumberOfInversions {
    public static int numberOfInversions(int []a, int n) {
        //return numInvBrute(a);
        return numInvOptimal(a);
    }

    //1) Optimal approach: T: O(nlogn), S: O(N)
    //Using merge sort.
    //If we have two sorted arrays, when merging
    //them to one array, if we encounter a larger element
    //in the first array (than the current element of the second array)
    //then all the remaing elements of the first array will form a valid
    //pair with curr element of the second array. After this, we merge the
    //two sorted array and continue to find the valid pairs in a similar manner.
    private static int numInvOptimal(int[] a) {
        //will store the ans.
        int[] ans = new int[1];
        mergeSortAndFindTheCount(a, 0, a.length-1, ans);
        return ans[0];
    }

    //merge sort algorith.
    private static void mergeSortAndFindTheCount(int[] a, int llow, int rhigh,
                                                 int[] ans) {
        if (llow >= rhigh) return;

        int mid = (rhigh+llow)/2;
        //sort left half.
        mergeSortAndFindTheCount(a, llow, mid, ans);
        //sort right half.
        mergeSortAndFindTheCount(a, mid + 1, rhigh, ans);
        //merge the halves.
        mergeWithGivenRange(a, llow, mid, rhigh, ans);

    }

    //merges two sorted ranges of the same array.

    private static void mergeWithGivenRange(int[] a, int llow, int mid,
                                            int rhigh, int[] ans) {
        int i = llow;
        int j = mid + 1;

        int idx = 0;
        int[] temp = new int[rhigh-llow+1];

        //merge sorted ranges [llow, mid] and [mid+1, rhigh]
        while (i <= mid && j <= rhigh) {
            if (a[i] <= a[j]) {
                temp[idx++] = a[i];
                i += 1;
            } else {
                temp[idx++] = a[j];
                j += 1;

                //when element if first array
                //is larger, means that and all the remaining
                //elements in the first array will form a valid
                //pair with the jth element of the right array.
                //so increase the ans count by that much.
                ans[0] += (mid - i + 1);
            }
        }


        while (i <= mid) {
            temp[idx++] = a[i];
            i += 1;
        }

        while (j <= rhigh) {
            temp[idx++] = a[j];
            j += 1;
        }

        //copy the sorted range back to the orginal
        //array.
        for (int k=llow; k<=rhigh; k++) {
            a[k] = temp[k-llow];
        }
    }


    //2) Brute force: T: O(N^2), S: O(1)
    //for every num in array check if elements
    //to its right are smaller and if yes, then
    //increment the count.
    private static int numInvBrute(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if (a[j] < a[i]) count += 1;
            }
        }

        return count;
    }
}
