package misc;

import java.util.HashMap;
import java.util.Map;

public class FrequencyOfElementsInArray {

    void printFrequency(int[] arr) {
        Map<Integer, Integer> fmap = new HashMap<>();
        for (int a: arr) {
            fmap.put(a,
                    fmap.getOrDefault(a, 0)+1);
        }
        System.out.println(fmap);
    }

    public static void main(String[] args) {
        FrequencyOfElementsInArray
                frequencyOfElementsInArray
                = new FrequencyOfElementsInArray();

        int[] arr = {10, 5, 20, 5, 10, 5};
        frequencyOfElementsInArray
                .printFrequency(arr);
    }
}
