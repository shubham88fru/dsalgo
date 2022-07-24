package basics.sorting;

import java.util.Arrays;

public class SelectionSort {

    //Naive
    void sortNaive(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            int min_ind = 0;
            for (int j=1; j<arr.length; j++) {
                if (arr[j] < arr[min_ind]) min_ind = j;
            }
            temp[i] = arr[min_ind];
            arr[min_ind] = Integer.MAX_VALUE;
        }
        System.arraycopy(temp, 0, arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    //T: Theta(n^2)
    void sort(int[] arr) {
        //only iterate till last but one,
        //because by the time we reach last,
        //it will already be sorted.
        for (int i=0; i<arr.length;i++) {
            int min_idx = i;
            //start finding min from rest
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j]<arr[min_idx]) min_idx = j;
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sortNaive(new int[] {1,2, 30, -10, 0, 15});
        selectionSort.sort(new int[] {1,2, 30, -10, 0, 15});
    }
}
