package arrays;

import java.util.Arrays;

public class MoveZerosToEnd {

    //My soln - doesn't work.
    static int[] moveZerosToEnd(int arr[]) {
        int zeroIdx = 0;
        int nonZeroIdx = 0;
        for (int i=0; i<arr.length; i++) {
           if (arr[i]==0) {
               zeroIdx = i;
               arr[zeroIdx] = arr[nonZeroIdx];
               arr[nonZeroIdx] = 0;
           } else nonZeroIdx = i;
        }
        return arr;
    }

    //T: O(N^2)
    static int[] moveZerosToEndNaiveSir(int arr[]) {
        for (int i = 0; i<arr.length; i++) {
            if (arr[i]==0) {
                for(int j=i+1; j< arr.length; j++) {
                    if(arr[j]!=0) {
                        int temp;
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        return arr;
    }

    //T:O(N)
    static int[] moveZerosToEndSir(int arr[]) {
        int count = 0;
        for(int i =0; i<arr.length; i++) {
            if (arr[i]!=0) {
                int temp;
                temp = arr[count];
                arr[count] = arr[i];
                arr[i] = temp;
                count++;
            }
        }
        return arr;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveZerosToEnd(new int[] {8, 5, 0, 10, 0, 20})));
        System.out.println(Arrays.toString(moveZerosToEndNaiveSir(new int[] {8, 5, 0, 10, 0, 20})));
        System.out.println(Arrays.toString(moveZerosToEndSir(new int[] {8, 5, 0, 10, 0, 20})));
    }
}
