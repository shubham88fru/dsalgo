package interview;

import java.util.Arrays;

//@link - https://www.geeksforgeeks.org/problems/two-numbers-with-sum-closest-to-zero1737/1
//@company - Amazon
public class PairSumClosestToZero {
    public static int closestToZero (int arr[], int n) {
        return pass1(arr, n);
    }

    private static int pass1(int[] arr, int n) {
        Arrays.sort(arr);

        int l = 0;
        int r = n-1;

        int min = Integer.MAX_VALUE;
        while (l < r) {
            int sum = arr[l] + arr[r];

            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
            } else if (Math.abs(sum) == Math.abs(min)) {
                min = Math.max(min, sum);
            }

            if (sum > 0) {
                r -= 1;
            } else {
                l += 1;
            }
        }

        return min;
    }
}
