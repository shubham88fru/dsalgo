package misc;

import java.util.HashSet;
import java.util.Set;

//Given an unsorted array and a sum,
//need to find if there exists a pair
//in the array with given sum.
public class PairSum {

    //Naive soln will be to find sum of
    //each possible pair in the array.
    //T: O(N^2)
    boolean findSumPairNaive(int[] arr, int sum) {
        for (int i=0; i<arr.length; i++) {
            for (int j=i+1; j<arr.length;j++) {
                if (arr[i]+arr[j] == sum)
                    return true;
            }
        }
        return false;
    }

    //T:O(N), S:O(N)
    boolean findSumPair(int[] arr, int sum) {
        Set<Integer> set
                = new HashSet<>();
        for (int el: arr) {
            if (set.contains(sum-el))
                return true;
            else
                set.add(el);
        }
        return false;
    }

    public static void main(String[] args) {
        PairSum pairSum
                = new PairSum();
        int[] arr = {3, 2, 8, 15, -8};
        System.out.println(
                pairSum.findSumPairNaive(arr,
                        17));

        System.out.println(
                pairSum.findSumPair(arr,
                        17)
        );
    }
}
