package misc;

import java.util.Arrays;
import java.util.Collections;

//Given an array and a num k, need to
//find sum of k elements so that sum is
//max
public class CollectMaxKFromArray {

    //T: (nlogn)
    void printMaxSum(Integer[] arr, int k) {
        Arrays.sort(arr, Collections.reverseOrder());
        int sum=0;
        for (int i=0; i<k; i++)
            sum+=arr[i];
        System.out.println(sum);
    }

    public static void main(String[] args) {
        CollectMaxKFromArray collectMaxKFromArray
                = new CollectMaxKFromArray();
        Integer[] arr = {3,7,2,5,12,30};
        collectMaxKFromArray
                .printMaxSum(arr, 3);
    }
}
