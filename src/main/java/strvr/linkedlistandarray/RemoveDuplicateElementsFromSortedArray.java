package strvr.linkedlistandarray;

//@link - https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
//@strvr - https://takeuforward.org/data-structure/remove-duplicates-in-place-from-sorted-array/
public class RemoveDuplicateElementsFromSortedArray {
    public int removeDuplicates(int[] arr) {
        return removeDuplicatesOptimal(arr);
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
                //move it to fixed part and incrase the fixed window.
                arr[idx] = arr[i];
            }
        }

        //need to return the length of fixed part
        //which will be one more than index.
        return idx+1;
    }
}
