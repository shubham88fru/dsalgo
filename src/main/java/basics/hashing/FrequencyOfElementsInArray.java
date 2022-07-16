package basics.hashing;

import java.util.HashMap;
import java.util.Map;

//Order of print doesn't matter.
//if it does, use linked hash map.
public class FrequencyOfElementsInArray {

    void printFrequencyOfElements(int[] arr) {
        if (arr.length == 0) return;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int el: arr) {
            if (frequencyMap.containsKey(el)) {
                int count = frequencyMap.get(el);
                frequencyMap.remove(el);
                frequencyMap.put(el, ++count);
            } else {
                frequencyMap.put(el, 1);
            }
        }
        System.out.println(frequencyMap);
    }

    void printFrequencyOfElementsSir(int[] arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int x: arr)
            //if existing key put in map, new value overrides old value.
            frequencyMap.put(x, frequencyMap.getOrDefault(x, 0)+1);
        for (Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        FrequencyOfElementsInArray frequency = new FrequencyOfElementsInArray();
        frequency.printFrequencyOfElements(new int[] {0, 0, 1, 2, 3, 1, 4, 2});
        frequency.printFrequencyOfElementsSir(new int[] {0, 0, 1, 2, 3, 1, 4, 2});
    }
}
