package basics.sorting;

import java.util.Arrays;

import static basics.sorting.MergeFunctionOfMergeSort.mergeFunctionSir2;

public class MergeSort {

    //l -> first index (left)
    //r -> last index (right)
    //T: Theta(nlogn), S: Theta(n)
    void mergeSort(int[] arr, int l, int r) {
        if (r>l) { //check if atleast two els present.
            int m = l+(r-l)/2; //same as (l+r)/2;
            mergeSort(arr, l, m); //sort left half
            mergeSort(arr, m+1, r); //sort right half
            mergeFunctionSir2(arr, l, m ,r); //merge two sorted arrays
        }
    }
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[] {10, 1, 5, 6, -1};
        mergeSort.mergeSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));

        arr = new int[] {10,5,30,15,7};
        mergeSort.mergeSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }
}
