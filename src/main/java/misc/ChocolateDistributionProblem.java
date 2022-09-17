package misc;

//Given an array with no. of chocolates in
//each packet and the no. of children.
//Need to distribute these chocolates such that
//Every child should get exactly one packet,
//also, we need to minimize the diff of chocolates
//between child who got min no. of chocolates and other
//child who got max no. of chocolates.

import java.util.Arrays;

//Basically, in this problem we need to produce the min
//difference in a set of m elements in a given array.
//We need to pick m elements from the array such that
//diff between min and max in that set is minimum
public class ChocolateDistributionProblem {

    //T: O(nlogn)
    int minDiff(int[] arr, int m) {
        if (m>arr.length)
            return Integer.MAX_VALUE;
        Arrays.sort(arr);
        int res = arr[m-1]-arr[0];

        //min in every window.
        for (int i=1; (i+m-1)<arr.length;i++)
            res = Math.min(res, (arr[i+m-1]-arr[i]));
        return res;
    }

    public static void main(String[] args) {
        ChocolateDistributionProblem chocolateDistributionProblem
                = new ChocolateDistributionProblem();
        int[] arr = {4,2,5,7,11,10,9};
        System.out.println(
                chocolateDistributionProblem
                        .minDiff(arr, 3)
        );
    }
}
