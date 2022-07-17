package basics.searching;

import java.util.Arrays;

//Binary search is only effective
//when array is sorted
public class BinarySearch {

    //Doesn't work
    //TODO: Check binary search implementation using recursion.
    int binarySearchMy(int[] arr, int el) {
        int mid = (arr.length/2);
        if (mid<0) return -1;
        if (arr[mid] == el) {
            return mid;
        } else if (el < arr[mid]) {
            return binarySearchMy(Arrays.copyOfRange(arr, 0, mid), el);
        } else {
            return binarySearchMy(Arrays.copyOfRange(arr, mid, arr.length), el);
        }
    }

    int binarySearchSir(int[] arr, int el) {
        int start = 0, mid;
        int last = arr.length - 1;
        while (start<=last) {
            mid = (start+last)/2;
            if (el == arr[mid]) return mid;
            else if (el<arr[mid]) {
                last = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearchMy(new int[] {10, 20, 30, 40, 50, 60}, 50));
        System.out.println(bs.binarySearchSir(new int[] {10, 20, 30, 40, 50, 60}, 60));
        System.out.println(bs.binarySearchSir(new int[] {10, 20, 30}, 25));
    }
}
