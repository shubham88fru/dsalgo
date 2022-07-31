package basics.sorting;

import java.util.Arrays;

public class CountingSort {

    void countSortNaiveSir(int[] arr, int k) {
        int[] count = new int[k];
        for (int value : arr) {
            count[value]++;
        }
        int index=0;
        for (int i=0; i<k;i++) {
            for(int j =0;j<count[i];j++) {
                arr[index] = i;
                index++;
            }
        }
    }

    void countSort(int[] arr, int k) {
        int[] count = new int[k];
        int n = arr.length;
        for (int i=0;i<k;i++) count[i] = 0;
        for (int j : arr) count[j]++;

        for(int i=1;i<k;i++) count[i] = count[i-1]+count[i];

        int[] output = new int[n];
        for (int i=n-1; i>=0;i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }


    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        int[] arr = new int[] {1,4,4,1,0,1};
        countingSort.countSortNaiveSir(arr, 5);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[] {1,4,4,1,0,1};
        countingSort.countSort(arr2, 5);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = new int[] {5, 6, 5, 2};
        countingSort.countSort(arr3, 7);
        System.out.println(Arrays.toString(arr3));
    }
}
