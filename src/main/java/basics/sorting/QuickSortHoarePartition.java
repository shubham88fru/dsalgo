package basics.sorting;

import java.util.Arrays;

public class QuickSortHoarePartition {

    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = PartitionFunctionOfQuickSort
                    .hoaresPartition(arr, low, high);
            //Note how we include `p` also for
            //sorting. This is where quick sort
            //using hoare differs quick sort using
            //lomuto.
            quickSort(arr, low, p);
            quickSort(arr, p+1, high);
        }
    }

    public static void main(String[] args) {
        QuickSortHoarePartition quickSortHoarePartition = new QuickSortHoarePartition();
        int[] arr1 = new int[] {8, 4, 7, 9, 3, 10, 5};
        quickSortHoarePartition.quickSort(arr1, 0, 6);
        System.out.println(Arrays.toString(arr1));
    }
}
