package arrays;

import java.util.Arrays;

//left -> counter clockwise rotation
public class LeftRotateArrayByOne {

    //T:O(N), S:O(1)
    static int[] rotateLeft(int arr[]) {
        int first = arr[0];
        int len = arr.length;
        for (int i=1;i<len;i++) {
            arr[i-1] = arr[i];
        }
        arr[len-1] = first;
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotateLeft(new int[] {1,2,3,4,5})));
    }
}
