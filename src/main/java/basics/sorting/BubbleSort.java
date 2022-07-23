package basics.sorting;

import java.util.Arrays;

public class BubbleSort {

    //T: Theta(n^2)
    void sort(int[] arr) {
        int compareTill = arr.length-1;
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<compareTill;j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
            compareTill--; //each pass has positioned the largest element to last
        }
        System.out.println(Arrays.toString(arr));
    }


    void sortSir(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            boolean swapped = false;
            for (int j=0; j<arr.length-i-1;j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            //If no swaps in a pass, means array already sorted.
            if (!swapped) break;
        }
        System.out.println(Arrays.toString(arr));
    }

    //T: O(N^2)
    void sortOptimized(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-i-1;j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(new int[] {5, 6,1,2,10});
        bubbleSort.sort(new int[] {15, 6,1,2,10});
        bubbleSort.sort(new int[] {25, 16,1,-2,10});
        bubbleSort.sort(new int[] {10, 8, 20, 5});

        System.out.println("------------------------");

        bubbleSort.sortSir(new int[] {5, 6,1,2,10});
        bubbleSort.sortSir(new int[] {15, 6,1,2,10});
        bubbleSort.sortSir(new int[] {25, 16,1,-2,10});
        bubbleSort.sortSir(new int[]{10, 8, 20, 5});

        System.out.println("------------------------");

        bubbleSort.sortOptimized(new int[] {5, 6,1,2,10});
        bubbleSort.sortOptimized(new int[] {15, 6,1,2,10});
        bubbleSort.sortOptimized(new int[] {25, 16,1,-2,10});
        bubbleSort.sortOptimized(new int[]{10, 8, 20, 5});
        bubbleSort.sortOptimized(new int[]{3, 5, 10, 20, 40});

    }
}
