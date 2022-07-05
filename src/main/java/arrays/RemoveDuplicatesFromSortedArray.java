package arrays;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    // My Soln - doesn't work
    static int removeDuplicatesFromSortedArray(int arr[]) {
        int duplicateIdx = -1;
        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] == arr[i-1]) && duplicateIdx==-1) {
                duplicateIdx = i;
            } else if (duplicateIdx !=-1) {
                arr[duplicateIdx] = arr[i];
                duplicateIdx++;
            }
        }
        return -1;
    }

    //Naive Soln
    //T: O(N), S:(N)
    static int removeDuplicatesFromSortedArrayNaiveSir(int arr[]) {
        int[] temp = new int[arr.length];
        temp[0] = arr[0];
        int res = 1; // Need to return size of distinct elements as per question

        //Copy distinct elements of arr to temp
        for (int i=1; i< arr.length; i++) {
            if (temp[res-1] != arr[i]) {
                temp[res] = arr[i];
                res++;
            }
        }

        if (res >= 0) System.arraycopy(temp, 0, arr, 0, res);
        System.out.println(Arrays.toString(arr));
        return res;
    }

    //T: O(N), S:O(1)
    static int removeDuplicatesFromSortedArraySir(int arr[]) {
        int res = 1;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] != arr[res-1]) {
                arr[res] = arr[i];
                res++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return res;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicatesFromSortedArrayNaiveSir(new int[]{10, 20, 20, 30, 30, 30}));
        System.out.println(removeDuplicatesFromSortedArraySir(new int[]{10, 20, 20, 30, 30, 30}));
    }
}
