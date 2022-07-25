package basics.sorting;

import java.util.Arrays;

//Given an array and 3 indexes (low, mid, and high)
//and that elements from low to mid are sorted, while
//elements from mid+1 to high are also sorted. Need
//to sort the elements from low to high.
//low need not be first index of array,
//high need not be last index of array.
public class MergeFunctionOfMergeSort {

    //T: Theta(N), N -> no. of els from low to high;
    void sortFromLowToHighMy(int[] arr, int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(arr, low, mid+1);
        int[] right = Arrays.copyOfRange(arr, mid+1, high+1);
        int[] temp = new int[high-low+1];
        int idx=0;
        int i = 0;
        int j = 0;

        while (i<left.length && j<right.length) {
            if (left[i]<=right[j]) {
                temp[idx] = left[i];
                i++;
            } else {
                temp[idx]=right[j];
                j++;
            }
            idx++;
        }

        while (i<left.length) {
            temp[idx] = left[i];
            i++;idx++;
        }
        while (j<right.length) {
            temp[idx] = right[j];
            j++;idx++;
        }

        System.out.println(Arrays.toString(temp));
    }

    public static void main(String[] args) {
        MergeFunctionOfMergeSort mergeFunctionOfMergeSort = new MergeFunctionOfMergeSort();
        mergeFunctionOfMergeSort.sortFromLowToHighMy(new int[]{10, 15, 20, 40, 8, 11, 55}, 0, 3, 6);
        System.out.println("------------------------------");
        mergeFunctionOfMergeSort.sortFromLowToHighMy(new int[]{10, 20, 40, 20, 30}, 0, 2, 4);

    }
}
