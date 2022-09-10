package misc;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

//Given an array, count the no. of
//greater elements for each element of
//the array.
public class CountGreaterElements {

    //T: O(nlogn)
    void printGreaterElementCount(int[] arr) {
        TreeMap<Integer, Integer> m
                = new TreeMap<>(Collections.reverseOrder());

        //T: O(nlogn)
        //el and its freq map.
        for (int x: arr)
            m.put(x, m.getOrDefault(x, 0)+1);

        //T: O(nlogn)
        int cumFreq = 0;
        for (Map.Entry<Integer, Integer>e: m.entrySet()) {
            int temp = e.getValue();
            m.put(e.getKey(), cumFreq);
            cumFreq += temp;
        }

        //T: O(nlogn)
        for (int x: arr)
            System.out.print(m.get(x)+" ");
    }

    public static void main(String[] args) {
        CountGreaterElements countGreaterElements
                = new CountGreaterElements();
        int[] arr = {2, 8, 10, 5, 8};
        countGreaterElements.
                printGreaterElementCount(arr);
    }
}
