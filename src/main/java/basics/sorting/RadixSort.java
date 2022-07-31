package basics.sorting;

import java.util.Arrays;

public class RadixSort {

    void radixSort(int[] arr) {
        int max = arr[0];
        for (int el: arr) {
            if (el>max) max = el;
        }

        for(int exp=1;max/exp>0;exp=exp*10)
            countSort(arr, exp);
    }

    //Note this implementation of
    //count sort is exactly same as prev
    //minor modifications so as to fit
    //it with radix sort.
    static void countSort(int[] arr, int exp) {
        int[] count = new int[10];
        int n = arr.length;
        for (int i=0;i<10;i++) count[i] = 0;
        for (int j : arr) count[(j/exp)%10]++;

        for(int i=1;i<10;i++) count[i] = count[i-1]+count[i];

        int[] output = new int[n];
        for (int i=n-1; i>=0;i--) {
            output[count[(arr[i]/exp)%10] - 1] = arr[i];
            count[(arr[i]/exp)%10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }



    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        int[] arr = new int[] {319, 212, 6, 8, 100, 50};
        radixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
