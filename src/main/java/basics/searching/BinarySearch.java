package basics.searching;

import java.util.Arrays;

//Binary search is only effective
//when array is sorted
public class BinarySearch {

    //Doesn't work
    int binarySearchMyRecursive(int[] arr, int el) {
        int mid = (arr.length/2);
        if (mid<0) return -1;
        if (arr[mid] == el) {
            return mid;
        } else if (el < arr[mid]) {
            return binarySearchMyRecursive(Arrays.copyOfRange(arr, 0, mid), el);
        } else {
            return binarySearchMyRecursive(Arrays.copyOfRange(arr, mid, arr.length), el);
        }
    }

    //T:O(log(n)), S:O(log(n))
    int binarySearchSirRecursive(int[] arr, int low, int high, int el) {
        int mid = (low+high)/2;
        if (low>high) return -1;
        if (arr[mid] == el) {
            return mid;
        } else if (el < arr[mid]) {
            high = mid - 1;
            return binarySearchSirRecursive(arr, low, high, el);
        } else {
            low = mid + 1;
            return binarySearchSirRecursive(arr, low, high, el);
        }
    }

    //T:O(log(n)), S:O(1)
    int binarySearchSirIterative(int[] arr, int el) {
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
        System.out.println(bs.binarySearchMyRecursive(new int[] {10, 20, 30, 40, 50, 60}, 50));
        System.out.println("-------------------------");

        System.out.println(bs.binarySearchSirIterative(new int[] {10, 20, 30, 40, 50, 60}, 60));
        System.out.println(bs.binarySearchSirIterative(new int[] {10, 20, 30}, 25));
        System.out.println("-------------------------");


        System.out.println(bs.binarySearchSirRecursive(new int[] {10, 20, 30}, 0, 2, 30));
    }
}
