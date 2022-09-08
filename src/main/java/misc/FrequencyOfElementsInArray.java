package misc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrequencyOfElementsInArray {

    //dont care about order
    void printFrequency(int[] arr) {
        Map<Integer, Integer> fmap = new HashMap<>();
        for (int a: arr) {
            fmap.put(a,
                    fmap.getOrDefault(a, 0)+1);
        }
        System.out.println(fmap);
    }

    //print in order of appearance in array.
    void printFrequencyInOrder(int[] arr) {
        Map<Integer, Integer> linkedFmap
                = new LinkedHashMap<>();
        for (int a: arr)
            linkedFmap.put(
                    a,
                    linkedFmap
                       .getOrDefault(a, 0)+1
            );
        System.out.println(linkedFmap);
    }

    public static void main(String[] args) {
        FrequencyOfElementsInArray
                frequencyOfElementsInArray
                = new FrequencyOfElementsInArray();

        int[] arr = {10, 5, 20, 5, 10, 5};
        frequencyOfElementsInArray
                .printFrequency(arr);

        frequencyOfElementsInArray
                .printFrequencyInOrder(arr);
    }
}
