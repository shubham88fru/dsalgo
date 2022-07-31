package basics.sorting;

import java.util.Arrays;

public class HeapSort {

    void heapSort(int[] arr) {
        int n = arr.length;
        BuildMaxHeap.buildHeap(arr);
        for (int i = n-1; i >= 1; i--) {
            //swap(arr[0], arr[i])
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            BuildMaxHeap.maxHeapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr = new int[] {50, 20, 10, 4, 15};
        heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
