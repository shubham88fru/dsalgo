package basics.sorting;

import java.util.Arrays;

//Partition the array such that,
//for a given pivot, all the els smaller
//than pivot are left to the pivot and all
//the els greater than pivot are to its right.
public class PartitionFunctionOfQuickSort {

    //T: O(N), S: O(N)
    //needs 3 traversals of array
    void partitionFunctionNaive(int[] arr, int low, int high, int partitionIndex) {
        int[] temp = new int[high - low + 1];
        int index = 0;
        for (int i=low; i<=high; i++) {
            if (arr[i]<=arr[partitionIndex]) {
                temp[index] = arr[i];
                index++;
            }
        }
        for (int i=low; i<=high; i++) {
            if (arr[i]>arr[partitionIndex]) {
                temp[index] = arr[i];
                index++;
            }
        }
        System.arraycopy(temp, 0, arr, 0, temp.length);
        System.out.println(Arrays.toString(arr));
    }

    int lomutoPartition(int[] arr, int low, int high) {
        int pivot = arr[high]; //we assume that pivot is always the last element.
        int i = low-1; //window of smaller elements (than pivot)
        for (int j=low; j<=high-1; j++) {
            if (arr[j] < pivot) {
                i++;
                //swap(arr[i], arr[j])
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //Finally swap the pivot (i.e. arr[high])
        //with the first el next to the smaller
        //element window.
        //swap(arr[i+1], arr[high])
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        System.out.println(Arrays.toString(arr));
        return i+1; //final pos of pivot.
    }

    public static void main(String[] args) {
        PartitionFunctionOfQuickSort partitionFunctionOfQuickSort =
                        new PartitionFunctionOfQuickSort();

        partitionFunctionOfQuickSort.partitionFunctionNaive(new int[] {5, 13, 6, 9, 12, 11, 8}, 0, 6, 6);

        partitionFunctionOfQuickSort.lomutoPartition(new int[] {10, 80, 30, 90, 40, 50, 70}, 0, 6);
        //TODO: try below corner cases with lomuto partition
        //[70, 60, 80, 40, 30] --> pivot is smaller than all other els.
        //[30, 40, 20, 50, 80] --> pivot is greater than all other els.
    }
}
