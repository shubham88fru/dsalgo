package strvr.greedy;

import java.util.Arrays;

//@link - https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#
//@strvr - https://takeuforward.org/data-structure/minimum-number-of-platforms-required-for-a-railway/
public class MinimumPlatforms {
    static int findPlatform(int arr[], int dep[], int n) {
        // add your code here
        return findPlatGreedy(arr, dep, n);
    }

    private static int findPlatGreedy(int[] arr, int[] dep, int n) {
        //I have no idea how this works.
        //We're literally changing the arrival-departure
        //pairs by sorting both the arrays, but still works :/
        Arrays.sort(arr);
        Arrays.sort(dep);

        int result = 1;
        int plat_needed = 1;
        int arriveIdx=1;
        int departIdx=0;

        while (arriveIdx < n && departIdx < n) {
            if (arr[arriveIdx] <= dep[departIdx]) {
                plat_needed += 1;
                arriveIdx += 1;
            } else if (arr[arriveIdx] > dep[departIdx]) {
                plat_needed -= 1;
                departIdx += 1;
            }

            if (plat_needed > result) result = plat_needed;
        }

        return result;
    }
}
