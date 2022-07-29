package basics.sorting;

import java.util.Arrays;

public class QuickSortLomutoPartition {

    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = PartitionFunctionOfQuickSort
                    .lomutoPartition(arr, low, high);
            quickSort(arr, low, p-1); //sort left half.
            quickSort(arr, p+1, high); //sort right half.
        }
    }

    public static void main(String[] args) {
        QuickSortLomutoPartition quickSortLomutoPartition = new QuickSortLomutoPartition();
        int[] arr1 = new int[] {8, 4, 7, 9, 3, 10, 5};
        quickSortLomutoPartition.quickSort(arr1, 0, 6);
        System.out.println(Arrays.toString(arr1));
    }
}
