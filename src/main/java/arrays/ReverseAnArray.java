package arrays;

import java.util.Arrays;

public class ReverseAnArray {

    //T: O(N), S:O(1)
    static int[] reverse(int arr[]) {
        int start = 0;
        int end = arr.length - 1;

        while (start<=end) {
            //swap
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            //move
            start++;
            end--;
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static void main(String[] args) {
        reverse(new int[]{1,2,3,4,5});
        reverse(new int[]{10, 5, 7, 30});
        reverse(new int[]{30, 7, 6, 5, 10});
    }
}
