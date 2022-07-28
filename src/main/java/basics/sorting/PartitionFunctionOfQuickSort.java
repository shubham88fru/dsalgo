package basics.sorting;

import java.util.Arrays;

public class PartitionFunctionOfQuickSort {

    //T: O(N), S: O(N)
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

    public static void main(String[] args) {
        PartitionFunctionOfQuickSort partitionFunctionOfQuickSort =
                        new PartitionFunctionOfQuickSort();
        partitionFunctionOfQuickSort.partitionFunctionNaive(new int[] {5, 13, 6, 9, 12, 11, 8}, 0, 6, 6);

    }
}
