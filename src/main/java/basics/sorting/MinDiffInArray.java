package basics.sorting;

import java.util.Arrays;

public class MinDiffInArray {

    //T: Theta(N) + O(NlogN) --> O(NlogN)
    int minDifference(int[] arr) {
        Arrays.sort(arr);
        int mindiff = Integer.MAX_VALUE;
        for (int i=1; i<arr.length; i++) {
            mindiff = Math.min(mindiff, arr[i] - arr[i-1]);
        }
        return mindiff;
    }

    //T: Theta(N^2)
    int minDifferenceNaive(int[] arr) {
        int res = Integer.MAX_VALUE;
        for (int i=1; i<arr.length; i++) {
            for (int j=0; j<i; j++) {
                int diff = Math.abs(Math.abs(arr[i])-Math.abs(arr[j]));
                if (diff<res) res = diff;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinDiffInArray minDiffInArray = new MinDiffInArray();
        System.out.println(minDiffInArray.minDifferenceNaive(new int[] {5, 3, 8}));
        System.out.println(minDiffInArray.minDifference(new int[] {5, 3, 8}));
        System.out.println(minDiffInArray.minDifference(new int[] {10, 8, 1, 4}));

    }
}
