package strvr.linkedlistandarray;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
//@strvr - https://takeuforward.org/data-structure/remove-duplicates-in-place-from-sorted-array/
public class RemoveDuplicateElementsFromSortedArray {
    public int removeDuplicates(int[] arr) {
        // return removeDuplicatesOptimal(arr);
        // return revise(arr);
        return revise2(arr);
    }

    /**
     Optimal - majorly same as
     the better sol, just a smart way
     to not use set to keep track of
     elements that were seen before.
     */
    private int revise2(int[] arr) {
        int n = arr.length;
        int j=0;
        for (int i=1; i<n; i++) {
            if (arr[i] != arr[j]) {
                arr[j+1] = arr[i];
                j += 1;
            }
        }

        return j+1;
    }

    /**
     My Better soln.
     */
    private int revise(int[] arr) {
        int n = arr.length;

        Set<Integer> st = new HashSet<>();
        int j = 0;
        for (int i=0; i<n; i++) {
            if (!st.contains(arr[i])) {
                arr[j] = arr[i];
                st.add(arr[i]);
                j += 1;
            }
        }

        return st.size();
    }

    private int removeDuplicatesOptimal(int[] arr) {
        //idx will hold the part array which is fixed.
        //i.e. all elements from 0 to idx, at any point
        //will be unique.
        int idx = 0; //first element is always unique.
        //then we start from first element and check
        //if its same as last element of fixed array
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != arr[idx]) {
                idx += 1; //if a new unique element found,
                //move it to fixed part and increase the fixed window.
                arr[idx] = arr[i];
            }
        }

        //need to return the length of fixed part
        //which will be one more than index.
        return idx+1;
    }
}
