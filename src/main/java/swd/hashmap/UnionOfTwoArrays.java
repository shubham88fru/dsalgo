package swd.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/union-of-two-arrays3538/1
public class UnionOfTwoArrays {
    public static int doUnion(int a[], int n, int b[], int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int el: a) {
            if (!map.containsKey(el)) map.put(el, 1);
        }

        for (int el: b) {
            if (!map.containsKey(b)) map.put(el, 1);
        }

        return map.size();
    }
}
