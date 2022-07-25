package basics.sorting;

import java.util.Arrays;

//Merge two sorted arrays,
//such that resultant is also
//sorted.
public class MergeTwoSortedArrays {

    //Naive
    //T: O((m+n)*log(m+n)), S: Theta(m+n)
    void mergeSortedArrays1(int[] arr1, int[] arr2) {
        int[] arr3 = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, arr3, 0, arr1.length);
        System.arraycopy(arr2, 0, arr3, arr1.length, arr2.length);
        System.out.println("Before " + Arrays.toString(arr3));
        Arrays.sort(arr3);
        System.out.println("After " + Arrays.toString(arr3));
    }

    //Efficient
    //T: O(m+n), S: O(1)
    void mergeSortedArrays(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;

        //Simultaneously traverse both arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                System.out.print(arr1[i] + " ");
                i++;
            } else {
                System.out.print(arr2[j] + " ");
                j++;
            }
        }

        // Finally append all appending elements
        // of larger array
        while (i<arr1.length) {
            System.out.print(arr1[i] + " ");
            i++;
        }
        while (j<arr2.length) {
            System.out.print(arr2[j] + " ");
            j++;
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedArrays mergeTwoSortedArrays = new MergeTwoSortedArrays();
        mergeTwoSortedArrays.mergeSortedArrays1(new int[]{10, 15, 20, 20}, new int[]{1, 12});
        System.out.println("-----------------------------");
        mergeTwoSortedArrays.mergeSortedArrays(new int[]{10, 15, 20, 20}, new int[]{1, 12});
        System.out.println("-----------------------------");
        mergeTwoSortedArrays.mergeSortedArrays(new int[]{10, 15, 20, 40}, new int[]{8, 11, 55});

    }
}
