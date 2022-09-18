package misc;

import java.util.*;

//Sort by frequency of occurrence and
//if same frequency then sort by value
//ie smaller element first.
public class SortElementsByFrequency {

    //O(nlogn)
    void sortByFrequency(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map
                    .getOrDefault(j, 0) + 1
            );
        }
        List<Map.Entry<Integer, Integer>>
                freqs = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<Integer, Integer>> comparator
                = Map.Entry
                .comparingByValue(Collections.
                        reverseOrder());
        freqs.sort(comparator
                .thenComparing(Map.Entry::getKey));
        int index=0;
        for (Map.Entry<Integer, Integer> e: freqs)
            for (int i=0; i<e.getValue(); i++)
                arr[index++] = e.getKey();
        System.out.println(Arrays.toString(arr));
    }

    void sortByFrequencyLinearTime(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map
                    .getOrDefault(j, 0) + 1
            );
        }

        List<List<Integer>> freq =
                new ArrayList<>();
        for (int i=0;i<=arr.length;i++) {
            freq.add(new ArrayList<Integer>());
        }
        for (Map.Entry<Integer, Integer> e: map.entrySet())
            freq.get(e.getValue()).add(e.getKey());

        int index = 0;

        //Although this looks like it will
        //be O(n^3) but it is not. It will still
        //be O(n) because the arraylist of arraylist
        //will have exactly n elements so this loop is
        //overall going to run exactly n times.
        for (int i=arr.length; i>0; i--) {
            for (int x: freq.get(i))
                for (int j=0; j<i; j++)
                    arr[index++] = x;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        SortElementsByFrequency sortElementsByFrequency
                = new SortElementsByFrequency();
        int[] arr = {10,30,30,20};
        sortElementsByFrequency
                .sortByFrequency(arr);
        System.out.println("-----------");
        int[] arr2 = {10,5,20,5,10,10,30};
        sortElementsByFrequency
                .sortByFrequency(arr2);
    }
}
