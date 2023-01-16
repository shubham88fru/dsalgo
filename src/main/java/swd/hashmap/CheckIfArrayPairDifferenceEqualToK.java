package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
public class CheckIfArrayPairDifferenceEqualToK {
    public boolean findPair(int[] arr, int size, int n) {
        //code here.
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i < size; i++) {
            int a = arr[i];
            int b1 = a - n; //a-b1 = n
            int b2 = a + n; //b2-a = n
            if (map.containsKey(b1) || map.containsKey(b2)) return true;
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        return false;
    }
}
