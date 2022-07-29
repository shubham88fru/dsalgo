package basics.sorting;

import java.util.Arrays;

public class QuickSortLomutoPartition {

    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = PartitionFunctionOfQuickSort
                    .lomutoPartition(arr, low, high);
            //Note in below two calls, `p` is not included.
            //this is because, after lomuto partition,
            //`p` is already at its correct position, and
            //hence no need to include it for any sorting.
            //This is where quick sort algo using lomuto
            //differs from quick sort using hoare's partition.
            //since hoare's partition doesn't guarantee the pivot
            //to be placed at its correct position, we need to
            //include `p` as well for sort.
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
